package com.almundo.callcenter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.almundo.callcenter.dispatcher.Dispatcher;

/**
 * It allows executing the execution example for a callcenter of 10 operators that handle 20 calls. 
 * The calls are answered by operators according to the priority that each one has. 
 * For that you have to start the callcenter, make the calls and turn it off
 * 
 * @author  Gonzalo Bordoy
 * @see     java.util.concurrent.ExcecutorService
 * @see     com.almundo.callcenter.Dispatcher
 */
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	public static void main(String[] args) {

		Dispatcher dispatcher=new Dispatcher();
		dispatcher.init();
		
		for(int i=0; i<20; i++){
			dispatcher.dispatchCall();
			try {
				//Wait a moment to show the order of priority in the execution
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.debug(e);
			}
		}
		dispatcher.shutdown();
	}


}
