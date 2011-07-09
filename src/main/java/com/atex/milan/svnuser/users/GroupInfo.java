package com.atex.milan.svnuser.users;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo
{
  private String name;
  private boolean disabled;
  private List<String> members;
  
  public GroupInfo()
  {
    this.disabled = false;
    this.members = new ArrayList<String>();
  }
  
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public boolean isDisabled()
  {
    return disabled;
  }
  public void setDisabled(boolean disabled)
  {
    this.disabled = disabled;
  }
  public void addMember(String user)
  {
    members.add(user);
  }
  public List<String> getMembers()
  {
    return members;
  }

}
