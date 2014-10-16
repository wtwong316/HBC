package com.sample.dao;
 
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * User: samirmes
 * Date: 12/9/13
 * Time: 3:23 PM
 */
@Repository
public class UserDaoImpl implements UserDao {

	private UserCache userCache = UserCache.getInstance();
	
    @Override
    public List<String> show() {
        return userCache.showUsers();
    }
 
    @Override
    public boolean add(String username) {
        return userCache.addUser(username);        
    }
}
