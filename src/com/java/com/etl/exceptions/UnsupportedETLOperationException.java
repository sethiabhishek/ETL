package com.etl.exceptions;

import com.etl.commons.ErrorCodes;

/**
 * Custom Exception for handling exception scenario,
 * more custom exceptions can be created afterwords
 * @author aseth7
 *
 */
public class UnsupportedETLOperationException extends Exception{

	private static final long serialVersionUID = 10L;
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private String errorMessage;
	
	public UnsupportedETLOperationException() {
		super(ErrorCodes.errorMessage);
		this.errorCode = ErrorCodes.errorCode;
	}
	
	

}
