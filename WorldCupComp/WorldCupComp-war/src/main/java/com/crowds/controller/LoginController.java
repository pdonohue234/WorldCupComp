package com.crowds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
	public String loadRegisterFormPage(Model m) {
		m.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)  
	public ModelAndView registerUser(@ModelAttribute User user) {  
		boolean success = this.getUserService().insertData(user); 
	  
		System.out.println(success);
		
		Map<String, Object> model = new HashMap<String, Object>();  
		model.put("added", true);  
		return new ModelAndView("registerUser", "model", model);  
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loadLoginFormPage(Model m) {
		m.addAttribute("user", new User());
		return "login";
	}
	
	@ModelAttribute("fixtureResultList")
	public List<FixtureResult> createModel() {
	    return new ArrayList<FixtureResult>();
	}
	
	@RequestMapping(value="/predictions", method=RequestMethod.POST)    
	public ModelAndView loginUser(@ModelAttribute User user) {  
		User userLoggedIn = this.getUserService().validateUser(user); 
		
		Map<String, Object> model = new HashMap<String, Object>();  
		
		if(userLoggedIn != null) {
			this.m_logger.warning("User found!" + userLoggedIn.getUserId());
			model.put("found", true);
			model.put("userLoggedIn", userLoggedIn);
			
			List<Prediction> predictions = this.getPredictionService().getUsersPredictions(userLoggedIn.getUserId());
			
			List<Fixture> fixtures = this.getFixtureService().getEventsFixtures(EventService.WORLD_CUP_2014_ID);
			if(fixtures != null) {
				
				if(predictions != null) {
					List<FixtureResult> results = getResults(fixtures, predictions);
					if(results!= null) {
						model.put("fixtureResults", results);
					}
				}
				else { 
					List<FixtureResult> results = getResults(fixtures, null);
					if(results!= null) {
						model.put("fixtureResults", results);
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
		}
		else {
			this.m_logger.warning("No user found!");
		}
		
		//model.put("fixtureResultList", new ArrayList<FixtureResult>());
		
		return new ModelAndView("predictions", "model", model);  
	}	
	
	public List<FixtureResult> getResults(List<Fixture> p_fixtures, List<Prediction> p_predictions ) {
		List<FixtureResult> results = new ArrayList<FixtureResult>();
		try {
			if(p_fixtures!= null && p_fixtures.size() != 0) {
				for(Fixture fixture : p_fixtures) {
					Prediction p = null;
					if(p_predictions!= null && p_predictions.size() != 0) {
						for(Prediction prediction : p_predictions) {
							if(StringUtils.isNotEmpty(fixture.getGameId()) && StringUtils.isNotEmpty(prediction.getGameId())) {
								p = prediction;
								break;
							}		
						}
					}
					if(p!=null)
						//Add new fixture
						results.add( new FixtureResult(fixture, p) );
					else
						//Add new fixture
						results.add( new FixtureResult(fixture) );
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
	public ModelAndView updatePredictions(@ModelAttribute List<FixtureResult> fixtureResultList) {  
		Map<String, Object> model = new HashMap<String, Object>();  	
		
		this.m_logger.warning("updatePredictions!" + fixtureResultList.size());
		
		if(fixtureResultList!= null) {
			model.put("fixtureResults", fixtureResultList);
		}
		
		return new ModelAndView("predictions", "model", model);  
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
