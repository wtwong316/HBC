package com.sample.dao;
 
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.collections.IteratorUtils;
 
/**
 * User: samirmes
 * Date: 12/9/13
 * Time: 3:25 PM
 */
public class UserCache {
 
    private static UserCache userCache;
    /**
     * the user name list use the concurrent skip list set so that no locking is needed in multi-threading in the current scenario
     */
    private static ConcurrentSkipListSet<String> userNameList = new ConcurrentSkipListSet<String>();
 
    private UserCache() {
    }
 
    public static UserCache getInstance() {
        if (userCache == null)
            userCache = new UserCache();
 
        return userCache;
    }
    
    public boolean addUser(String userName) {
    	return userNameList.add(userName);
    }
    
    @SuppressWarnings("unchecked")
	public List<String> showUsers() {
    	return IteratorUtils.toList(userNameList.iterator());
    }
    
}
