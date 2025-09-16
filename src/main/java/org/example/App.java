package org.example;

import org.example.service.AveragePrice;
import org.example.service.MinMaxPrice;
import org.example.service.EVChargingOptimizer;
import org.example.service.AllHourlyPrices;
import org.example.service.CSVImport;
import org.example.ui.Menu;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ------------props------------
        Boolean appIsRunning = true;
        String userName;
        int selectedZone = 0;
        int averageMenuOption = 0;
        int selectedMainMenu = 0;
        String[] zoneIds = { "", "SE3", "SE1", "SE2", "SE4" };
        String[] zoneNames = { "", "Stockholm", "Luleå", "Sundsvall", "Malmö" };

        // --user name menu--
        Menu.spacer(20);
        Menu.userNameMenu();
        userName = scanner.nextLine();

        while (appIsRunning) {
            // --zone menu---
            Menu.spacer(20);
            Menu.zoneMenu(userName);
            selectedZone = scanner.nextInt();
            scanner.nextLine();
            if (selectedZone == 6) {
                System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                appIsRunning = false;
                break;
            }
            // --Main menu--
            Menu.spacer(20);
            Menu.mainMenu(userName, zoneNames[selectedZone]);
            selectedMainMenu = scanner.nextInt();
            scanner.nextLine();

            // ------Handle user selection----
            switch (selectedMainMenu) {
                case 1:
                    // --Show today's average electricity cost for 24H period--
                    AveragePrice.showAveragePrice(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                        appIsRunning = false;
                        break;
                    }
                    break;
                case 2:
                    // --Find cheapest and most expensive hours--
                    MinMaxPrice.showMinMaxPrices(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                        appIsRunning = false;
                        break;
                    }
                    break;
                case 3:
                    // --Find best EV charging times--
                    EVChargingOptimizer.showBestChargingTimes(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                        appIsRunning = false;
                        break;
                    }
                    break;
                case 4:
                    // --Show all hourly prices--
                    AllHourlyPrices.showAllHourlyPrices(userName, zoneNames[selectedZone], zoneIds[selectedZone]);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                        appIsRunning = false;
                        break;
                    }
                    break;
                case 5:
                    //--CSV import and cost calculation--
                    CSVImport.showCSVImport(userName, zoneNames[selectedZone], zoneIds[selectedZone], scanner);
                    averageMenuOption = scanner.nextInt();
                    scanner.nextLine();
                    if (averageMenuOption == 2) {
                        System.out.println("Goodbye " + userName + "! Thanks for using the Electricity Price CLI.");
                        appIsRunning = false;
                        break;
                    }
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
