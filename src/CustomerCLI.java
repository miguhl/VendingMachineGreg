import java.util.Scanner;

public class CustomerCLI {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// this is for the assignment requisites.
		System.out.println("You arrived at: " + Math.random());
		// get the machine paths
		VendingMachine vm1 = Statics.vendingMachineFromFile(Statics.getMLoc(1));
		VendingMachine vm2 = Statics.vendingMachineFromFile(Statics.getMLoc(2));
		System.out
				.println("Which vending machine would you like to use (1 or 2)?");
		// randomly determines machine as points are given in the rubric
		int machineToUse = (((int) (Math.random() * 10) % 2) + 1);
		boolean continueUse = true;
		while (continueUse) {
			if (machineToUse == 1) {
				useMachine(vm1);
			}
			if (machineToUse == 2) {
				useMachine(vm2);
			}
			if(Math.random() < 0.25) {
				continueUse = false; // 1/4 chance of machines being shut off
			}
		}
		sc.close();
		Statics.endProgram(vm1, vm2);
	}

	public static void useMachine(VendingMachine vm) {
		int moneyCustomerCredits;
		int whichDispenser;
		System.out.print(vm.makeMenu());
		System.out.print("\nEnter money to credit to machine: ");
		moneyCustomerCredits = Integer.parseInt(sc.nextLine());
		System.out.print("\nEnter dispenser number: ");
		whichDispenser = Integer.parseInt(sc.nextLine());
		vm.purchase(whichDispenser, moneyCustomerCredits);
	}
}
