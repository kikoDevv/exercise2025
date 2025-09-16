package org.example.service;

import org.example.model.PriceData;
import org.example.ui.Menu;
import java.util.List;

public class MinMaxPrice {

  public static void showMinMaxPrices(String userName, String zoneName, String zoneId) {
    try {
      GetPrices service = new GetPrices();
      List<PriceData> prices = service.findAllPrices();

      if (prices.isEmpty()) {
        System.out.println("No price data available.");
        return;
      }

      // --Find cheapest price--
      PriceData cheapestHour = prices.get(0);
      for (PriceData price : prices) {
        if (price.getSEK_per_kWh() < cheapestHour.getSEK_per_kWh()) {
          cheapestHour = price;
        }
      }

      // --Find most expensive price--
      PriceData expensiveHour = prices.get(0);
      for (PriceData price : prices) {
        if (price.getSEK_per_kWh() > expensiveHour.getSEK_per_kWh()) {
          expensiveHour = price;
        }
      }

      // --Calculate price difference--
      double priceDifference = expensiveHour.getSEK_per_kWh() - cheapestHour.getSEK_per_kWh();

      // --Display results using Menu--
      Menu.minMaxPriceMenu(
          userName,
          zoneName,
          cheapestHour.getSEK_per_kWh(),
          cheapestHour.getTime_start().substring(11, 16),
          cheapestHour.getTime_end().substring(11, 16),
          expensiveHour.getSEK_per_kWh(),
          expensiveHour.getTime_start().substring(11, 16),
          expensiveHour.getTime_end().substring(11, 16),
          priceDifference);

    } catch (Exception e) {
      System.out.println("❌ Error finding min/max prices: " + e.getMessage());
    }
  }
}
