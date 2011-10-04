package com.atex.milan.svnuser.servlet.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.UserInfo;

@Produces({MediaType.APPLICATION_JSON})
public class UserResource
{
  @Context UriInfo uriInfo;
  @Context Request request;
  String username;

  UserResource(UriInfo uriInfo, Request request, String username)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.username = username;
  }

  @GET
  public UserInfo getUserInfo()
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
