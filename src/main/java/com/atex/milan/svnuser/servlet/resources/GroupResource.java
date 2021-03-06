package com.atex.milan.svnuser.servlet.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.servlet.resources.exceptions.AlreadyMemberException;
import com.atex.milan.svnuser.servlet.resources.exceptions.NotMemberException;
import com.atex.milan.svnuser.servlet.resources.exceptions.UsernameNotValidException;
import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.GroupInfo;
import com.atex.milan.svnuser.users.UserInfo;

@Produces({MediaType.APPLICATION_JSON})
public class GroupResource
{
  protected static final Logger logger = Logger.getLogger(GroupResource.class.getName());
  
  @Context UriInfo uriInfo;
  @Context Request request;
  GroupInfo group;

  GroupResource(UriInfo uriInfo, Request request, String groupname)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    
    SvnApp app = SvnApp.getInstance();
    AuthInfo info = app.getInfo();
    List<GroupInfo> groups = info.getGroups();
    for (GroupInfo g: groups) {
      if (g.getName().equals(groupname)) {
        group = g;
        break;
      }
    }
  }
  
  @GET
  public GroupInfo getGroupInfo()
  {
    return group;
  }
  
  @POST
  @Path("addmember/{username}")
  public GroupInfo doAddMember(@PathParam("username") String username)
  {
    logger.info("doAddMember " + username);
    
    String[] names = username.split(",");
    for (String name: names) {
      addMember(name);
    }
    return group;      
  }

  @POST
  @Path("delmember/{username}")
  public GroupInfo doDelMember(@PathParam("username") String username)
  {
    logger.info("doDelMember " + username);
    
    if (!group.getMembers().contains(username)) {
      throw new NotMemberException(username);
    }
    UserInfo u = getUser(username);
    if (u != null) {
      group.delMember(username);
      return group;      
    }
    throw new UsernameNotValidException(username);
  }
  
  protected void addMember(String username)
  {
    logger.info("addMember " + username);
    
    if (group.getMembers().contains(username)) {
      throw new AlreadyMemberException(username);
    }
    UserInfo u = getUser(username);
    if (u != null) {
      group.addMember(username);
    } else {
      throw new UsernameNotValidException(username);
    }
  }
  
  protected UserInfo getUser(String username)
  {
    SvnApp app = SvnApp.getInstance();
    AuthInfo info = app.getInfo();
    List<UserInfo> users = info.getUsers();
    for (UserInfo u: users) {
      if (u.getLogin().equals(username)) {
        return u;
      }
    }
    return null;
  }

}
