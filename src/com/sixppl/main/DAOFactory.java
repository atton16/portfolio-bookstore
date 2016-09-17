package com.sixppl.main;

public class DAOFactory {
	
	String method;
	
	public DAOFactory(String method){
		this.method = method;
	}
	
	public DAOMethod getMethod(){
		switch(method.toLowerCase()){
			case "dummy": return (DAOMethod) new DummyMethod();
			default: return null;
		}
	}

}
