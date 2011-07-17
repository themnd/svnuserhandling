package com.atex.milan.svnuser.servlet.listener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.atex.milan.svnuser.app.SvnApp;
import com.atex.milan.svnuser.app.SvnConfiguration;

public class AppContextListener implements ServletContextListener
{
  protected static final Logger logger = Logger.getLogger(AppContextListener.class.getName());
  
  @Override
  public void contextInitialized(ServletContextEvent event)
  {
    String configuration = System.getProperty("SVNUSERS_CONF");
    logger.config("Configuration can be found in " + configuration);
    
    Properties properties = loadConfigurationProperties(configuration);
    if (properties != null) {
      try {
        SvnApp app = SvnApp.getInstance();
        SvnConfiguration svn_config = new SvnConfiguration(properties);
        app.init(svn_config);
      } catch (IOException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event)
  {
  }

  protected Properties loadConfigurationProperties(String configuration)
  {
    try {
      InputStreamReader reader = new FileReader(configuration);
      Properties prop = new Properties();
      prop.load(reader);
      return prop;
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return null;
  }

}
