/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.servlet;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.users.UserInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author mnova
 */
public class DisableUserServlet
        extends BaseJSONServlet
{
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
