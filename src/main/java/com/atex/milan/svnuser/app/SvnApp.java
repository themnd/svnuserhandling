/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.app;

import com.atex.milan.svnuser.utils.parser.PasswdParser;
import com.atex.milan.svnuser.users.UserInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mnova
 */
public class SvnApp
{
  static private SvnApp instance = new SvnApp();

  static public SvnApp getInstance()
  {
    return instance;
  }

  protected SvnConfiguration config;
  protected List<UserInfo> users = null;

  public void init(SvnConfiguration config) throws IOException
  {
    setConfig(config);
    init();
  }

  public void init() throws IOException
  {
    File f = new File(config.getPasswd());
    users = PasswdParser.parse(f);
  }

  public void setConfig(SvnConfiguration config)
  {
    this.config = config;
  }
  
  public SvnConfiguration getConfig()
  {
    return config;
  }

  public List<UserInfo> getUsers()
  {
    return users;
  }

  public UserInfo getUser(String login)
  {
    for (UserInfo u: users) {
      String name = u.getLogin();
      if (name == null) {
        continue;
      }
      if (name.equals(login)) {
        return u;
      }
    }
    return null;
  }

  public void writePasswd() throws IOException
  {
    File f = new File(config.getPasswd());
    PasswdParser.write(f, users);
  }

}
