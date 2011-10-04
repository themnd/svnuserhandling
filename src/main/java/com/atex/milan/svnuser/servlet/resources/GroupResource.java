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
import com.atex.milan.svnuser.users.GroupInfo;

@Produces({MediaType.APPLICATION_JSON})
public class GroupResource
{
  @Context UriInfo uriInfo;
  @Context Request request;
  String groupname;

  GroupResource(UriInfo uriInfo, Request request, String groupname)
  {
    this.uriInfo = uriInfo;
    this.request = request;
    this.groupname = groupname;
  }
  
  @GET
  public GroupInfo getGroupInfo()
  {
    SvnApp app = SvnApp.getInstance();
    AuthInfo info = app.getInfo();
    List<GroupInfo> groups = info.getGroups();
    for (GroupInfo g: groups) {
      if (g.getName().equals(groupname)) {
        return g;
      }
    }
    return null;
  }

}
