
public class Dispenser {
	private int inventory; // amount of product available
	private int price; // price as cents
	private FoodInfo item; // defines the product in the dispenser
	
	public Dispenser(int iinventory, int iprice, FoodInfo iitem) {
		inventory = iinventory;
		price = iprice;
		item = iitem;
	}
	
	public void alterInventory(int alterBy, String forLog) {
		if(inventory + alterBy < 0) {
					System.err.println("The program tried to set the inventory to contain less than no items,\n"
					+ "the inventory remains unchanged at " + this.getInventory());
		} else {
			inventory += alterBy; // use a positive input to increase inventory, negative to decrease
			Statics.printToFile(Statics.logFile,
					Statics.makeTimestamp() + " : " + alterBy + " " + this.getItem().getName() + " : " + forLog + "\n");
		}
	}

	
	//getSets
	// *** Set up logging for administrative tasks such as set ***
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int ninventory) {
		inventory = ninventory;	
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int nprice) {
		price = nprice;
	}
	public FoodInfo getItem() {
		return item;
	}
	public void setItem(FoodInfo nitem) {
		item = nitem;
	}
}
