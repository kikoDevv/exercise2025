package org.example;

import org.example.service.GetPrices;
import org.example.model.PriceData;
import java.util.List;
import org.example.ui.Menu;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GetPrices service = new GetPrices();
        List<PriceData> prices = service.findAllPrices();
        String userName;


        //------------------Root----------------------
        Menu.spacer(20);
        Menu.userNameMenu();

        userName = scanner.nextLine();
        System.out.println(userName);


        // for (PriceData price : prices) {
        //     System.out.printf("%.4f SEK/kWh (%s to %s)%n",
        //         price.getSEK_per_kWh(),
        //         price.getTime_start().substring(11, 16), // Just show HH:MM
        //         price.getTime_end().substring(11, 16));
        // }
    }
}
