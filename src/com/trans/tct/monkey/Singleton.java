package com.trans.tct.monkey;

public class Singleton {

	private static class SingleIns{
		private static final Singleton singleton = new Singleton();
	}
	
	private Singleton(){
		
	}
	
	public static Singleton getIns(){
		return SingleIns.singleton;
	}
	
	

}
