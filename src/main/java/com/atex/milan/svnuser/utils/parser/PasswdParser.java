/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils.parser;

import com.atex.milan.svnuser.users.UserInfo;
import java.io.File;
import java.io.IOException;

import com.atex.milan.svnuser.utils.FileUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnova
 */
public class PasswdParser
{
  protected static final String COMMENT_SIGN = "#";
  protected static final char PWD_SEPARATOR = ':';
  protected static final String USER_SEPARATOR = "\n";

  static public List<UserInfo> parse(File f) throws IOException
  {
    List<UserInfo> users = new ArrayList<UserInfo>();
    String content = FileUtil.getFileResourceText(f.getAbsolutePath());
    String[] lines = content.split(USER_SEPARATOR);
    for (String line: lines) {
      if (line.startsWith(COMMENT_SIGN)) {
        UserInfo u = getUserInfo(line.substring(1));
        u.setDisabled(true);
        users.add(u);
      } else {
        UserInfo u = getUserInfo(line);
        users.add(u);
      }
    }
    return users;
  }

  static public void write(File f, List<UserInfo> users) throws IOException
  {
    StringBuilder content = new StringBuilder();
    for (UserInfo u: users) {
      if (u.isDisabled()) {
        content.append(COMMENT_SIGN);
      }
      content.append(u.getLogin());
      content.append(PWD_SEPARATOR);
      content.append(u.getPassword());
      content.append(USER_SEPARATOR);
    }
    FileUtil.setFileResourceText(f.getAbsolutePath(), content.toString());
  }

  static private UserInfo getUserInfo(String line)
  {
    int idx = line.indexOf(PWD_SEPARATOR);
    String login = line.substring(0, idx);
    String pwd = line.substring(idx + 1);
    UserInfo u = new UserInfo();
    u.setLogin(login);
    u.setPassword(pwd);
    return u;
  }

}
