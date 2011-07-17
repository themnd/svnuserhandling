package com.atex.milan.svnuser.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.users.UserInfo;

/**
 *
 * @author mnova
 */
public class DisableUserServlet
        extends BaseJSONServlet
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  protected void processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject obj)
          throws ServletException,
                 IOException
  {
      SvnApp app = SvnApp.getInstance();
      String login = request.getParameter("login");
      UserInfo u = app.getUser(login);
      if (u == null) {
        response.setStatus(404);
      } else {
        if (u.isDisabled()) {
          obj.put("msg", "already disabled");
          response.setStatus(200);
        } else {
          obj.put("msg", "user has been disabled");
          u.setDisabled(true);
          app.writePasswd();
        }
      }
  }
}
