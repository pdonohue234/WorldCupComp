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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller 
public class LoginController extends MultiActionController implements InitializingBean{

	@Autowired
	private UserService		m_userService;
	yy
	
	@RequestMapping("/register")  
	public ModelAndView registerUser(@ModelAttribute User p_user) {  
	  
		boolean success = this.getUserService().insertData(p_user); 
	  
		Map<String, Object> model = new HashMap<String, Object>();  
		model.put("added", success);  
		return new ModelAndView("register", "model", model);  
	}
<<<<<<< HEAD
	
	/** ..
=======
	
	/** 
>>>>>>> refs/remotes/origin/master
	 * Check whether all mandatory properties are really set (initialized)
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(m_userService, "UserService is mandatory!");
	}

	public UserService getUserService() {
		return m_userService;
	}

	public void setUserService(UserService p_userService) {
		m_userService = p_userService;
	}
	
	
}
