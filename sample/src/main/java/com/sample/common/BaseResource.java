package com.sample.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public abstract class BaseResource <T extends BaseResourceLinks> {
	private T links;

	public T getLinks() {
		return links;
	}

	public void setLinks(T links) {
		this.links = links;
	} 

}
