/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

/**
 *
 * @author mnova
 */
public class HTPasswdTool extends ToolExecutor
{
  public HTPasswdTool(String toolExecutable, String passwdFile, String userName, String password)
  {
    super(toolExecutable, "HTPWD-E", "HTPWD", "-b", passwdFile, userName, password);
  }

}
