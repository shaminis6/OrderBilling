package org.project.ordertotalcalculation.service;

import java.util.HashMap;

public interface PromotionLevelCalculationInterface {

	public HashMap<String, Double> itemLevelPromotionX(String items[]);

	public HashMap<String, Double> itemLevelPromotionY(String items[]);

	public HashMap<String, Double> orderLevelPromotion(String items[]);

}
