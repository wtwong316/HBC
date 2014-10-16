package com.sample.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.dao.UserDao;

/**
 * This class is to perform the request from the rest api layer to service the user related tasks.
 * @author WT
 *
 */
@Component
public class UserService {
	@Autowired
	private UserDao userDao;
    
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * call the show method from userDao to get all the user
	 * @param locale the locale to use in i18N
	 * @return
	 */
	public List<String> show(Locale locale) {
		return userDao.show();
	}
	
	/**
	 * call the add method from userDao to add a user
	 * @param userName input user name
	 * @param locale the locale to use in i18N
	 * @return true if add success, otherwise false
	 */
    public boolean add(String userName, Locale locale) {
    	return userDao.add(userName);
    }
}
