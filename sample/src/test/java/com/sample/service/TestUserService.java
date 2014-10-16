package com.sample.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import com.sample.dao.UserDao;

public class TestUserService {
	private static final String USERSERVICE_BEAN_ID = "userService";	
	private UserService userService;
	private Locale locale = Locale.US;
	private UserDao userDao;
	
	@BeforeClass(alwaysRun=true)
	public void setupClass() {
		ApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
		userService = (UserService) context.getBean(USERSERVICE_BEAN_ID);
		userDao = mock(UserDao.class);
		userService.setUserDao(userDao);
	}
	
	@Test
	public void testShow() {
		List<String> list = Arrays.asList("a");
		when(userDao.show()).thenReturn(list);
		List<String> nameList = userService.show(locale);
		Assert.assertNotNull(nameList);
		Assert.assertEquals(nameList.size(), 1);
	}
	
	@Test
    public void testAdd() {
		Answer<Boolean> answer = new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				if (invocation.getArguments()[0] != null) {
					return true;
				} else {
					return false;
				}
			}
			
		};
		when(userDao.add(anyString())).then(answer);
    	Assert.assertTrue(userService.add("testAdd", locale));
    	Assert.assertFalse(userService.add(null, locale));
    }	

}
