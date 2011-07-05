/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.app;

/**
 *
 * @author mnova
 */
public class SvnConfiguration
{

  private String pwgenCmd;
  private String htpasswdCmd;
  private String passwd;
  private String authz;

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
