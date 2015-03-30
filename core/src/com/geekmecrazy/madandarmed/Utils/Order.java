package com.geekmecrazy.madandarmed.Utils;

//TODO AMA a changer par runnable

public abstract class Order {

	public void excute(){
		runOrder();
	}
	
	public abstract void runOrder();
}
