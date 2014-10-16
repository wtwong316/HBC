package com.sample.api;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.sample.common.RestAPIResult;
import com.sample.common.RestAction;
import com.sample.common.UserResources;
import com.sample.common.UserResourcesAssembler;
import com.sample.service.UserService;

/**
 * This class is to provide a controller for user resource
 * @author WT
 *
 */
@Controller("/sample")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserResourcesAssembler userResourcesAssembler;
	
	/**
	 * This method is to get the api description of the user resource 
	 * @param locale The locale to use in i18N
	 * @return the user resources
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public HttpEntity<UserResources> navigateUser(Locale locale) {
		logger.debug("navigate user");
		return new ResponseEntity<UserResources>(userResourcesAssembler.toResource(RestAction.NULL), HttpStatus.OK);
	}
	
	/**
	 * This method is to show all the users
	 * @param locale the local to use in i18N
	 * @return
	 */
	@RequestMapping(value = "/user/show", method = RequestMethod.GET)
	public HttpEntity<UserResources> showUsers(Locale locale) {
		logger.debug("show users");
		List<String> users = userService.show(locale);
		return new ResponseEntity<UserResources>(userResourcesAssembler.toResource(users), HttpStatus.OK);
	}
	
	/** This method is to add a user 
	 * @param userName the use name to add
	 * @param locale the locale to use in i18N
	 * @return the Rest API Result 
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)	
	public HttpEntity<RestAPIResult> addUser(@RequestParam String userName, Locale locale) {
		logger.debug("add user", locale);
		boolean result = userService.add(userName, locale);
		if (result) {
			return new ResponseEntity<RestAPIResult>(
					new RestAPIResult(RestAPIResult.SUCCESS,RestAPIResult.SUCCESS_STATUS), HttpStatus.OK);
		} else {
			return new ResponseEntity<RestAPIResult>(
					new RestAPIResult(RestAPIResult.FAILED,RestAPIResult.DUPLICATE_ENTITY), HttpStatus.BAD_REQUEST);
		}

	}
	
}
