package org.project.ordertotalcalculation.service;

import java.util.HashMap;

import org.project.ordertotalcalculation.model.Item;

public class PromotionLevelCalculation implements
		PromotionLevelCalculationInterface {

	Item item = new Item();
	HashMap<String, Double> itemPrices = item.getItemPrices();
	static HashMap<String, Double> itemSubTotals = new HashMap<String, Double>();
	static boolean isPromotionXApplied = false;
	static boolean isPromotionYApplied = false;

	@Override
	public HashMap<String, Double> itemLevelPromotionX(String items[]) {
		isPromotionXApplied = true;
		double orderAdjustment = 0.0;
		for (int k = 0; k < items.length; k++) {
			String subItems[] = items[k].split(" ");
			String subItemNameX = subItems[0];
			int quantity = Integer.parseInt(subItems[1]);
			if (isPromotionYApplied) {
				if (quantity >= 3) {
					int remainingQuantity = quantity - 3;
					if (itemSubTotals.containsKey(subItemNameX)) {
						double itemPrice = itemSubTotals.get(subItemNameX);
						orderAdjustment = (itemPrice * 1.5)
								+ (remainingQuantity * itemPrice);
						itemSubTotals.put(subItemNameX, orderAdjustment);
					}

				} else {
					if (itemPrices.containsKey(subItemNameX)) {
						double itemPrice = itemPrices.get(subItemNameX);
						itemSubTotals.put(subItemNameX, itemPrice * quantity);
					}
				}

			} else {
				if (quantity >= 3) {
					int remainingQuantity = quantity - 3;
					if (itemPrices.containsKey(subItemNameX)) {
						double itemPrice = itemPrices.get(subItemNameX);
						orderAdjustment = (itemPrice * 1.5)
								+ (remainingQuantity * itemPrice);
						itemSubTotals.put(subItemNameX, orderAdjustment);
					}

				} else {
					if (itemPrices.containsKey(subItemNameX)) {
						double itemPrice = itemPrices.get(subItemNameX);
						itemSubTotals.put(subItemNameX, itemPrice * quantity);
					}
				}
			}
		}
		return itemSubTotals;
	}

	@Override
	public HashMap<String, Double> itemLevelPromotionY(String items[]) {
		isPromotionYApplied = true;
		for (int k = 0; k < items.length; k++) {
			String subItems[] = items[k].split(" ");
			String subItemNameX = subItems[0];
			int quantity = Integer.parseInt(subItems[1]);
			if (isPromotionXApplied) {
				if (itemPrices.containsKey(subItemNameX)) {
					double itemPrice = itemSubTotals.get(subItemNameX);
					itemSubTotals.put(subItemNameX, itemPrice * 0.5);
				}
			} else {
				if (itemPrices.containsKey(subItemNameX)) {
					double itemPrice = itemPrices.get(subItemNameX);
					itemSubTotals.put(subItemNameX, itemPrice * quantity * 0.5);
				}
			}

		}
		return itemSubTotals;

	}

	@Override
	public HashMap<String, Double> orderLevelPromotion(String items[]) {
		// TODO Auto-generated method stub
		double orderAdjustment = 0.0;
		boolean isPromotionApplied = false;
		for (int l = 0; l < items.length; l++) {
			String subitems[] = items[l].split(" ");
			String subItemNameX = subitems[0];
			int quantityX = Integer.parseInt(subitems[1]);
			if ((subItemNameX.equals("oranges") || subItemNameX
					.equals("apples")) && !isPromotionApplied) {
				l++;
				while (l < items.length) {
					String subitemslist[] = items[l].split(" ");
					String subItemNameY = subitemslist[0];
					int quantityY = Integer.parseInt(subitemslist[1]);
					if (subItemNameY.equals("oranges")
							|| subItemNameY.equals("apples")) {
						isPromotionApplied = true;
						if (quantityX <= quantityY) {
							itemSubTotals.put(subItemNameX,
									(100.0 * quantityX) / 2);
							int remainingQuantity = quantityY - quantityX;
							double itemPriceY = itemPrices.get(subItemNameY);
							orderAdjustment = ((100 * quantityX) / 2)
									+ (remainingQuantity * itemPriceY);
							itemSubTotals.put(subItemNameY, orderAdjustment);
						} else {
							itemSubTotals.put(subItemNameY,
									(100.0 * quantityY) / 2);
							int remainingQuantity = quantityX - quantityY;
							double itemPriceX = itemPrices.get(subItemNameX);
							orderAdjustment = ((100 * quantityY) / 2)
									+ (remainingQuantity * itemPriceX);
							itemSubTotals.put(subItemNameX, orderAdjustment);
						}
					} else {
						double itemPriceY = itemPrices.get(subItemNameY);
						itemSubTotals.put(subItemNameY, itemPriceY * quantityY);
					}
					if (!isPromotionApplied)
						l++;
					else
						break;
				}
			} else {
				if (itemPrices.containsKey(subItemNameX)) {
					double itemPrice = itemPrices.get(subItemNameX);
					itemSubTotals.put(subItemNameX, itemPrice * quantityX);
				}
			}
		}
		return itemSubTotals;
	}

}
