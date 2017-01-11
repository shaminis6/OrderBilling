package org.project.ordertotalcalculation.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderCalculationMainTest {
	
	String items[]={"apples 3"};
	int promoCode=10001;
	OrderCalculationMain ordcal = new OrderCalculationMain();

	@Test
	public void test() {
		int result=(int)ordcal.calculateOrderTotal(items, promoCode);
		assertEquals(result,300);
	}


}
