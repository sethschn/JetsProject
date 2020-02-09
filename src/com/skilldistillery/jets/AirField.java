package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirField {
	// F I E L D S
	private List<Jet> jets = new ArrayList<>();

	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}

	// C O N S T R U C T O R
	public AirField() {
		super();
		readFromFile();
	}

	public void addJetMenu(Scanner kb) {
		System.out.println("Please choose a type of jet to create.");
		System.out.println("1.) Cargo Plane");
		System.out.println("2.) Fighter Jet");
		System.out.println("3.) Passenger Jet");
		System.out.print("Choice(1-3): ");
		int choice = 0;
		try {
			choice = kb.nextInt();
			if (choice > 0 && choice < 4) {
				addJet(kb, choice);
			} else {
				System.out.println();
				System.out.println("\t\tInvalid input please use 1-3\n");
			}
		} catch (Exception e) {
			System.out.println();
			// e.printStackTrace();
			System.out.println("\t\tInvalid input please use 1-3\n");
			choice = 0;
			kb.nextLine();
		}
	}
	

	public void addJet(Scanner kb, int type) {
		// String model, double speed, int range, long price
		String jetModel = null;
		double jetSpeed = 0;
		int jetRange = 0;
		long jetPrice = 0;
		boolean error = false;
		kb.nextLine();
		try {
			System.out.print("Enter model name: ");
			jetModel = kb.nextLine();
		} catch (Exception e) {
			error = true;
			// e.printStackTrace();
			System.out.println("\t\tInvalid input please use a string for model name");
			kb.nextLine();
			
		}
		
		if (!error) {
			try {
				System.out.print("Enter max speed: ");
				jetSpeed = kb.nextDouble();
			} catch (Exception e) {
				error = true;
				// e.printStackTrace();
				System.out.println("\t\tInvalid input please use a double for speed");
				kb.nextLine();
			}
		}
		
		if (!error) {
			try {
				System.out.print("Enter max range: ");
				jetRange = kb.nextInt();
			} catch (Exception e) {
				error = true;
				// e.printStackTrace();
				System.out.println("\t\tInvalid input please use a integer for range");
				kb.nextLine();
			}
		}
		
		if (!error) {
			try {
				System.out.print("Enter price: ");
				jetPrice = kb.nextLong();
			} catch (Exception e) {
				error = true;
				// e.printStackTrace();
				System.out.println("\t\tInvalid input please use a long for price");
				kb.nextLine();
			}
		}	
		if (jetModel == null || jetSpeed == 0.0 || jetRange == 0 || jetPrice == 0) {
			//System.out.println("\t\tINVALID INPUT");
		} else {
			if (!jetModel.equalsIgnoreCase("")) {
				if (type == 1) {
					CargoPlane cp = new CargoPlane(jetModel, jetSpeed, jetRange, jetPrice);
					jets.add(cp);
					System.out.println("Added " + cp.getClass().getSimpleName());
				} else if (type == 2) {
					FighterJet fj = new FighterJet(jetModel, jetSpeed, jetRange, jetPrice);
					jets.add(fj);
					System.out.println("Added " + fj.getClass().getSimpleName());
				} else if (type == 3) {
					PassengerJet pj = new PassengerJet(jetModel, jetSpeed, jetRange, jetPrice);
					jets.add(pj);
					System.out.println("Added " + pj.getClass().getSimpleName());
				}
				System.out.println();
			} else {
				System.out.println("\t\tModel can not be blank");
				System.out.println();
			}
		}
	}

	public void removeJetMenu(Scanner kb) {
		int choice = -1;
		boolean error = false;
		for (int i = 0; i < jets.size(); i++) {
			System.out.println("Index: "+i+" : "+jets.get(i));
		}
		System.out.println();
		try {
			System.out.print("Enter index number: ");
			choice = kb.nextInt();
		} catch (Exception e) {
			error = true;
			// e.printStackTrace();
			System.out.println("\t\tInvalid input please enter a number between 0 and "+(jets.size()-1));
			kb.nextLine();
		}
		if (!error) {
			if (choice > -1 && choice < jets.size()) {
				System.out.println("Removing jet "+jets.get(choice)+"\n");
				jets.remove(choice);
			}else {
				System.out.println("\t\tInvalid input please enter a number between 0 and "+(jets.size()-1));
			}
		}
	}
	
	
	
	public void flyAllJets() {
		for (Jet jet : jets) {
			jet.fly();
		}
	}

	public void loadAllCargoJets() {
		for (Jet jet : jets) {
			if (jet instanceof CargoPlane) {
				((CargoPlane) jet).loadCargo();
				System.out.println(jet);
			}
		}
		System.out.println();
	}

	public void dogfight() {
		for (Jet jet : jets) {
			if (jet instanceof FighterJet) {
				((FighterJet) jet).fight();
				System.out.println(jet);
			}
		}
		System.out.println();
	}

	public void listAllJets() {
		for (Jet jet : jets) {
			System.out.println(jet);
		}
		System.out.println();
	}

	public void getFastestJet() {
		double fastest = Integer.MIN_VALUE;
		Jet fastestJet = null;
		for (Jet jet : jets) {
			if (jet.getSpeedInMach() > fastest) {
				fastest = jet.getSpeedInMach();
				fastestJet = jet;
			}
		}
		System.out.println("Fastest jet is the " + fastestJet.getModel() + " with a speed of " + fastestJet.getSpeed() + " km/h");
		System.out.println(fastestJet+"\n");
	}

	public void getLongestRangeJet() {
		int longest = Integer.MIN_VALUE;
		Jet lrJet = null;
		for (Jet jet : jets) {
			if (jet.getRange() > longest) {
				longest = jet.getRange();
				lrJet = jet;
			}
		}
		System.out.println("Longest range jet is the " + lrJet.getModel() + " with a range of "
				+ (double) lrJet.getRange() + " km");
		System.out.println(lrJet+"\n");
	}

	public void readFromFile() {
		String fileName = "jets.txt";
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] jetBits = line.split(", ");
				for (String string : jetBits) {
					// System.out.println(string);
				}
				if (jetBits[0].equalsIgnoreCase("FighterJet")) {
					FighterJet fj = new FighterJet(jetBits[1], Double.parseDouble(jetBits[2]),
							Integer.parseInt(jetBits[3]), Long.parseLong(jetBits[4]));
					jets.add(fj);
				} else if (jetBits[0].equalsIgnoreCase("CargoPlane")) {
					CargoPlane cp = new CargoPlane(jetBits[1], Double.parseDouble(jetBits[2]),
							Integer.parseInt(jetBits[3]), Long.parseLong(jetBits[4]));
					jets.add(cp);
				} else if (jetBits[0].equalsIgnoreCase("PassengerJet")) {
					PassengerJet pj = new PassengerJet(jetBits[1], Double.parseDouble(jetBits[2]),
							Integer.parseInt(jetBits[3]), Long.parseLong(jetBits[4]));
					jets.add(pj);
				}
				// System.out.println(jets);
				// System.out.println(jetBits[0]);
				// jetBits[0] is type
				// jetBits[1] is model string
				// jetBits[2] is speed double
				// jetBits[3] is range int
				// jetBits[4] is price long

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Invalid filename: " + e.getMessage());
			return;
		} catch (IOException e) {
			System.err.println("Problem while reading " + fileName + ": " + e.getMessage());
			return;
		}
	}

}
