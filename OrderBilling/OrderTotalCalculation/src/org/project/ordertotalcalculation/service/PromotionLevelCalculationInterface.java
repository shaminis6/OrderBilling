package org.project.ordertotalcalculation.service;

import java.util.HashMap;

/*
 * Interface which declares the promotion methods
 */

public interface PromotionLevelCalculationInterface {

	public HashMap<String, Double> itemLevelPromotionX(String items[]);

	public HashMap<String, Double> itemLevelPromotionY(String items[]);

	public HashMap<String, Double> orderLevelPromotion(String items[]);

}
