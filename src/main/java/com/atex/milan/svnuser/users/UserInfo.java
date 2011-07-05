/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.users;

/**
 *
 * @author mnova
 */
public class UserInfo
{
  private String login;
  private String password;
  private boolean disabled;

  public void UserInfo()
  {
    this.disabled = false;
  }
  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the disabled
   */
  public boolean isDisabled() {
    return disabled;
  }

  /**
   * @param disabled the disabled to set
   */
  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  
}
