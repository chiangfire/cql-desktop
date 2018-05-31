package com.firecode.cqldesktop;

import java.util.function.Consumer;
/**
 * @author JIANG
 *
 */
public class BaseTest{
	
	
	public final void p(Object o){
		System.out.println(o);
	}
	
	public final void timer(Consumer<Object> consumer){
		long time = System.currentTimeMillis();
		consumer.accept(null);
		System.out.println(String.join("", "执行完毕耗时：",String.valueOf(System.currentTimeMillis() - time)));
	}

}
