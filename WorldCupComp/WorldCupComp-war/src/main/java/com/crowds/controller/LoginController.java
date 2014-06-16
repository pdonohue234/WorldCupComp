package com.crowds.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ArrayUtils;

import com.crowds.database.dto.Fixture;
import com.crowds.database.dto.Prediction;
import com.crowds.database.dto.User;
import com.crowds.database.dto.UserScore;
import com.crowds.services.EventService;
import com.crowds.services.FixtureService;
import com.crowds.services.PredictionService;
import com.crowds.services.UserService;
import com.crowds.services.UserScoreService;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller 
public class LoginController {

	public Logger			m_logger	= 	Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private FixtureService		m_fixtureService;	
	
	@Autowired
	private PredictionService	m_predictionService;
	
	@Autowired
	private UserService			m_userService;
	
	@Autowired
	private UserScoreService	m_userScoreService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String loadRegisterFormPage(Model m, HttpServletRequest request) {
		Map<String,Object> attributes = request.getParameterMap();
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			if( StringUtils.equalsIgnoreCase(entry.getKey(), "tx") ) {
				if(entry.getValue().getClass().isArray()) {
					if(ArrayUtils.isNotEmpty( (String[]) entry.getValue())) {
						m.addAttribute("tx", (String)((String[])entry.getValue())[0]);
					}
			    		
				}
				else {
					m.addAttribute("tx", entry.getValue());
				}
			}
		}
		m.addAttribute("user", new User());
		return "register";
	}
	
	/**
	 * Method to initially register a use and direct them to the predictions page
	 */
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)  
	public ModelAndView registerUser(@ModelAttribute User user, BindingResult result) { 
		Map<String, Object> model = new HashMap<String, Object>();  
		
		//If user wants to add a new private group, ensure the name does not already exist
		if(user.getNewPrivateCompName()) {
			if(StringUtils.isNotBlank(user.getPrivateCompName())) {
				int numRows = this.getUserService().doesPrivateCompNameExist(user.getPrivateCompName());
				//Then it already exists!
				if(numRows > 0) {
					model.put("added", false);  
					result.rejectValue("privateCompName","privateCompName.notvalid","Group already exist!");
					return new ModelAndView("register", "model", model);					
				}
			}
		}
		//If user wants to join an existing private group, ensure the group already exists
		else {
			if(StringUtils.isNotBlank(user.getPrivateCompName())) {
				int numRows = this.getUserService().doesPrivateCompNameExist(user.getPrivateCompName());
				//Then it already exists!
				if(numRows == 0) {
					model.put("added", false);  
					result.rejectValue("privateCompName","privateCompName.notvalid","Group does not exist!");
					return new ModelAndView("register", "model", model);					
				}
			}
		}
		
		boolean success = this.getUserService().insertData(user); 

		model.put("added", success);  

		if(success) {
			this.m_logger.warning("User has been added: " + user.getUserId());
			model = getFixtureResultList(model, user.getUserId());
			
			sendMail(user);
			
			return new ModelAndView("predictions", "model", model); 
		}
		else {
			this.m_logger.warning("User has NOT been added: " + user.getUserId());
			//Add in error message here
			result.rejectValue("userId","userId.notvalid","Account already in place with that email!");
			return new ModelAndView("register", "model", model);
		}  
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loadLoginFormPage(Model m) {
		m.addAttribute("user", new User());
		return "login";
	}
	
	@ModelAttribute("fixtureResultList")
	public FixtureResultList createModel() {
	    return new FixtureResultList();
	}
	
	@RequestMapping(value="/predictions", method=RequestMethod.POST)    
	public ModelAndView loginUser(@ModelAttribute User user, BindingResult result) {  
		this.m_logger.warning("Finding user: " + user.getUserId());
		this.m_logger.warning("Finding password: " + user.getPassword());
		
		String error = this.getUserService().validateUser(user); 
		
		Map<String, Object> model = new HashMap<String, Object>();  
		
		if(StringUtils.isNotBlank( error )) {
			if( StringUtils.equalsIgnoreCase("found", error) ) {
				model.put("found", true);
				User userLoggedIn = this.getUserService().getUser(user.getUserId());
				model.put("userLoggedIn", userLoggedIn );
				
				model = getFixtureResultList(model, userLoggedIn.getUserId());
				
				return new ModelAndView("predictions", "model", model);  
			}
			else if( StringUtils.equalsIgnoreCase("password", error) ) {
				//Add in error message here
				result.rejectValue("password","password.notvalid","Password entered is invalid!");
			}
			else if( StringUtils.equalsIgnoreCase("userId", error) ) {
				//Add in error message here
				result.rejectValue("userId","userId.notvalid","Email entered is invalid!");
			}
			else if( StringUtils.equalsIgnoreCase("empty", error) ) {
				//Add in error message here
				result.rejectValue("userId","userId.notvalid","Credentials submitted are empty!");
				this.m_logger.warning("BindingResult: " + result.getFieldError().getField());
			}
			else {
				this.m_logger.warning("Server Error Occurred retrieving user: " + user.getUserId());
				//Add in error message here
				result.rejectValue("userId","userId.notvalid","Server Error Occurred retrieving user - try again!");
			}
		}
		else {
			this.m_logger.warning("Server Error Occurred retrieving user: " + user.getUserId());
			//Add in error message here
			result.rejectValue("userId","userId.notvalid","Server Error Occurred retrieving user - try again!");			
		}
		return new ModelAndView("login", "model", model);
	}
	
	public Map<String, Object> getFixtureResultList(Map<String, Object> model, String userLoggedIn) {
		List<Prediction> predictions = this.getPredictionService().getUsersPredictions(userLoggedIn);
		
		List<Fixture> fixtures = this.getFixtureService().getEventsFixtures(EventService.WORLD_CUP_2014_ID);
		if(fixtures != null) {
			if(predictions != null) {
				List<FixtureResult> results = getResults(fixtures, predictions, userLoggedIn);
				if(results!= null) {
					FixtureResultList fixtureResultList = new FixtureResultList();
					fixtureResultList.setFixtureResults(results);
					model.put("fixtureResultList", fixtureResultList);
				}
			}
			else { 
				List<FixtureResult> results = getResults(fixtures, null, userLoggedIn);
				if(results!= null) {
					FixtureResultList fixtureResultList = new FixtureResultList();
					fixtureResultList.setFixtureResults(results);
					model.put("fixtureResultList", fixtureResultList);
				}
			}
		}
		else {
			this.m_logger.warning("No fixtures found!");
		}
		
		double score = this.getPredictionService().calcuateUserPredictionScore(fixtures, predictions);
		if(score != -1) {
			model.put("score", score); 
		}
		
		return model;
	}
	
	public List<FixtureResult> getResults(List<Fixture> p_fixtures, List<Prediction> p_predictions, String p_userId ) {
		List<FixtureResult> results = new ArrayList<FixtureResult>();
		try {
			if(p_fixtures!= null && p_fixtures.size() != 0) {
				for(Fixture fixture : p_fixtures) {
					Prediction p = null;
					if(p_predictions!= null && p_predictions.size() != 0) {
						for(Prediction prediction : p_predictions) {
							if(StringUtils.isNotEmpty(fixture.getGameId()) && StringUtils.isNotEmpty(prediction.getGameId())) {
								if(StringUtils.equalsIgnoreCase(fixture.getGameId(), prediction.getGameId())) {
									p = prediction;
									break;
								}
							}		
						}
					}
					if(p!=null)
						//Add new fixture
						results.add( new FixtureResult(fixture, p) );
					else
						//Add new fixture
						results.add( new FixtureResult(fixture, p_userId ) );
				}
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to get User's Prediction/Fixtures list for User:" + p_predictions.get(0).getUserId() );
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return results;
	}
	
	
	
	@RequestMapping(value="/updatePredictions", method=RequestMethod.POST)    
	public ModelAndView updatePredictions(@ModelAttribute FixtureResultList fixtureResultList) {  		
		String userId = "";
		boolean updatesHappened = false;
		boolean oneUpdateFailed = false;
		
		boolean success = false;
		//Update each entry amended
		if(fixtureResultList!= null && fixtureResultList.getFixtureResults().size() > 0) {
			userId = fixtureResultList.getFixtureResults().get(0).getUserId();
			this.m_logger.warning("Updating Predictions for: " + userId);
			for(FixtureResult fixtureResult : fixtureResultList.getFixtureResults() ) {
				this.m_logger.warning("For fixture: " + fixtureResult.getGameId());
				//Check if values have changed
				if(fixtureResult.isChanged()) {
					this.m_logger.warning("It's changed: Team1=" + fixtureResult.getTeamOneScore() + ", Team2=" + fixtureResult.getTeamTwoScore() + ", Winning Team=" + fixtureResult.getWinningTeam());
					if(fixtureResult.doubleCheckGameHasNotStarted()) {
						this.m_logger.warning("Game Has Not Started so proceed: " + fixtureResult.getGameId());
						//Ensure that we have the keys to update with
						if(fixtureResult.keysAreSet()) {
							this.m_logger.warning("It's keys are set");
							//If scores have been set
							if(fixtureResult.scoresAreSet()) {
								this.m_logger.warning("Updating Prediction record: " + fixtureResult.getUserId() +"-"+ fixtureResult.getGameId());
								Prediction prediction = new Prediction(fixtureResult.getUserId(), fixtureResult.getGameId() );
								prediction.setTeam1Prediction(Integer.parseInt(fixtureResult.getTeamOneScore()) );
								prediction.setTeam2Prediction(Integer.parseInt(fixtureResult.getTeamTwoScore()) );
								prediction.setWinningTeamPrediction(fixtureResult.getWinningTeam() );
								prediction.setDate(new Date());
								success = this.getPredictionService().saveOrUpdate(prediction);
								updatesHappened = true;
							}
						}
					}
					else {
						this.m_logger.warning("But Game Has STARTED so CANNOT proceed: " + fixtureResult.getGameId());
						oneUpdateFailed = true;
					}
				}
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();  
		model = getFixtureResultList(model, userId);
		if(updatesHappened)
			model.put("updated", success);

		model.put("oneUpdateFailed", oneUpdateFailed);
		return new ModelAndView("predictions", "model", model);  
	}
	
	@RequestMapping(value="/miniGroupTable", method=RequestMethod.POST)    
	public ModelAndView miniGroupTable(HttpServletRequest request) { 
		try {
			Map<String, Object> model = new HashMap<String, Object>(); 
			List<UserResult> results = new ArrayList<UserResult>();
			String userId = null;
			String privateCompName = null;
			
			Map<String,Object> attributes = request.getParameterMap();
			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				if( StringUtils.equalsIgnoreCase(entry.getKey(), "userId") ) {
					if(entry.getValue().getClass().isArray()) {
						if(ArrayUtils.isNotEmpty( (String[]) entry.getValue())) {
							userId =  (String)((String[])entry.getValue())[0];
						}
				    		
					}
					else {
						userId = (String) entry.getValue();
					}
				}
				
				if( StringUtils.equalsIgnoreCase(entry.getKey(), "privateCompName") ) {
					if(entry.getValue().getClass().isArray()) {
						if(ArrayUtils.isNotEmpty( (String[]) entry.getValue())) {
							privateCompName =  (String)((String[])entry.getValue())[0];
						}
				    		
					}
					else {
						privateCompName = (String) entry.getValue();
					}
				}				
			}
			
			
			if(StringUtils.isNotBlank( userId ) && StringUtils.isNotBlank( userId )) {
				User userLoggedIn = this.getUserService().getUser(userId);
				
				List<User> allUsers = this.getUserService().getUserListForPrivateCompName(privateCompName);
				
				List<Fixture> fixtures = this.getFixtureService().getEventsFixtures(EventService.WORLD_CUP_2014_ID);
				
				//For each User get their score
				for(User user : allUsers ) {
					this.m_logger.warning("GroupUser: " + user.getUserId() );
					List<Prediction> predictions = this.getPredictionService().getUsersPredictions(user.getUserId());				
					double score = this.getPredictionService().calcuateUserPredictionScore(fixtures, predictions);
					
					
					results.add( new UserResult(user.getName(), score) );
				}
				
				model.put("groupName", privateCompName );
				model.put("user", userLoggedIn);
			}
			
			if(results.size() > 0) {
				Collections.sort(results, new MyComparator());
				
				UserResultList userResultList = new UserResultList();
				userResultList.setUserResults(results);
				model.put("userResultList", userResultList);
			}
			
			return new ModelAndView("miniGroupTable", "model", model);  
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/createLeaderBoard", method=RequestMethod.GET)    
	public ModelAndView createLeaderBoard(HttpServletRequest request) { 
		try {
			Map<String, Object> model = new HashMap<String, Object>(); 
			List<UserResult> results = new ArrayList<UserResult>();
			
			//Get all the users in the table
			List<User> allUsers = this.getUserService().getUserList();
				
			//Get all the fixtures
			List<Fixture> fixtures = this.getFixtureService().getEventsFixtures(EventService.WORLD_CUP_2014_ID);
				
			//For each User get their score
			for(User user : allUsers ) {
				List<Prediction> predictions = this.getPredictionService().getUsersPredictions(user.getUserId());				
				double score = this.getPredictionService().calcuateUserPredictionScore(fixtures, predictions);
				results.add( new UserResult(user.getName(), score) );
			}
			
			//Sort the array with highest score first
			if(results.size() > 0) {
				Collections.sort(results, new MyComparator());
			}
			
			//Create table to hold all the results
			if(results.size() > 0) {
				Collections.sort(results, new MyComparator());
				
				UserResultList userResultList = new UserResultList();
				userResultList.setUserResults(results);
				model.put("userResultList", userResultList);
			}
			return new ModelAndView("createLeaderBoard", "model", model);  
		}
		catch(Exception e) {
			return null;
		}
	}
	
	
	public void sendMail(User p_user) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("WorldCupPredictionCompetition@gmail.com ", "World Cup Prediction Competition Admin"));
            
            if(StringUtils.isNotBlank(p_user.getName())) {
	            msg.addRecipient(Message.RecipientType.TO,
	                             new InternetAddress(p_user.getUserId(), p_user.getName()));
            }
            else {
	            msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(p_user.getUserId(), ""));           	
            }
            
            msg.setSubject("Welcome to the World Cup Prediction Competion");
            
            String msgBody = "Hi,"
            				+ "\n"
            				+ "\nWelcome to the World Cup Prediction Competion in aid of Laura Lynn Children's Hospice"
            				+ "\n"
            				+ "\nUsername: " + p_user.getUserId()
            				+ "\nPassword: " + p_user.getPassword()
            				+ "\n"
            				+ "\nMake sure to check back to https://worldcuppredictioncomp.appspot.com to enter your predictions"
            				+ "\n"
            				+ "\nThank you for your support.";
            				
            msg.setText(msgBody);
            
            Transport.send(msg);

        } catch (AddressException e) {
			this.m_logger.severe("AddressException Error attemping to email User:" + p_user.getUserId() );
			this.m_logger.severe(e.getLocalizedMessage());
        } catch (MessagingException e) {
			this.m_logger.severe("MessagingException Error attemping to email User:" + p_user.getUserId() );
			this.m_logger.severe(e.getLocalizedMessage());
        } catch (UnsupportedEncodingException e) {
			this.m_logger.severe("UnsupportedEncodingException Error attemping to email User:" + p_user.getUserId() );
			this.m_logger.severe(e.getLocalizedMessage());
        }
        
	}
	
	/**
	 * Check whether all mandatory properties are really set (initialized)
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(m_userService, "UserService is mandatory!");
	}

	@Autowired
	public UserService getUserService() {
		return m_userService;
	}

	public void setUserService(UserService p_userService) {
		m_userService = p_userService;
	}
	
	@Autowired
	public PredictionService getPredictionService() {
		return m_predictionService;
	}

	public void setPredictionService(PredictionService p_predictionService) {
		m_predictionService = p_predictionService;
	}
	
	@Autowired
	public FixtureService getFixtureService() {
		return m_fixtureService;
	}

	public void setFixtureService(FixtureService p_fixtureService) {
		m_fixtureService = p_fixtureService;
	}	
	
	@Autowired
	public UserScoreService getUserScoreService() {
		return m_userScoreService;
	}

	public void setUserScoreService(UserScoreService p_userScoreService) {
		m_userScoreService = p_userScoreService;
	}	
}
