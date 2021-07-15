package com.grocerymanagement.exceptions;

public class RequestManagerException extends GroceryHandlerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RequestManagerException()
	{
		super();
	}
	public RequestManagerException(String message)
	{
		super(message);
	}
}
