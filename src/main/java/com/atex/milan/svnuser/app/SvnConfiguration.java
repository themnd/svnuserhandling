package com.atex.milan.svnuser.app;

import java.util.Properties;

/**
 *
 * @author mnova
 */
public class SvnConfiguration
{
  private static final String AUTHZ_PARAM = "authz";
  private static final String PASSWD_PARAM = "passwd";
  private static final String TOOLCMD_PARAM = "toolcmd";
  private static final String HTPASSWDCMD_PARAM = "htpasswdcmd";

  private String pwgenCmd;
  private String htpasswdCmd;
  private String passwd;
  private String authz;

  public SvnConfiguration()
  {
  }
  
  public SvnConfiguration(Properties properties)
  {
    String toolCmd = properties.getProperty(TOOLCMD_PARAM);
    String passwd = properties.getProperty(PASSWD_PARAM);
    String authz = properties.getProperty(AUTHZ_PARAM);
    String htpasswdCmd = properties.getProperty(HTPASSWDCMD_PARAM);

    setPwgenCmd(toolCmd);
    setAuthz(authz);
    setPasswd(passwd);
    setHtpasswdCmd(htpasswdCmd);
  }
  
  /**
   * @return the pwgenCmd
   */
  public String getPwgenCmd()
  {
    return pwgenCmd;
  }

  /**
   * @param pwgenCmd the pwgenCmd to set
   */
  public void setPwgenCmd(String pwgenCmd)
  {
    this.pwgenCmd = pwgenCmd;
  }

  /**
   * @return the htpasswdCmd
   */
  public String getHtpasswdCmd()
  {
    return htpasswdCmd;
  }

  /**
   * @param htpasswdCmd the htpasswdCmd to set
   */
  public void setHtpasswdCmd(String htpasswdCmd)
  {
    this.htpasswdCmd = htpasswdCmd;
  }

  /**
   * @return the passwd
   */
  public String getPasswd()
  {
    return passwd;
  }

  /**
   * @param passwd the passwd to set
   */
  public void setPasswd(String passwd)
  {
    this.passwd = passwd;
  }

  /**
   * @return the authz
   */
  public String getAuthz()
  {
    return authz;
  }

  /**
   * @param authz the authz to set
   */
  public void setAuthz(String authz)
  {
    this.authz = authz;
  }
}
