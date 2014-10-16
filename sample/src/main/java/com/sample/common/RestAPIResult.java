package com.sample.common;

public class RestAPIResult {
	public static final int SUCCESS = 0;
	public static final int FAILED = -1;
	public static final String SUCCESS_STATUS = "Success";	
	public static final String FAILURE_STATUS = "Failure";		
	public static final String DUPLICATE_ENTITY = "Duplicate entity";
	public static final String CREATE_FAILURE = "Create Failure";	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public RestAPIResult(int error, String description) {
		this.description = description;
		this.error = error;
	}
	private String description;
	private int error;
}
