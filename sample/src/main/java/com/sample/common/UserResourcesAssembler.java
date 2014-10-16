package com.sample.common;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserResourcesAssembler {

	public UserResources toResource(RestAction action) {
		UserResources resources = new UserResources();
		UserResourceLinks userResourceLinks = new UserResourceLinks();
		userResourceLinks.buildLinks(null, null, 0L, action);
		resources.setLinks(userResourceLinks);
		return resources;
	}
	
	public UserResources toResource(List<String> users) {
		UserResources userResources = toResource(RestAction.SHOW_ALL_ACTION);
		userResources.setUsers(users);
		return userResources;
	}
}
