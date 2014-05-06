package com.crowds.controller;

import java.util.HashMap;
import java.util.Map;

import com.crowds.database.dto.User;
import com.crowds.services.UserService;

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

	@Autowired
	private UserService		m_userService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String loadFormPage(Model m) {
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
	
	
}
