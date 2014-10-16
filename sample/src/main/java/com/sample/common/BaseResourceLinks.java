package com.sample.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static com.sample.common.RestAction.CREATE_ACTION;
import static com.sample.common.RestAction.SHOW_ALL_ACTION;;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public abstract class BaseResourceLinks {
	private static final String LINK_REL_SELF = "self";
	private static final String LINK_REL_CREATE = "create";
	private static final String LINK_REL_SHOWALL = "showAll";
	private static final String LINK_FIRST_PAGE = "firstPage";
	private static final String LINK_NEXT_PAGE = "nextPage";
	private static final String LINK_PREV_PAGE = "prevPage";
	private static final String LINK_LAST_PAGE = "lastPage";	
	private static final String PAGE_SIZE = "pageSize";
	private static final String PAGE_START_INDEX = "pageStartIndex";

	
	public Link getCreate() {
		return create;
	}
	public void setCreate(Link create) {
		this.create = create;
	}
	public Link getShowAll() {
		return showAll;
	}
	public void setShowAll(Link showAll) {
		this.showAll = showAll;
	}
	public Link getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(Link firstPage) {
		this.firstPage = firstPage;
	}
	public Link getNextPage() {
		return nextPage;
	}
	public void setNextPage(Link nextPage) {
		this.nextPage = nextPage;
	}
	public Link getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(Link prevPage) {
		this.prevPage = prevPage;
	}
	public Link getLastPage() {
		return lastPage;
	}
	public void setLastPage(Link lastPage) {
		this.lastPage = lastPage;
	}
	
	public Link getSelf() {
		return self;
	}
	public void setSelf(Link self) {
		this.self = self;
	}

	private Link self;
	private Link create;
	private Link showAll;
	private Link firstPage;
	private Link nextPage;
	private Link prevPage;
	private Link lastPage;
	private long total;
	
	protected void createSelfLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize, long total) {
		self=createLink(parentLink, pageStartIndex, pageSize, LINK_REL_SELF);
		this.total = total;
	}
	
	protected void createCreateLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		create=createLink(parentLink.slash(CREATE_ACTION.getActionName()), pageStartIndex, pageSize, LINK_REL_CREATE);
	}
	
	protected void createShowAllLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		showAll=createLink(parentLink.slash(SHOW_ALL_ACTION.getActionName()), pageStartIndex, pageSize, LINK_REL_SHOWALL);
	}
	
	protected void createFirstPageLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		firstPage=createLink(parentLink, pageStartIndex, pageSize, LINK_FIRST_PAGE);
	}	
	
	protected void createNextPageLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		nextPage=createLink(parentLink, pageStartIndex, pageSize, LINK_NEXT_PAGE);
	}	
	
	protected void createPrevtPageLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		prevPage=createLink(parentLink, pageStartIndex, pageSize, LINK_PREV_PAGE);
	}	
	
	protected void createLastPageLink(ControllerLinkBuilder parentLink, String pageStartIndex, String pageSize) {
		lastPage = createLink(parentLink, pageStartIndex, pageSize, LINK_LAST_PAGE);
	}	
	
	private Link createLink(ControllerLinkBuilder parentLink,
			String pageStartIndex, String pageSize, String linkRel) {
		String url;
		if (pageStartIndex != null && pageSize != null) {
			url = parentLink.toUriComponentsBuilder().queryParam(PAGE_START_INDEX, pageStartIndex)
				.queryParam(PAGE_SIZE, pageSize).build().toUriString();
		} else {
			url = parentLink.toUri().toString();
		}
		return new Link(url, linkRel);
		
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}	
}
