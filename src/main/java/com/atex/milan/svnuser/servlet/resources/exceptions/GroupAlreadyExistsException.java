package com.atex.milan.svnuser.servlet.resources.exceptions;


public class GroupAlreadyExistsException extends GenericWebMethodException
{
  private static final long serialVersionUID = 1L;
  
  public GroupAlreadyExistsException(String name)
  {
    super("group " + name + " already exists");
  }

}
