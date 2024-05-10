package com.alura.challenge;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultRates {
    public Rate consultRateExchange(String base, String target){
        URI link = URI.create("https://v6.exchangerate-api.com/v6/ddd654e0a3fb3e6bb31396a5/pair/EUR/GBP"+base+"/"+target);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://foo.com/"))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Rate.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar la conversión para las monedas ingresadas");
        }
    }




}
