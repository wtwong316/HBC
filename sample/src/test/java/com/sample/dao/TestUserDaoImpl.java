package com.sample.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;

public class TestUserDaoImpl {
	private static final String user1 = "USER1";
	private static final String user2 = "USER2";
	private static final String user3 = "USER3";	
	private static final String USERDAO_BEAN_ID = "userDao";
	private UserDao userDao;
	
	@BeforeClass(alwaysRun=true)
	public void setupClass() {
		ApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
		userDao = (UserDao) context.getBean(USERDAO_BEAN_ID);
	}

	@Test
	public void testAdd1() {
		boolean result = userDao.add(user1);
		Assert.assertTrue(result);
		List<String> list = userDao.show();
		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 1);
	}
	
	@Test(dependsOnMethods={"testAdd1"})
	public void testAdd2() {
		int count1=0;
		List<String> list = userDao.show();
		if (list.size() > 0) {
			for (String user : list) {
				if (user.equals(user1)) {
					count1++;
				}
			}
		}
		boolean result = userDao.add(user1);
		Assert.assertFalse(result);
		list = userDao.show();
		int count2=0;
		if (list.size() > 0) {
			for (String user : list) {
				if (user.equals(user1)) {
					count2++;
				}
			}
		}
		Assert.assertEquals(count1,count2);
	}
	
	@Test(dependsOnMethods={"testAdd2"})
	public void testList() {
		userDao.add(user3);
		userDao.add(user2);
		List<String> list = userDao.show();
		Assert.assertEquals(list.size(), 3);
		Assert.assertTrue(list.get(0).equals(user1));
		Assert.assertTrue(list.get(1).equals(user2));
		Assert.assertTrue(list.get(2).equals(user3));
	}

}
