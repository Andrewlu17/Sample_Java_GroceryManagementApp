package com.grocerymanagement.exceptions;

public class DAOException extends ServiceException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOException()
	{
		super();
	}
	public DAOException(String message)
	{
		super(message);
	}
}
