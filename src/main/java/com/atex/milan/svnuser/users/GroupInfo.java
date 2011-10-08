package com.atex.milan.svnuser.users;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo
{
  private String name;
  private boolean disabled;
  private boolean modified;
  private List<String> members;
  
  public GroupInfo()
  {
    this.disabled = false;
    this.modified = false;
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
  public boolean isModified()
  {
    return modified;
  }
  public void setModified(boolean modified)
  {
    this.modified = modified;
  }
  public void addMember(String user)
  {
    synchronized(members) {
      members.add(user);
      setModified(true);
    }
  }
  public void delMember(String user)
  {
    synchronized(members) {
      for (String m: members) {
        if (m.equals(user)) {
          setModified(true);
          members.remove(m);
          break;
        }
      }
    }
  }
  public List<String> getMembers()
  {
    return members;
  }

}
