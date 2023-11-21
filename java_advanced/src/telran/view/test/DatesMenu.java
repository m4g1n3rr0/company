package telran.view.test;

import telran.view.*;

public class DatesMenu {
	
	public static Item getMenu() {
		
		Menu menu = new Menu((Item[]) getItems(), "Dates Operations");
		return menu;
		
	}

	private static Object getItems() {
		
		Item[] items = {
				
			Item.of("Date after given days", io -> dateAfter(io)),
			Item.of("Date after given days", io -> dateBefore(io)),
			Item.of("Number days between two dates", io -> daysBetweenDates(io)),
			exit()
				
		};
		
		return items;
		
	}

	private static Item exit() {
		
		return null;
		
	}

	private static Object daysBetweenDates(InputOutput io) {
		
		return null;
		
	}

	private static Object dateBefore(InputOutput io) {
		
		return null;
		
	}

	private static Object dateAfter(InputOutput io) {
		
		return null;
		
	}
	
}
