package com.atex.milan.svnuser.servlet.resources.exceptions;


public class AlreadyMemberException extends GenericWebMethodException
{
  private static final long serialVersionUID = 1L;
  
  public AlreadyMemberException(String username)
  {
    super("user " + username + " is already member of this group");
  }

}
