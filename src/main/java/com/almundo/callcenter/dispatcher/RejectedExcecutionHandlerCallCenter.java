package com.almundo.callcenter.dispatcher;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.almundo.callcenter.model.TakeCall;
/**
 * The <code>RejectedExcecutionHandlerCallCenter</code> is an implementation of <code>RejectedExcecutionHandler</code>,
 * allows to handle a rejected execution.
 * In case the ExecutorService is shutdown, the execution is lost, otherwise the execution goes to the thread queue
 * 
 * @author  Gonzalo Bordoy
 * @see     com.almundo.callcenter.Dispatcher
 * @see     com.almundo.callcenter.TakeCall
 */
public class RejectedExcecutionHandlerCallCenter implements RejectedExecutionHandler {
	private final Logger logger = LogManager.getLogger(getClass());
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		try {
			if (!executor.isShutdown()) {
				//If the callcenter is available, the call goes to the queue
				executor.getQueue().put(r);
				logger.debug("La llamada " + ((TakeCall)r).getCallId() + " fue rechazada, se incorpora a la cola de llamadas en espera");
            }else{
            	//If the callcenter is not available, the call is lost
            	logger.debug("La llamada " + ((TakeCall)r).getCallId() + " fue rechazada y el call center no puede recibir llamadas");
            }
		} catch (InterruptedException e) {
			logger.debug(e);
		}
	}

}
