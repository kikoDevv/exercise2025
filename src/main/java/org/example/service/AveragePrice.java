package org.example.service;

import org.example.model.PriceData;
import java.util.List;
import org.example.ui.Menu;

public class AveragePrice {

    //--------Method to calculate and show average price---------
    public static void showAveragePrice(String userName, String zoneName, String zoneId) {
        try {
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


            //--Show avreage menu--
            Menu.spacer(20);
            Menu.print("|============[Mean price for " + userName + " in " + zoneName + " area" +"]=============|");
            Menu.print("|                                                                |");
            Menu.print("|              AVERAGE PRICE ANALYSIS IS READY                   |");
            Menu.print("|                                                                |");
            Menu.print("|       Zone  " + zoneName +" "+ zoneId  + "                                |");
            Menu.print("|       The total hours analyzed is "+ prices.size() + "                           |");
            Menu.print(String.format("|       The average price in this time zone is: %.4f SEK/kWh   |", averagePrice));
            if (averagePrice < 0.20) {
                Menu.print("|       Great! Today has relatively low electricity prices.      |");
            } else if (averagePrice > 0.50) {
                Menu.print("|       Today has relatively high electricity prices. Consider delaying energy-intensive activities  |");
            } else {
                Menu.print("|            Today has moderate electricity prices.  |");
            }
            Menu.print("|                                                                |");
            Menu.print("|       [1] Restart from the begining                            |");
            Menu.print("|       [2] close the program                                    |");
            Menu.print("|                                                                |");
            Menu.print("|========================[Select Option]=========================|");
            Menu.print("|");
            Menu.print("|");
            System.out.print("|~~~~~>:");
        } catch (Exception e) {
            System.out.println("Error calculating average price: " + e.getMessage());
        }
    }
}
