import java.util.Scanner;

public class AdminCLI {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Welcome to the administration interface for the vending machines");
		System.out.println("The first machine is first:");
		System.out
				.print("Enter the amount of dispensers the machine will hold: ");
		int idispensers = Integer.parseInt(sc.nextLine());
		System.out
				.print("Enter the amount of money, in cents, the machine has in it: ");
		int imoneyInMachine = Integer.parseInt(sc.nextLine());
		VendingMachine vm1 = new VendingMachine(idispensers, imoneyInMachine);
		int iinventory;
		int iprice;
		String foodname; // fname reserved for filenames!
		String fdescription;
		for (int i = 0; i < idispensers; i++) {
			System.out.print("Enter the amount of product dispenser " + i
					+ " holds: ");
			iinventory = Integer.parseInt(sc.nextLine());
			System.out
					.print("Enter the price, in cents, the product in dispenser "
							+ i + " will cost: ");
			iprice = Integer.parseInt(sc.nextLine());
			System.out.print("Enter the name of the product in dispenser " + i
					+ ": ");
			foodname = sc.nextLine();
			System.out.print("Describe that item: ");
			fdescription = sc.nextLine();
			vm1.insertDispenser(i, new Dispenser(iinventory, iprice,
					new FoodInfo(foodname, fdescription)));
		}
		System.out.println("The second machine is second:");
		System.out
				.print("Enter the amount of dispensers the machine will hold: ");
		idispensers = Integer.parseInt(sc.nextLine());
		System.out
				.print("Enter the amount of money, in cents, the machine has in it: ");
		imoneyInMachine = Integer.parseInt(sc.nextLine());
		VendingMachine vm2 = new VendingMachine(idispensers, imoneyInMachine);
		for (int i = 0; i < idispensers; i++) {
			System.out.print("Enter the amount of product dispenser " + i
					+ " holds: ");
			iinventory = Integer.parseInt(sc.nextLine());
			System.out
					.print("Enter the price, in cents, the product in dispenser "
							+ i + " will cost: ");
			iprice = Integer.parseInt(sc.nextLine());
			System.out.print("Enter the name of the product in dispenser " + i
					+ ": ");
			foodname = sc.nextLine();
			System.out.print("Describe that item: ");
			fdescription = sc.nextLine();
			vm2.insertDispenser(i, new Dispenser(iinventory, iprice,
					new FoodInfo(foodname, fdescription)));
		}
		Statics.endProgram(vm1, vm2);
	}	
}
