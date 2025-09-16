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

    //Average price display menu
    public static void averagePriceMenu(String userName, String zoneName, String zoneId,
                                        int totalHours, double averagePrice) {
        spacer(20);
        print("|============[Mean price for " + userName + " in " + zoneName + " area" +"]=============|");
        print("|                                                                |");
        print("|              AVERAGE PRICE ANALYSIS IS READY                   |");
        print("|                                                                |");
        print("|       Zone  " + zoneName +" "+ zoneId  + "                                |");
        print("|       The total hours analyzed is "+ totalHours + "                           |");
        print(String.format("|       The average price in this time zone is: %.4f SEK/kWh   |", averagePrice));
        if (averagePrice < 0.20) {
            print("|       Great! Today has relatively low electricity prices.      |");
        } else if (averagePrice > 0.50) {
            print("|       Today has relatively high electricity prices. Consider delaying energy-intensive activities  |");
        } else {
            print("|            Today has moderate electricity prices.  |");
        }
        print("|                                                                |");
        print("|       [1] Restart from the begining                            |");
        print("|       [2] close the program                                    |");
        print("|                                                                |");
        print("|========================[Select Option]=========================|");
        print("|");
        print("|");
        System.out.print("|~~~~~>:");
    }

    //Min/Max menu
    public static void minMaxPriceMenu(String userName, String zoneName,
                                       double cheapestPrice, String cheapestStart, String cheapestEnd,
                                       double expensivePrice, String expensiveStart, String expensiveEnd,
                                       double priceDifference) {
        spacer(20);
        print("|===============[Price Analysis for " + userName + " in " + zoneName + "]================|");
        print("|                                                                |");
        print("|              CHEAPEST AND MOST EXPENSIVE HOURS                 |");
        print("|                                                                |");
        print(String.format("|            ⬇️  CHEAPEST HOUR: %.4f SEK/kWh                    |", cheapestPrice));
        print(String.format("|                Time: %s to %s                            |", cheapestStart, cheapestEnd));
        print(String.format("|            ⬆️  MOST EXPENSIVE HOUR: %.4f SEK kWh              |", expensivePrice));
        print(String.format("|                Time: %s to %s                            |", expensiveStart, expensiveEnd));
        print(String.format("|            ⚖️  PRICE DIFFERENCE: %.4f SEK/kWh                 |", priceDifference));
        print("|                                                                |");
        if (priceDifference > 0.30) {
            print("|     💡 TIP: Consider timing energy-intensive activities!       |");
        } else {
            print("|    INFO: Price variation is relatively small today.          |");
        }
        print("|                   [1] Back to main menu                        |");
        print("|                   [2] Exit program                             |");
        print("|                                                                |");
        print("|========================[Select Option]=========================|");
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
