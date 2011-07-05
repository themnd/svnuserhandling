/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnova
 */
public class ToolExecutor  extends Thread {

  private static final Logger logger = Logger.getLogger(ToolExecutor.class.getName());

  private int m_timeout;
  private List<String> parameters;
  private String m_err = "";
  private String m_out = "";

  private Level err_logLevel = Level.INFO;
  private Level out_logLevel = Level.INFO;

  private String output;

  public ToolExecutor(String toolExecutable, String err, String out, String... params)
  {
    parameters = new ArrayList<String>();
    String[] parts = toolExecutable.split(" ");
    if (parts == null) {
      addParameter(toolExecutable);
    } else {
      for (int idx = 0; idx < parts.length; idx++) {
        addParameter(parts[idx]);
      }
    }
    // by default the timeout is 30 seconds;
    m_timeout = 30;
    for (String p: params) {
      addParameter(p);
    }
    m_err = err;
    m_out = out;
  }

  public void setTimeout(int timeout)
  {
    m_timeout = timeout;
  } // setTimeout

  public void setErrLogLevel(Level level)
  {
    err_logLevel = level;
  } // setErrLogLevel

  public void setOutLogLevel(Level level)
  {
    out_logLevel = level;
  } // setOutLogLevel

  public String getOutput()
  {
    return output;
  }

  private void addParameter(String param)
  {
    if (param != null && param.length() > 0) {
      parameters.add(param);
    }
  }

  @Override
  public  void run()
  {
    Date t1 = new Date();

    try
    {
      String[] params = parameters.toArray(new String[parameters.size()]);

      String cmd = "";
      for (String p: params) {
        cmd += p + " ";
      }
      log("Comand: " + cmd);

      Runtime rt = Runtime.getRuntime();
      Process proc = rt.exec(params);
      StreamGobbler errorGlobber = new StreamGobbler(proc.getErrorStream(), m_err);
      StreamGobbler outputGlobber = new StreamGobbler(proc.getInputStream(), m_out);
      errorGlobber.setLogLevel(err_logLevel);
      outputGlobber.setLogLevel(out_logLevel);
      errorGlobber.start();
      outputGlobber.start();
      ControllerThread cThread = new ControllerThread(proc, m_timeout); //process and timeout
      cThread.start();
      proc.waitFor();
      outputGlobber.join();
      errorGlobber.join();

      cThread.clean();

      output = outputGlobber.getLines();
    }
    catch (IOException e)
    {
      log(Level.SEVERE, e.getMessage(), e);
    } catch (InterruptedException e) {
      log(Level.SEVERE, e.getMessage(), e);
    } catch (Exception e) {
      log(Level.SEVERE, e.getMessage(), e);
    }
    Date t2 = new Date();
    log("command execution terminated, completed in " + (t2.getTime() - t1.getTime()) + " ms");
  } // run

  public void waitExecutionEnd()
  {
    try {
      while (isAlive()) {
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      logger.log(Level.WARNING, e.getMessage(), e);
      throw new IllegalArgumentException(e);
    }
  }

  private static void log(String msg)
  {
    log(Level.INFO, msg);
  } // log

  private static void log(Level level, String msg)
  {
    logger.log(level, "Thread: {0} - {1}", new Object[]{Thread.currentThread().getId(), msg});
  } // log

  private static void log(Level level, String msg, Throwable thrown)
  {
    logger.log(level, "Thread: "+Thread.currentThread().getId() + " - " + msg, thrown);
  } // log

}
