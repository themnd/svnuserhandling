package com.atex.milan.svnuser.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.UserInfo;
import com.atex.milan.svnuser.utils.parser.AuthzParser;
import com.atex.milan.svnuser.utils.parser.PasswdParser;

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
  protected AuthInfo info = null;

  public void init(SvnConfiguration config) throws IOException
  {
    setConfig(config);
    init();
  }

  public void init() throws IOException
  {
    File a = new File(config.getAuthz());
    info = AuthzParser.parse(a);
    
    File p = new File(config.getPasswd());
    info.setUsers(PasswdParser.parse(p));
  }

  public void setConfig(SvnConfiguration config)
  {
    this.config = config;
  }
  
  public SvnConfiguration getConfig()
  {
    return config;
  }

  public AuthInfo getInfo()
  {
    return info;
  }

  public List<UserInfo> getUsers()
  {
    return info.getUsers();
  }
  
  public UserInfo getUser(String login)
  {
    for (UserInfo u: getUsers()) {
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
    PasswdParser.write(f, getUsers());
  }

}
