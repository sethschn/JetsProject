package com.skilldistillery.jets;

import java.util.List;
import java.util.Scanner;

public class JetsApplication {
	private static AirField airField = new AirField();
	private static Scanner kb = new Scanner(System.in);
	private static JetsApplication app = new JetsApplication();

	public static void main(String[] args) {
		app.launch();
		kb.close();
	}

	public JetsApplication() {
		super();
	}

	private void launch() {
		app.displayUserMenu();
	}

	private void displayUserMenu() {

		System.out.println("1.) List fleet");
		System.out.println("2.) Fly all jets");
		System.out.println("3.) View fastest jet");
		System.out.println("4.) View jet with longest range");
		System.out.println("5.) Load all Cargo Jets");
		System.out.println("6.) Dogfight");
		System.out.println("7.) Add a jet to Fleet");
		System.out.println("8.) Remove a jet from Fleet");
		System.out.println("9.) Quit the program");
		System.out.print("Choice(1-9): ");
		getUserInput();
	}//end displaymenu
	
	public void getUserInput() {
		//System.out.print("Enter your choice: ");
		int choice = 0;
		try {
			choice = kb.nextInt();
			if (choice > 0 && choice < 10) {
				//System.out.println("valid input");
			}else {
				System.out.println("\t\tInvalid input please use 1-9");
				displayUserMenu();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("\t\tInvalid input please use 1-9");
			choice = 0;
			kb.nextLine();
			displayUserMenu();
		}
		System.out.println();
		switch (choice) {
		case 1:
//		1) List fleet
			airField.listAllJets();
			displayUserMenu();
			break;
		case 2:
//		2) Fly all jets
			airField.flyAllJets();
			displayUserMenu();
			break;
		case 3:
//		3) View fastest jet
			airField.getFastestJet();
			displayUserMenu();
			break;
		case 4:
//		4) View jet with longest range
			airField.getLongestRangeJet();
			displayUserMenu();
			break;
		case 5:
//		5) Load all Cargo Jets
			airField.loadAllCargoJets();
			displayUserMenu();
			break;
		case 6:
//		6) Dogfight!
			airField.dogfight();
			displayUserMenu();
			break;
		case 7:
//		7) Add a jet to Fleet
			airField.addJetMenu(kb);
			displayUserMenu();
			break;
		case 8:
//		8) Remove a jet from Fleet
			airField.removeJetMenu(kb);
			displayUserMenu();
			break;
		case 9:
//		9) Quit
			System.out.println("Quitting");
			kb.close();
			System.exit(0);
			break;
		default:
			System.out.println("\t\tYou have entered default");

		}
	}
	

}
