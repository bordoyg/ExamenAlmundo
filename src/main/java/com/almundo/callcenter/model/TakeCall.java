package com.almundo.callcenter.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The <code>TakeCall</code> is implementation of <code>Runnable</code> 
 * this class contains the logic to answer a call, each instance represents a call
 * 
 * @author  Gonzalo Bordoy
 */
public class TakeCall implements Runnable{
	protected static int callCount=0;
	/* identify each call. */
	private final int callId;
	private final Logger logger = LogManager.getLogger(getClass());
	
	public TakeCall(){
		callId=callCount++;
	}
	
	/**
	 * Method that is invoked when a call can be answered. Each call takes between five and ten seconds
	 */
	@Override
	public void run() {
		logger.debug("llamada " +  callId + " atendida por " + Thread.currentThread().getName());
		int numero = (int)(Math.random()*10 + 5) *1000;
		try {
			Thread.sleep(numero);
		} catch (InterruptedException e) {
			logger.debug(e);
		}
		
		logger.debug("llamada " +  callId + " terminada por " + Thread.currentThread().getName());
		
	}
	
	public int getCallId(){
		return callId;
	}
}
