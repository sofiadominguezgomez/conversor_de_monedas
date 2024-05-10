package com.alura.challenge;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultRates {
    public Rate consultRateExchange(String base, String target){

        URI link = URI.create("https://v6.exchangerate-api.com/v6/ddd654e0a3fb3e6bb31396a5/pair/" + base + "/" + target);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(link)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Rate.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar la conversión para las monedas ingresadas");
        }
    }


    public String[][] getSupportedCodes() {
        URI link = URI.create("https://v6.exchangerate-api.com/v6/ddd654e0a3fb3e6bb31396a5/codes");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(link)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            Gson gson = new Gson();
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);

            // Extract supported currency codes
            String[][] supportedCodes = new String[apiResponse.supported_codes.length][2];
            for (int i = 0; i < apiResponse.supported_codes.length; i++) {
                supportedCodes[i][0] = apiResponse.supported_codes[i][0];
                supportedCodes[i][1] = apiResponse.supported_codes[i][1];
            }

            return supportedCodes;
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar la conversión para las monedas ingresadas");
        }
    }

    private static class ApiResponse {
        String[][] supported_codes;
    }
}
