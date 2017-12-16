package com.mart.dto;

import java.io.Serializable;

public class ErrorMsg implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String errorMessage;
	
	public ErrorMsg(){}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
