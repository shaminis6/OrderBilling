package org.project.ordertotalcalculation.service;

import java.util.HashMap;

import org.project.ordertotalcalculation.model.Item;

/*
 * This class calculates the order total with discounts
 */

public class OrderTotalCalculation {

	Item item = new Item();
	HashMap<String, Double> itemPrices = new HashMap<String, Double>();
	PromotionLevelCalculation promocal = new PromotionLevelCalculation();
	HashMap<String, Double> itemTotal = new HashMap<String, Double>();

	/*
	 * Calls appropriate promotion methods
	 */

	public HashMap<String, Double> calculateOrderItemsTotal(String items[],
			int promoCode) {
		if (promoCode != 0) {
			if (promoCode == 10000) {
				// call ItemlevelPromotion
				itemTotal = promocal.itemLevelPromotionX(items);
			} else if (promoCode == 10001) {
				itemTotal = promocal.itemLevelPromotionY(items);
			} else {
				// call OrderlevelPromotion
				itemTotal = promocal.orderLevelPromotion(items);
			}
		} else {
			// Calculate Order Total for items with no discounts
			for (int j = 0; j < items.length; j++) {
				String subItems[] = items[j].split(" ");
				String subItemName = subItems[0];
				int quantity = Integer.parseInt(subItems[1]);
				double itemPrice = itemPrices.get(subItemName);
				itemTotal.put(subItemName, itemPrice * quantity);
			}
		}
		return itemTotal;

	}

}
