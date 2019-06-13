package com.almundo.callcenter.test;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.almundo.callcenter.dispatcher.Dispatcher;

/**
 * Test to answer a call, twenty simultaneous calls and twenty sequential calls
 * 
 * @author  Gonzalo Bordoy
 * @see     com.almundo.callcenter.Main
 */
public class CallCenterTest {
	private static Dispatcher dispatcher =new Dispatcher();
	
	@BeforeClass
	public static void before(){
		dispatcher.init();
	}
	
	@AfterClass
	public static void after(){
		dispatcher.shutdown();
	}
	
	@Test
	public void testOneCall() {
		dispatcher.dispatchCall();
	}

	@Test
	public void testConcurrencyCalls() {
		for(int i=0; i<20; i++){
			Thread t=new Thread(){
				@Override
				public void run(){
					dispatcher.dispatchCall();
				}
			};
			t.start();
			
		}
	}
	@Test
	public void testSecuencialCalls() {
		for(int i=0; i<20; i++){
			try {
				Thread.sleep(100);
				dispatcher.dispatchCall();
			} catch (InterruptedException e) {
				fail(e.getMessage());
			}
		}
	}
}
