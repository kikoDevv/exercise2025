package org.example.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPrices {
  private static final String URL = "https://www.elprisetjustnu.se/api/v1/prices/2025/09-15_SE3.json";

  private final HttpClient client;

  public GetPrices() {
    client = HttpClient.newHttpClient();
  }

  public String findAll() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URL))
        .GET()
        .build();

      HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        return response.body();
      } else {
        throw new RuntimeException("Failed to fetch data with status code: " + response.statusCode());
      }

    } catch (Exception e) {
      throw new RuntimeException("Error fetching price data: " + e.getMessage(), e);
    }
  };

  public static void main(String[] args) {
    GetPrices priceService = new GetPrices();

    try {
      String prices = priceService.findAll();
      System.out.println("Todays electricity prices:");
      System.out.println(prices);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

}
