package org.project.ordertotalcalculation.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderCalculationMainTest {
	
	String items[]={"Apples 2"};
	int promoCode=10000;
	OrderCalculationMain ordcal = new OrderCalculationMain();

	@Test
	public void test() {
		int result=(int)ordcal.calculateOrderTotal(items, promoCode);
		assertEquals(result,400);
	}

}
