package telran.view.test;

import telran.view.*;

public class NumbersMenu {
	
	public static Item getMenu() {
		
		Menu menu = new Menu(getItems(), "Arethmetic Operation");
		
		return menu;
		
	}
	
	private static Item[] getItems() {
		
		return ArithmeticSimpleCalculatorAppl.getItems();
		
	}

}
