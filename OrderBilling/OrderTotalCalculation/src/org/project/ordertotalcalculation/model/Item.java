package org.project.ordertotalcalculation.model;

import java.util.HashMap;
/*
 * This model class holds the original item price of each item
 */
public class Item {

	private HashMap<String, Double> itemPrices = new HashMap<String, Double>();

	public Item() {
		itemPrices.put("apples", 200.0);
		itemPrices.put("oranges", 200.0);
		itemPrices.put("plums", 200.0);
	}

	public HashMap<String, Double> getItemPrices() {
		return itemPrices;
	}

	public void setItemPrices(HashMap<String, Double> itemPrices) {
		this.itemPrices = itemPrices;
	}

}
