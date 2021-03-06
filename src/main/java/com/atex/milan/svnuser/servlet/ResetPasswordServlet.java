package com.atex.milan.svnuser.servlet;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.app.SvnConfiguration;
import com.atex.milan.svnuser.users.UserInfo;
import com.atex.milan.svnuser.utils.HTPasswdTool;
import com.atex.milan.svnuser.utils.PWGenTool;

/**
 *
 * @author mnova
 */
public class ResetPasswordServlet
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
    SvnConfiguration config = app.getConfig();
    String login = request.getParameter("login");
    UserInfo u = app.getUser(login);
    if (u == null) {
      response.setStatus(404);
    } else {
      if (u.isDisabled()) {
        obj.put("msg", "user is not enabled");
        response.setStatus(200);
      } else {
        logger.log(Level.INFO, "Generating password for {0}", u.getLogin());
        PWGenTool pwgen_tool = new PWGenTool(config.getPwgenCmd());
        pwgen_tool.start();
        pwgen_tool.waitExecutionEnd();
        String newPwd = pwgen_tool.getPassword();
        logger.info("New password is [" + newPwd + "]");
        if (newPwd == null || newPwd.length() == 0) {
          throw new IllegalArgumentException("Cannot create new password");
        }
        u.setPassword(newPwd);
        HTPasswdTool htpasswd_tool = new HTPasswdTool(config.getHtpasswdCmd(), config.getPasswd(), u.getLogin(), u.getPassword());
        htpasswd_tool.start();
        htpasswd_tool.waitExecutionEnd();
        obj.put("msg", "password has been changed to " + newPwd);
        app.init();
      }
    }
  }
}
