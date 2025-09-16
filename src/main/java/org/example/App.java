package org.example;

import org.example.service.AveragePrice;
import org.example.service.MinMaxPrice;
import org.example.ui.Menu;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //------------props------------
        Boolean appIsRunning = true;

        String userName;
        int selectedZone = 0;
        int averageMenuOption = 0;
        int selectedMainMenu = 0;
        String[] zoneIds = {"", "SE3", "SE1", "SE2", "SE4"};
        String[] zoneNames = {"", "Stockholm", "Luleå", "Sundsvall", "Malmö"};

        //--user name menu--
        Menu.spacer(20);
        Menu.userNameMenu();
        userName = scanner.nextLine();

        while (appIsRunning) {
        //--zone menu---
        Menu.spacer(20);
        Menu.zoneMenu(userName);
        selectedZone = scanner.nextInt();
        scanner.nextLine();
            //--Main menu--
            Menu.spacer(20);
            Menu.mainMenu(userName, zoneNames[selectedZone]);
            selectedMainMenu = scanner.nextInt();
            scanner.nextLine();

            //------Handle user selection----
            switch (selectedMainMenu) {
                case 1:
                    //--Show today's average electricity cost for 24H period--
                    AveragePrice.showAveragePrice(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        appIsRunning = false;
                    }
                    break;
                case 2:
                    //--Find cheapest and most expensive hours--
                    MinMaxPrice.showMinMaxPrices(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine(); 
                    if (averageMenuOption == 2) {
                        appIsRunning = false;
                    }
                    break;
                case 3:
                    System.out.println("Feature coming soon: Best EV charging time");
                    break;
                case 4:
                    System.out.println("Feature coming soon: All hourly prices");
                    break;
                case 5:
                    System.out.println("Feature coming soon: CSV import");
                    break;
                case 6:
                    System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                    appIsRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a number between 1-6.");
            }
        }

        scanner.close();
    }

}
