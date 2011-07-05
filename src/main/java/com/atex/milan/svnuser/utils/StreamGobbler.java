/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnova
 */
class StreamGobbler extends Thread
{
  private static final Logger logger = Logger.getLogger(StreamGobbler.class.getName());

  InputStream is;
  String type;
  Level logLevel = Level.INFO;
  StringBuilder lines;
  private boolean interruptor = false;

  StreamGobbler(InputStream is, String type)
  {
    this.is = is;
    this.type = type;
  } // StreamGobbler

  public void setLogLevel(Level level)
  {
    this.logLevel = level;
  } // setLogLevel

  @Override
  public void run()
  {
    try
    {
      lines = new StringBuilder();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line=null;
      while ( (line = br.readLine()) != null) {
        logger.log(this.logLevel, "{0}>{1}", new Object[]{type, line});
        lines.append(line);
      }
    } catch (IOException e)
    {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  } // run

  public String getLines()
  {
    if (lines == null) {
      return null;
    }
    return lines.toString();
  }

} // StreamGobbler
