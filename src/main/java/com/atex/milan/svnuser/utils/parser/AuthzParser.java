package com.atex.milan.svnuser.utils.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.atex.milan.svnuser.users.AuthInfo;
import com.atex.milan.svnuser.users.GroupInfo;
import com.atex.milan.svnuser.utils.FileUtil;

/**
 * 
 * @author mnova
 */
public class AuthzParser
{
  protected static final Logger logger = Logger.getLogger(AuthzParser.class.getName());
  
  private static final String COMMENT_SIGN = "#";
  private static final String LINE_SEPARATOR = "\n";
  private static final String SECTION_START = "[";
  private static final String SECTION_END = "]";
  private static final String ALIASES_SECTION = "aliases";
  private static final String GROUPS_SECTION = "groups";
  private static final String GROUP_SEPARATOR = "=";
  private static final String USER_SEPARATOR = ",";

  static public AuthInfo parse(File f) throws IOException
  {
    AuthInfo info = new AuthInfo();
    List<GroupInfo> groups = info.getGroups();
    String content = FileUtil.getFileResourceText(f.getAbsolutePath());
    String[] lines = content.split(LINE_SEPARATOR);
    String currentSectionName = "";
    for (String line : lines) {
      if (line == null) {
        continue;
      }
      String trimmedLine = line.trim();
      if (trimmedLine.startsWith(COMMENT_SIGN)) {
        continue;
      }
      if (trimmedLine.length() == 0) {
        continue;
      }
      if (trimmedLine.startsWith(SECTION_START) && trimmedLine.endsWith(SECTION_END)) {
        currentSectionName = getSectionName(trimmedLine);
        continue;
      }
      if (currentSectionName.equals(GROUPS_SECTION)) {
        GroupInfo g = getGroup(trimmedLine);
        if (g != null) {
          groups.add(g);
        }
      }
    }
    return info;
  }

  private static String getSectionName(String line)
  {
    return line.substring(1, line.length() - 1);
  }
  
  private static GroupInfo getGroup(String line)
  {
    String[] parts = line.split(GROUP_SEPARATOR);
    if (parts.length > 0) {
      String name = parts[0].trim();
      if (name.length() > 0) {
        GroupInfo g = new GroupInfo();
        g.setName(name);
        if (parts.length > 1) {
          String[] members = parts[1].trim().split(USER_SEPARATOR);
          for (String member: members) {
            g.addMember(member.trim());
          }
        }
        return g;
      }
    }
    return null;
  }
  
}
