/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.milan.svnuser.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnova
 */
public class ControllerThread extends Thread{

  private static final Logger logger = Logger.getLogger(ControllerThread.class.getName());

	private long timeOut = 0 ;
	private Process processToControll = null;
	private boolean interruptor = false;

	public ControllerThread(Process proc, long timeOut)
	{
		this.processToControll = proc;
		this.timeOut = timeOut;
	}

	@Override
	public void run()
	{
		try
		{
			while(timeOut!=0)
			{
				if(interruptor) {
					break;
				}

				sleep(1000);
				timeOut--;
				if ((timeOut % 10) == 0) {
				  logger.info(getTID() + "waiting for max " + timeOut + " seconds");
				} else {
				  logger.fine(getTID() + "waiting for max " + timeOut + " seconds");
				}
			}

			if (timeOut == 0) {
			  logger.warning(getTID() + "the external process will be killed!");
			}

			//if timeout or process end the external tool process must destroyed
			processToControll.destroy();
		}
		catch (InterruptedException e)
		{
		  logger.log(Level.SEVERE, getTID() + e.getMessage(), e);
		}
	} // run

	public void clean()
	{
		interruptor = true;
	} // clean

  private String getTID()
  {
    return "Thread: "+Thread.currentThread().getId() + " - ";
  } // getTID
}
