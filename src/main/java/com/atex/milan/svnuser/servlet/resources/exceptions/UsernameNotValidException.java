package com.atex.milan.svnuser.servlet.resources.exceptions;


public class UsernameNotValidException extends GenericWebMethodException
{
  private static final long serialVersionUID = 1L;
  
  public UsernameNotValidException(String username)
  {
    super("username " + username + " is not a valid username");
  }
}
