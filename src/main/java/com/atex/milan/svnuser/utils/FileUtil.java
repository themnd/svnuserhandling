/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mnova
 */
public class FileUtil
{
  private static final String NEW_LINE = "\n";

  /**
   * Gets the text from the URL resource.
   * <p/>
   *
   * @param path the resource's file path
   * @return the resource's text
   * @throws java.io.IOException if the resource was not found
   */
  public static String getFileResourceText(String path) throws IOException
  {
    BufferedReader fileReader = null;
    StringBuilder stringBuilder = new StringBuilder();
    try {
      fileReader = new BufferedReader(new FileReader(path));
      String line = null;
      while ((line = fileReader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append(NEW_LINE);
      }
    } finally {
      if (fileReader != null) {
        fileReader.close();
      }
    }
    return stringBuilder.toString();
  }

  public static void setFileResourceText(String path, String content) throws IOException
  {
    BufferedWriter fileWriter = null;
    try {
      fileWriter = new BufferedWriter(new FileWriter(path));
      fileWriter.write(content);
    } finally {
      if (fileWriter != null) {
        fileWriter.close();
      }
    }
  }
}
