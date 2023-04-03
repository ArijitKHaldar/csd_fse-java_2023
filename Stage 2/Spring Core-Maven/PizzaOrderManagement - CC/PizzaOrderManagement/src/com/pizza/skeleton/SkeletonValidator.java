package com.pizza.skeleton;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *		   This class is used to verify if the Code Skeleton is intact
 *         and not modified by participants thereby ensuring smooth auto
 *         evaluation
 */

public class SkeletonValidator {
	public SkeletonValidator() {
		validateClassName("com.pizza.util.PizzaShop");
		validateClassName("com.pizza.model.PizzaOrder");
		validateClassName("com.pizza.exception.InvalidPizzaOrderException");
		validateClassName("com.pizza.test.PizzaShopTest");

		validateMethodSignature(
				"getPizzaType:java.lang.String,setPizzaType:void,setOrderId:void,getCustomerName:java.lang.String,getPizzaName:java.lang.String,getSize:java.lang.String,setPizzaName:void,getOrderId:int,setSize:void,setCustomerName:void,getQuantity:int,setQuantity:void",
				"com.pizza.model.PizzaOrder");
		validateMethodSignature(
				"viewPizzaOrdersByType:java.util.List,viewPizzaOrdersTypeWise:java.util.Map,countTotalQuantityForEachSize:java.util.Map,viewPizzaOrderById:com.pizza.model.PizzaOrder,getPizzaOrderList:java.util.List,validateSize:boolean,setPizzaOrderList:void",
				"com.pizza.util.PizzaShop");
		validateMethodSignature(
				"test11ValidateSizeWhenSmall:void,test12ValidateSizeWhenMedium:void,test13ValidateSizeWhenLarge:void,test14ValidateSizeWhenInvalid:void,test15ViewPizzaOrderByIdWhenValid:void,test16ViewPizzaOrderByIdWhenInvalid:void,test17ViewPizzaOrdersByType:void,test18ViewPizzaOrdersTypeWise:void,test19CountTotalQuantityForEachSize:void,test20ViewPizzaOrdersByTypeForEmptyList:void,test21ViewPizzaOrdersTypeWiseForEmptyList:void,test22CountTotalQuantityForEachSizeForEmptyList:void",
				"com.pizza.test.PizzaShopTest");

	}

	private static final Logger LOG = Logger.getLogger("SkeletonValidator");

	protected final boolean validateClassName(String className) {

		boolean iscorrect = false;
		try {
			Class.forName(className);
			iscorrect = true;
			LOG.info("Class Name " + className + " is correct");

		} catch (ClassNotFoundException e) {
			LOG.log(Level.SEVERE, "You have changed either the " + "class name/package. Use the correct package "
					+ "and class name as provided in the skeleton - "+className);

		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"There is an error in validating the " + "Class Name. Please manually verify that the "
							+ "Class name is same as skeleton before uploading");
		}
		return iscorrect;
	}

	protected final void validateMethodSignature(String methodWithExcptn, String className) {
		Class cls = null;
		String methodName = null;
		try {

			String[] actualmethods = methodWithExcptn.split(",");
			boolean errorFlag = false;
			String[] methodSignature;

			String returnType = null;

			for (String singleMethod : actualmethods) {
				boolean foundMethod = false;
				methodSignature = singleMethod.split(":");

				methodName = methodSignature[0];
				returnType = methodSignature[1];
				cls = Class.forName(className);
				Method[] methods = cls.getMethods();
				for (Method findMethod : methods) {
					if (methodName.equals(findMethod.getName())) {
						foundMethod = true;
						if (!(findMethod.getReturnType().getName().equals(returnType))) {
							errorFlag = true;
							LOG.log(Level.SEVERE, " You have changed the " + "return type in '" + methodName
									+ "' method. Please stick to the " + "skeleton provided");

						} else {
							LOG.info("Method signature of " + methodName + " is valid");
						}

					}
				}
				if (!foundMethod) {
					errorFlag = true;
					LOG.log(Level.SEVERE, " Unable to find the given public method " + methodName
							+ ". Do not change the " + "given public method name. " + "Verify it with the skeleton");
				}

			}
			if (!errorFlag) {
				LOG.info("Method signature is valid");
			}

		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					" There is an error in validating the " + "method structure. Please manually verify that the "
							+ "Method signature is same as the skeleton before uploading. Class Name : "+className+", method name : "+methodName);
		}
	}

}
