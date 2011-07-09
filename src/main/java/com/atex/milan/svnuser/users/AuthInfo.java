package com.atex.milan.svnuser.users;

import java.util.ArrayList;
import java.util.List;

public class AuthInfo
{
  private List<UserInfo> users;
  private List<GroupInfo> groups;
  
  public AuthInfo()
  {
    users = new ArrayList<UserInfo>();
    groups = new ArrayList<GroupInfo>();
  }
  
  public void setUsers(List<UserInfo> users)
  {
    this.users = users;
  }
  
  public List<UserInfo> getUsers()
  {
    return users;
  }

  public void setGroups(List<GroupInfo> groups)
  {
    this.groups = groups;
  }
  
  public List<GroupInfo> getGroups()
  {
    return groups;
  }

}
