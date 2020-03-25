package org.iceberg.classloader;

import java.net.*;

public class Andromeda {
	private static final Andromeda instance = new Andromeda();
	
	private Andromeda(){
		
	}
	
	public static Andromeda getInstance(){
		return instance;
	}
	
	public static void main(String[] args) throws Exception{
		
		URLClassLoader loader1 = new URLClassLoader(new URL[]{new URL("file:///home/root/workspace/andromeda/bin/")}){
			public String toString(){
				return "loader1";
			}
		};

		URLClassLoader loader2 = new URLClassLoader(new URL[]{new URL("file:///home/root/workspace/andromeda/bin/")},
				ClassLoader.getSystemClassLoader().getParent()){
			public String toString(){
				return "loader2";
			}
		};

		Class clazz1 = loader1.loadClass("Andromeda");
		Class clazz2 = loader2.loadClass("Andromeda");
		
		Andromeda obj1 = (Andromeda)(clazz1.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
		System.out.println(loader1 + " loads " + obj1);
		
		Andromeda obj2 = (Andromeda)(clazz2.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
		System.out.println(loader2 + " loads " + obj2);
		
		Andromeda obj3 = Andromeda.getInstance();
		System.out.println(Andromeda.class.getClassLoader() + " loads " + obj3);
	}
}

