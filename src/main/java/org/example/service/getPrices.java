package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.PriceData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetPrices {
  private static final String URL = "https://www.elprisetjustnu.se/api/v1/prices/2025/09-15_SE3.json";

  private final HttpClient client;
  private final ObjectMapper objectMapper;

  public GetPrices() {
    client = HttpClient.newHttpClient();
    objectMapper = new ObjectMapper();
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

  public List<PriceData> findAllPrices() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URL))
        .GET()
        .build();

      HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        //------------Parse JSON into List of PriceData objects---------
        TypeReference<List<PriceData>> typeReference = new TypeReference<List<PriceData>>() {};
        return objectMapper.readValue(response.body(), typeReference);
      } else {
        throw new RuntimeException("Failed to fetch data with status code: " + response.statusCode());
      }

    } catch (Exception e) {
      throw new RuntimeException("Error fetching price data: " + e.getMessage(), e);
    }
  }

  public static void main(String[] args) {
    GetPrices priceService = new GetPrices();

    try {
      //--Get raw JSON string--
      String rawPrices = priceService.findAll();
      System.out.println("-----------Raw JSON response here----------:");
      System.out.println(rawPrices);

      System.out.println("=============== Parsed data ================");

      //---Get parsed PriceData objects---
      List<PriceData> priceDataList = priceService.findAllPrices();
      System.out.println("Parsed electricity prices:");

      for (PriceData price : priceDataList) {
        System.out.printf("From %s to %s: %.4f SEK/kWh%n",
                         price.getTime_start(),
                         price.getTime_end(),
                         price.getSEK_per_kWh());
      }

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

}
