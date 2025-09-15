package org.example.service;

import org.example.model.PriceData;
import java.util.List;
import org.example.ui.Menu;

public class AveragePrice {

    //--------Method to calculate and show average price---------
    public static void showAveragePrice(String userName, String zoneName, String zoneId) {
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Calculating average electricity price for " + userName + " in " + zoneName + "...");
            System.out.println("=".repeat(60));

            GetPrices service = new GetPrices();
            List<PriceData> prices = service.findAllPrices();

            if (prices.isEmpty()) {
                System.out.println("No price data available.");
                return;
            }

            //---Calculate the average price--
            double totalPrice = 0.0;
            for (PriceData price : prices) {
                totalPrice += price.getSEK_per_kWh();
            }

            double averagePrice = totalPrice / prices.size();


            //--Display the result--
            Menu.print("|============[Mean price for " + userName + " in " + zoneName + " area" +"]=============|");
            Menu.print("|                                                                |");
            Menu.print("|              AVERAGE PRICE ANALYSIS IS READY                   |");
            Menu.print("|         Zone " + zoneName + zoneId  + "                                |");
            Menu.print("|   Checking the electricity price for different time and zone.  |");
            Menu.print("|         Scouting best possible time to charge your EV.         |");
            Menu.print("|       Scoute the cheapest and most expensive usage hours.      |");
            Menu.print("|                                                                |");
            Menu.print("|           Lets start by entering your name first               |");
            Menu.print("|                                                                |");
            Menu.print("|=======================[Enter your name]========================|");
            Menu.print("|");
            Menu.print("|");
            System.out.print("|~~~~~>:");



            System.out.printf("Zone: %s (%s)%n", zoneName, zoneId);
            System.out.printf("Period: 24-hour period%n");
            System.out.printf("Total hours analyzed: %d%n", prices.size());
            System.out.printf("Average price: %.4f SEK/kWh%n", averagePrice);
            System.out.println("─".repeat(40));

            //---Additional helpful info---
            if (averagePrice < 0.20) {
                System.out.println("💡 Great! Today has relatively low electricity prices.");
            } else if (averagePrice > 0.50) {
                System.out.println("⚠️  Today has relatively high electricity prices. Consider delaying energy-intensive activities.");
            } else {
                System.out.println("ℹ️  Today has moderate electricity prices.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error calculating average price: " + e.getMessage());
        }
    }
}
