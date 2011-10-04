package com.atex.milan.svnuser.servlet.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.GroupInfo;

@Path("/groups")
public class GroupsResource
{
  @Context UriInfo uriInfo;
  @Context Request request;
  
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<GroupInfo> getGroups()
  {
    SvnApp app = SvnApp.getInstance();
    AuthInfo info = app.getInfo();
    List<GroupInfo> groups = info.getGroups();
    return groups;
  }

  @Path("{groupname}")
  public GroupResource getGroupResource(@PathParam("groupname") String groupname)
  {
    return new GroupResource(uriInfo, request, groupname);
  }
}
