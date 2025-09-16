package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.PriceData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetPrices {
  private static final String API_URL_PATTERN = "https://www.elprisetjustnu.se/api/v1/prices/%d/%s_%s.json";
  private static final String DEFAULT_ZONE = "SE3"; // Stockholm

  private final HttpClient client;
  private final ObjectMapper objectMapper;
  private final String zoneId;

  public GetPrices() {
    this(DEFAULT_ZONE);
  }

  public GetPrices(String zoneId) {
    this.client = HttpClient.newHttpClient();
    this.objectMapper = new ObjectMapper();
    this.zoneId = zoneId;
  }

  public List<PriceData> findAllPrices() {
    List<PriceData> allPrices = new ArrayList<>();

    try {
      // Get today's prices
      LocalDate today = LocalDate.now();
      List<PriceData> todayPrices = fetchPricesForDate(today);
      allPrices.addAll(todayPrices);

      // --get tomorrow's price if available--
      try {
        LocalDate tomorrow = today.plusDays(1);
        List<PriceData> tomorrowPrices = fetchPricesForDate(tomorrow);
        allPrices.addAll(tomorrowPrices);
      } catch (Exception e) {
        System.out.println("Tomorrow's prices not yet available.");
      }

      return allPrices;

    } catch (Exception e) {
      throw new RuntimeException("Error fetching price data: " + e.getMessage(), e);
    }
  }

  private List<PriceData> fetchPricesForDate(LocalDate date) {
    try {
      String dateStr = date.format(DateTimeFormatter.ofPattern("MM-dd"));
      String url = String.format(API_URL_PATTERN,
          date.getYear(), dateStr, zoneId);

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .GET()
          .build();

      HttpResponse<String> response = client.send(request,
          HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        TypeReference<List<PriceData>> typeReference = new TypeReference<List<PriceData>>() {
        };
        return objectMapper.readValue(response.body(), typeReference);
      } else {
        throw new RuntimeException("Failed to fetch data for " + date + " with status code: " + response.statusCode());
      }

    } catch (Exception e) {
      throw new RuntimeException("Error fetching price data for " + date + ": " + e.getMessage(), e);
    }
  }

  // --Legacy method for backward compatibility--
  public String findAll() {
    try {
      LocalDate today = LocalDate.now();
      String dateStr = today.format(DateTimeFormatter.ofPattern("MM-dd"));
      String url = String.format(API_URL_PATTERN,
          today.getYear(), dateStr, zoneId);

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
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
  }
}
