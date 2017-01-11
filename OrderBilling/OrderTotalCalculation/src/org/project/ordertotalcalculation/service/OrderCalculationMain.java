package org.project.ordertotalcalculation.service;

import java.util.HashMap;
import java.util.Scanner;

/*
 * This main method gets user inputs and performs Promo code validation and displays the final Order total
 */
public class OrderCalculationMain {

	private boolean itemDiscountX = false;
	private boolean itemDiscountY = false;
	private boolean orderDiscount = false;

	/*
	 * Order Total Calculation Method
	 */
	public double calculateOrderTotal(String items[], int promoCode) {
		double orderTotal = 0.0;
		OrderTotalCalculation ordtotcal = new OrderTotalCalculation();
		HashMap<String, Double> itemSubTotals = ordtotcal
				.calculateOrderItemsTotal(items, promoCode);
		try {
			for (int m = 0; m < items.length; m++) {
				String subItems[] = items[m].split(" ");
				String itemName = subItems[0];
				if (itemSubTotals.containsKey(itemName)) {
					double itemTotal = itemSubTotals.get(itemName);
					orderTotal = orderTotal + itemTotal;
				}
			}
		} catch (Exception e) {
			System.out.println("items not present");
		}
		return orderTotal;
	}

	/*
	 * Promo Code validation
	 */

	public boolean promoCodeValidation(int promoCode) {
		if (promoCode == 10000 && itemDiscountX == false) {
			itemDiscountX = true;
			orderDiscount = true;
			return itemDiscountX;
		} else if (promoCode == 10001 && itemDiscountY == false) {
			itemDiscountY = true;
			orderDiscount = true;
			return itemDiscountY;
		} else if (promoCode == 10002 && orderDiscount == false) {
			orderDiscount = true;
			return orderDiscount;
		} else
			return false;

	}

	/*
	 * Main method
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderCalculationMain ordcal = new OrderCalculationMain();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of items");
		String numberOfItems = sc.nextLine();
		String items[] = new String[Integer.parseInt(numberOfItems)];
		System.out
				.println("Please choose any of the items from the list \n1.apples \n2.oranges \n3.plums");
		System.out.println("Enter the item names and its quantity one by one");
		for (int i = 0; i < Integer.parseInt(numberOfItems); i++) {
			items[i] = sc.nextLine();
		}
		char choice = 'Y';
		int promoCode = 0;
		double orderTotal = 0.0;
		System.out
				.println("Choose any of the below given promocodes \n1.Buy 3kgs of any item for the price of 1.5kgs(PromoCode:10000)"
						+ "\n2.50% off on price of each item(PromoCode:10001) \n3. Buy 1kg of Apples & 1kg of Oranges for Rs.100(PromoCode:10002)");

		do {
			System.out.println("Enter the promo code : ");
			promoCode = sc.nextInt();
			boolean promoFlag = ordcal.promoCodeValidation(promoCode);
			if (promoFlag == true || promoCode == 0)
				orderTotal = ordcal.calculateOrderTotal(items, promoCode);
			else
				System.out
						.println("Invalid Promo Code");
			if (promoCode != 10002) {
				System.out
						.println("Do you want to continue.Press 'Y' or 'N'. If 'Y', Please enter the promo code");
				choice = sc.next().charAt(0);
			} else
				choice = 'N';
		} while (choice == 'Y');
		System.out.println("Order Total : " + (int) orderTotal);
		sc.close();
	}

}
