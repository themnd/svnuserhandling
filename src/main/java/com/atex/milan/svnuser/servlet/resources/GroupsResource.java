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
import com.atex.milan.svnuser.servlet.resources.exceptions.GroupAlreadyExistsException;
import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.GroupInfo;

@Path("/groups")
@Produces({MediaType.APPLICATION_JSON})
public class GroupsResource
{
  protected static final Logger logger = Logger.getLogger(GroupsResource.class.getName());
  
  @Context UriInfo uriInfo;
  @Context Request request;
  
  @GET
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
  
  @POST
  @Path("addgroup/{groupname}")
  public GroupInfo doAddGroup(@PathParam("groupname") String groupname)
  {
    logger.info("doAddGroup " + groupname);
    
    groupname = groupname.trim().toLowerCase();
    
    SvnApp app = SvnApp.getInstance();
    AuthInfo info = app.getInfo();
    List<GroupInfo> groups = info.getGroups();
    for (GroupInfo g: groups) {
      if (g.getName().equals(groupname)) {
        throw new GroupAlreadyExistsException(groupname);
      }
    }
    
    GroupInfo g = new GroupInfo();
    g.setName(groupname);
    g.setModified(true);
    
    groups.add(g);
    return g;
  }
}
