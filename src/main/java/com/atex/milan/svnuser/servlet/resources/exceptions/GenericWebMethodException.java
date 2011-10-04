package com.atex.milan.svnuser.servlet.resources.exceptions;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class GenericWebMethodException extends WebApplicationException
{
  private static final long serialVersionUID = 1L;
  private List<String> errors;

  public GenericWebMethodException(String... errors)
  {
      this(Arrays.asList(errors));
  }

  public GenericWebMethodException(List<String> errors)
  {
      super(Response.status(Status.BAD_REQUEST).entity(errors).build());
      this.errors = errors;
  }

  public List<String> getErrors()
  {
      return errors;
  }
}
