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

public class AddUserServlet extends BaseJSONServlet
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  @Override
  protected void processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject obj)
      throws ServletException, IOException
  {
    SvnApp app = SvnApp.getInstance();
    SvnConfiguration config = app.getConfig();
    String login = request.getParameter("login");
    if (app.getUser(login) != null) {
      obj.put("msg", "user " + login + " already exists!");
    } else {
      logger.log(Level.INFO, "Generating password for {0}", login);
      PWGenTool pwgen_tool = new PWGenTool(config.getPwgenCmd());
      pwgen_tool.start();
      pwgen_tool.waitExecutionEnd();
      String newPwd = pwgen_tool.getPassword();
      logger.info("New password is [" + newPwd + "]");
      if (newPwd == null || newPwd.length() == 0) {
        throw new IllegalArgumentException("Cannot create new password");
      }
      HTPasswdTool htpasswd_tool = new HTPasswdTool(config.getHtpasswdCmd(), config.getPasswd(), login, newPwd);
      htpasswd_tool.start();
      htpasswd_tool.waitExecutionEnd();
      obj.put("msg", "user " + login + " has been created with password " + newPwd);
      app.init();
    }
  }

}
