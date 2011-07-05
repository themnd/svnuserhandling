/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

/**
 *
 * @author mnova
 */
public class PWGenTool extends ToolExecutor
{
  public PWGenTool(String toolExecutable, String... params)
  {
    super(toolExecutable, "PWGEN-E", "PWGEN-O", params);
  }

  public String getPassword()
  {
    String out = super.getOutput();
    if (out != null) {
      return out.trim();
    }
    return null;
  }
}
