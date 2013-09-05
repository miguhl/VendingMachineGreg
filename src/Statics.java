import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Statics {

	// for testing...
	public static void main(String[] args) {
		// printToFile("test1", "test11\n"); // it works!
		// System.out.println(centsIntToDollarsString(1769999)); // it works!
		// System.out.println(makeTimestamp()); // it works!
		// System.out.println(makeFstamp()); // it works!
	}

	public static final String logFile = "testLog1"; // file for logging
														// activities.
	public static final String mLocate = "mLocate"; // has the paths to the
													// machine files(dynamic)

	// prints sinput to the file with path fname
	public static void printToFile(String fname, String sinput) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream(fname, true));
			outputStream.print(sinput);
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fname);
			System.exit(0);
		}
	}

	public static String centsIntToDollarsString(int cents) {
		int dollars = cents / 100; // removes last 2 digits of cents...
		int justCents = cents - (dollars * 100); // takes dollars and adds to 0s
													// in front of it...
		if (justCents != 0 && justCents/10 != 0) {
			return "$" + dollars + "." + justCents;
		} if (justCents !=0 && justCents/10 == 0) {
			return "$" + dollars + "." + "0" + justCents;
		}
		else {
			return "$" + dollars + "." + "00";
		}
	}

	public static VendingMachine vendingMachineFromFile(String fname) {
		try {
			FileInputStream fstream = new FileInputStream(fname);
			DataInputStream dstream = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					dstream));
			/*
			 * Design concept: begin by defining the vending machines variables,
			 * save the dispenser array for last; load in each dispenser, deal
			 * with finishing constructing a machine when either a finish code
			 * happens (0 means end the method, 1 means another dispenser OR
			 * finish when the array is full as defined when the int dispensers
			 * is read?
			 */
			int fdispensers = Integer.parseInt(br.readLine());
			int fmoneyInMachine = Integer.parseInt(br.readLine());
			int finventory;
			int fprice;
			String foodName; // fname taken for filename! woops.
			String fdescription;
			VendingMachine vmReturn = new VendingMachine(fdispensers,
					fmoneyInMachine);
			for (int i = 0; i < fdispensers; i++) {
				finventory = Integer.parseInt(br.readLine());
				fprice = Integer.parseInt(br.readLine());
				foodName = br.readLine();
				fdescription = br.readLine();
				vmReturn.insertDispenser(i, new Dispenser(finventory,
						fprice, new FoodInfo(foodName, fdescription)));
			}
			br.close();
			return vmReturn;
		} catch (Exception e) {
			//
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
			return new VendingMachine(); // never use this, do not save in a
											// file...
		}
	}

	// writes machines to file and closes the program.
	public static void endProgram(VendingMachine vm1, VendingMachine vm2) {
		String machineLog1 = makeFstamp() + "vm1"; // filename for vm1 of
													// course!
		String machineLog2 = makeFstamp() + "vm2";// filename for vm2 of course!
		printToFile(machineLog1, vm1.getDispensers() + "\n"); // concatenating
																// empty string
																// converts int
																// to string!
		// loops through each dispenser to get their attributes including the
		// attributes of the foodinfo contained.
		printToFile(machineLog1, vm1.getMoneyInMachine() + "\n"); // same as
																	// above.
		for (int i = 0; i < vm1.getDispensers(); i++) {
			printToFile(machineLog1, vm1.getFromDispensersArray(i)
					.getInventory() + "\n");
			printToFile(machineLog1, vm1.getFromDispensersArray(i).getPrice()
					+ "\n");
			printToFile(machineLog1, vm1.getFromDispensersArray(i).getItem()
					.getName()
					+ "\n");
			printToFile(machineLog1, vm1.getFromDispensersArray(i).getItem()
					.getDescription()
					+ "\n");
		}
		// above but for vm2 instead of vm1
		printToFile(machineLog2, vm2.getDispensers() + "\n");
		printToFile(machineLog2, vm2.getMoneyInMachine() + "\n");
		for (int i = 0; i < vm2.getDispensers(); i++) {
			printToFile(machineLog2, vm2.getFromDispensersArray(i)
					.getInventory() + "\n");
			printToFile(machineLog2, vm2.getFromDispensersArray(i).getPrice()
					+ "\n");
			printToFile(machineLog2, vm2.getFromDispensersArray(i).getItem()
					.getName()
					+ "\n");
			printToFile(machineLog2, vm2.getFromDispensersArray(i).getItem()
					.getDescription()
					+ "\n");
		}
		File overwrite = new File(mLocate);
		overwrite.delete();
		printToFile(mLocate, machineLog1 + "\n");
		printToFile(mLocate, machineLog2 + "\n");
		printToFile(logFile, makeTimestamp() + " program shut down gracefully.\n");
		System.exit(0);
	}

	// used in log files etc.
	public static String makeTimestamp() {
		Date theDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy'@'H.mm.ss");
		return df.format(theDate);
	}

	// used for naming the files the vending machines are written to
	public static String makeFstamp() {
		Date theDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat(
				"dd'-'MM'-'yyyy'_'H'-'mm'-'ss");
		return df.format(theDate);
	}

	// Get the path for the machine file
	public static String getMLoc(int which) {
		try {
			FileInputStream fstream = new FileInputStream(mLocate);
			DataInputStream dstream = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					dstream));
			String toReturn = br.readLine();
			if (which != 1) {
				for (int i = 1; i < which; i++) {
					toReturn = br.readLine();
				}
			}
			br.close();
			return toReturn;
		} catch (Exception e) {
			//
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
			return ""; // it makes the compiler happy!
		}
	}
}
