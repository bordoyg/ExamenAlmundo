package com.almundo.callcenter.dispatcher;

import java.util.concurrent.ThreadFactory;

/**
 * The <code>CallCenterThreadFactory</code> creates ten threads in total, with the following 
 * execution priority: five of them represent Operators, three Supervisors and two Directors.
 * The priority of execution is not defined synchronization
 * 
 * @author  Gonzalo Bordoy
 * @see     java.util.concurrent.ExcecutorService
 * @see     com.almundo.callcenter.Dispatcher
 */
public class CallCenterThreadFactory implements ThreadFactory {
	private static final int MAX_DIRECTOR = 2;
	private static final int MAX_SUPERVISOR = 3;
	private static final int MAX_OPERATOR = 5;
	
	private int countDirecotr = 0;
	private int countSupervisor = 0;
	private int countOperator = 0;
	
	/**
	 * Create new Thread according to criteria
	 */
	public Thread newThread(Runnable r) {
		Thread t=new Thread(r);
		if(countOperator<MAX_OPERATOR){
			t.setName("Operador " + countOperator++);
			t.setPriority(Thread.MIN_PRIORITY);
			return t;
		}
		if(countSupervisor<MAX_SUPERVISOR){
			t.setName("Supervisor " + countSupervisor++);
			t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
		if(countDirecotr<MAX_DIRECTOR){
			t.setName("Direcotor " + countDirecotr++);
			t.setPriority(Thread.MAX_PRIORITY);
			return t;
		}
		return null;	
		
	}

}
