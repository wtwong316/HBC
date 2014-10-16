package com.sample.common;

public enum RestAction {
	CREATE_ACTION("add"), SHOW_ALL_ACTION("show"), NULL("");	
	private RestAction(String actionName) {
		this.setActionName(actionName);
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	private String actionName;
}
