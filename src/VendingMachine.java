
public class VendingMachine {
	// *** All money is CENTS ***
	// dispensers isn't final because the compiler whines about finals not being initialized
	// until the object of the class containing them is constructed
	private int moneyInMachine; // the amount of money in the machine
	private int dispensers; // this is not meant to change
	private Dispenser[] dispensersArray; // because the array size cannot be changed.
	
	public VendingMachine(int idispensers, int imoneyInMachine) {
		dispensers = idispensers;
		moneyInMachine = imoneyInMachine;
		// arrays cannot be passed into constructor, use insertDispenser(int, Dispenser) later
		dispensersArray = new Dispenser[dispensers];
	}
	/* Just for try/catch during fatal errors, never use the empty constructor
	 * during normal program situations, NEVER write the machines made by this to file!*/
	public VendingMachine() {
		dispensers = 0;
		moneyInMachine = 0;
		dispensersArray = new Dispenser[0];
	}
	
	public void addMoney(int iinput) {
			moneyInMachine += iinput;
	}
	
	public int purchase(int slot, int money) { // returns change
		if(slot >= dispensers) {
			System.out.println("That is not a valid slot!");
			return money;
		} if(dispensersArray[slot].getInventory() == 0) {
			System.out.println("That item is sold out.");
			return money;
		} if(money < dispensersArray[slot].getPrice()) {
			System.out.println("Insufficient funds.");
			return money;
		} else {
			// log gets written from Dispenser.alterInventory
			dispensersArray[slot].alterInventory(-1, "Item was purchased."); //String for log
			System.out.println("Enjoy your " + dispensersArray[slot].getItem().getName() + ".\n"
					+ "It Cost you " + Statics.centsIntToDollarsString(dispensersArray[slot].getPrice())
					+ " and you have " + Statics.centsIntToDollarsString(money - dispensersArray[slot].getPrice())
					+ " change.");
			return money - dispensersArray[slot].getPrice();
		}
	}
	
	public String makeMenu() {
		String menu = "Dispenser : Price : Item Name : Item Description : Availability\n";
		for(int i = 0; i < dispensers; i++) {
			menu = menu.concat(i + " : " + Statics.centsIntToDollarsString(dispensersArray[i].getPrice()) + " : "
					+ dispensersArray[i].getItem().getName() + " : "
					+ dispensersArray[i].getItem().getDescription() + " : "
					+ dispensersArray[i].getInventory() + "\n");
		}
		return menu;
	}
	
	public void insertDispenser(int iinput, Dispenser dinput) {
		dispensersArray[iinput] = dinput;
	}
	
	
	// dispensers can't be redefined because arrays aren't dynamic, just a get here.
	public int getDispensers() {
		return dispensers;
	}
	public int getMoneyInMachine() {
		return moneyInMachine;
	}
	public Dispenser getFromDispensersArray(int index) {
		return dispensersArray[index];
	}
	// no get dispenser from array/ get the whole array because
	// all code that needs that is in this class such as purchase
}
