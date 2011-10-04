package com.atex.milan.svnuser.servlet.resources.exceptions;


public class NotMemberException extends GenericWebMethodException
{
  private static final long serialVersionUID = 1L;
  
  public NotMemberException(String username)
  {
    super("user " + username + " is not a member of this group");
  }

}
