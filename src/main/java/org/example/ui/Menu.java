package org.example.ui;


public class Menu {
    //----------func to print-------------
    public static void print(String message) {
        System.out.println(message);
    }
    //--------func for main menu UI----------
    //userName menu
    public static void userNameMenu () {
        print("|=====================[Welcome to El scoute]=====================|");
        print("|                                                                |");
        print("|        Here you will be able to scoute the following           |");
        print("|                                                                |");
        print("|   Checking the electricity price for different time and zone.  |");
        print("|         Scouting best possible time to charge your EV.         |");
        print("|       Scoute the cheapest and most expensive usage hours.      |");
        print("|                                                                |");
        print("|           Lets start by entering your name first               |");
        print("|                                                                |");
        print("|=======================[Enter your name]========================|");
        print("|");
        print("|");
        System.out.print("|~~~~~>:");
    }

    //Zone menu
    public static void zoneMenu (String userName) {

        print("|=====================[Welcome " + userName + "]=====================|");
        print("|                                                                |");
        print("|           Please select one of the area below                  |");
        print("|                                                                |");
        print("|     [1] if your scouting for [ Stockholm ] area                |");
        print("|     [2] if your scouting for [ Luleå ] area                    |");
        print("|     [3] if your scouting for [ Sundsvall ] area                |");
        print("|     [4] if your scouting for [ Malmö ] area                    |");
        print("|     [6] Exit program                                           |");
        print("|                                                                |");
        print("|======================[Select your area]========================|");
        print("|");
        print("|");
        System.out.print("|~~~~~>:");
    }
    //Main menu
    public static void mainMenu (String userName, String area) {
       print("|=====================[Menu for " + userName + " in " + area + "]=====================|");
        print("|                                                                |");
        print("|          Please select one of the following option             |");
        print("|                                                                |");
        print("|     [1] Check todays average electricity cost for 24H period   |");
        print("|     [2] Scout the cheapest and most expensive hourly prices    |");
        print("|     [3] Scout the best time zone to charge your EV             |");
        print("|     [4] Check all hourly prices for today                      |");
        print("|     [5] Import consumption data from CSV file                  |");
        print("|     [6] Exit program                                           |");
        print("|                                                                |");
        print("|=======================[Select option]========================|");
        print("|");
        print("|");
        System.out.print("|~~~~~>:");
    }
    //-----------func to print spacer vertically-----------
    public static void spacer(int space) {
      for (int i = 0; i < space; i++) {
        System.out.println("                                :");
      }
      System.out.println();
    }
}
