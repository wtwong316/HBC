package com.sample.dao;
 
import java.util.List;
 
/**
 * User: samirmes
 * Date: 12/9/13
 * Time: 3:23 PM
 */
public interface UserDao {
	/**
	 * This method is to show all the users
	 * @return a list of use in String
	 */
    List<String> show();
    
    /**
     * This method is to add a user
     * @param username user name
     * @return true if add success, otherwise false
     */
    boolean add(String username);
}
