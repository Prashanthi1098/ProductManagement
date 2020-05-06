package com.project.loginservice.exception;

public class SellerNotFoundException extends RuntimeException
{
	public SellerNotFoundException(String name)
	{
	super("could not found User " +name);
}
}
