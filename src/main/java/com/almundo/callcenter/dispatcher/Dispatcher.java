package com.almundo.callcenter.dispatcher;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.almundo.callcenter.model.TakeCall;

/**
 * The <code>Dispatcher</code> the call center calls to assign them to some ,
 * operator. In case there are no operators available the calls go to a 
 * queue where they will be taken care of
 * 
 * @author  Gonzalo Bordoy
 * @see     java.util.concurrent.ThreadPoolExecutor
 * @see     com.almundo.callcenter.RejectedExcecutionHandlerCallCenter
 */
public class Dispatcher {
	private final Logger logger = LogManager.getLogger(getClass());
	private RejectedExcecutionHandlerCallCenter rehcc=new RejectedExcecutionHandlerCallCenter();
	private ThreadPoolExecutor executor=null;
	
	/**
	 * Initializes the executor with ten threads from the object <code>CallCenterThreadFactory</code>
	 */
	public void init(){	
		if(executor==null || executor.isShutdown()){
			executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(10, new CallCenterThreadFactory());
			executor.setRejectedExecutionHandler(rehcc);	
		}
	}
	/**
	 * Shutdown the executor waiting for finalize all threads
	 */
	public void shutdown(){
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			logger.debug(e);
		}
	}
	/**
	 * Dispatch one call
	 */
	public void dispatchCall(){
		TakeCall tc=new TakeCall();
		executor.execute(tc);
		if(!executor.isShutdown() && !executor.getQueue().isEmpty()){
			logger.debug("No podemos atender en este momento la llamada " + tc.getCallId() + " aguarde un instante");
		}
	}
	
}
