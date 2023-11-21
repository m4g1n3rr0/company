package telran.view.test;

import telran.view.*;

public class NumberDatesAppl {

	public static void main(String[] args) {
		
		InputOutput io = new StandardInputOutput();
		Menu menu = new Menu(new Item[] {NumbersMenu.getMenu(), Item.exit()}, "Nummbers-Dates-Operators");
		menu.perform(io);

	}

}
