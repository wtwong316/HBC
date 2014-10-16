package com.sample.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.sample.api.UserController;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserResourceLinks extends BaseResourceLinks {
	public static final String USER = "user";
	public void buildLinks(String pageStartIndex, String pageSize, long totalEntries, RestAction action) {		
		ControllerLinkBuilder parentLink = ControllerLinkBuilder.linkTo(UserController.class).slash(USER);
		if (action == RestAction.NULL) {
			this.createSelfLink(parentLink, pageStartIndex, pageSize, totalEntries);
		} else {
			this.createSelfLink(parentLink.slash(action.getActionName()), pageStartIndex, pageSize, totalEntries);
		}
	
		if (action != RestAction.CREATE_ACTION) {
			this.createCreateLink(parentLink, pageStartIndex, pageSize);
		} 
		
		if (action != RestAction.SHOW_ALL_ACTION) {
			this.createShowAllLink(parentLink, pageStartIndex, pageSize);
		} 

		if (pageStartIndex != null && pageSize != null) {
			long pageSizeValue = Long.valueOf(pageSize).longValue();
			long pageStartIndexValue = Long.valueOf(pageStartIndex).longValue();
			long totalCount = pageStartIndexValue * pageSizeValue;
			if (totalEntries > 0) {
				this.createFirstPageLink(parentLink, "0", pageSize);
				
				this.createLastPageLink(parentLink, String.valueOf(totalEntries%pageSizeValue), pageSize);
			}
			if (totalEntries > totalCount) {
				this.createNextPageLink(parentLink, pageStartIndex, pageSize);
			}
			if (pageStartIndexValue > 0 && totalEntries > totalCount) {
				this.createPrevtPageLink(parentLink, pageStartIndex, pageSize);
			}						
		}
	}
}
