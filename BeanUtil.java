package com.yige.util;

import java.lang.reflect.Method;
/**
 * @author weinianjie
 * @created 2011-10-19
 * 
 * @version 1.0
 */
public class BeanUtil {
	/**
	 * @parameter Object origin,Object target
	 * @return Object
	 * 
	 * 
	 *         用到反射机制
	 * 
	 * 
	 *         此方法将调用origin的getter方法，将得到的值作为相应的参数传给target的setter方法
	 * 
	 * 
	 *         注意，origin的getter方法和target方法必须是public类型
	 */
	public static Object CopyBeanToBean(Object origin, Object target) {
		Method[] method1 = origin.getClass().getMethods();
		Method[] method2 = target.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							try {
								objs2[0] = method1[i].invoke(origin, objs1);// 激活origin的相应的get的方法，objs1数组存放调用该方法的参数,此例中没有参数，该数组的长度为0
								method2[j].invoke(target, objs2);// 激活target的相应的set的方法，objs2数组存放调用该方法的参数
							} catch (Exception e) {
								e.printStackTrace();
							}
							continue;
						}
					}
				}
			}
		}
		return target;
	}
}
