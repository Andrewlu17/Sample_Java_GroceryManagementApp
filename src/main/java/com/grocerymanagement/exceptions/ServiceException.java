package com.grocerymanagement.exceptions;

import com.grocerymanagement.exceptions.RequestManagerException;

public class ServiceException extends RequestManagerException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServiceException()
	{
		super();
	}
	public ServiceException(String message)
	{
		super(message);
	}
}
