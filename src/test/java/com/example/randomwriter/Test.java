package com.example.randomwriter;

public class Test {
	public static void main(String[] args){
		System.out.println("++++++++++++++++++++++++");
String path = System.getProperty("java.class.path");
String path4 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
System.out.println("path 1 = " + path);
System.out.println("path 4 = " + path4);
System.out.println("++++++++++++++++++++++++");
	}
}
