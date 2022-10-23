package com.backend.blogBackend.Exception.Custom;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorDescription;
	
	public ControllerException(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public ControllerException() {
		super();
		
	}

	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
