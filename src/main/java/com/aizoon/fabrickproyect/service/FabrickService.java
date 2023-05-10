package com.aizoon.fabrickproyect.service;

import com.aizoon.fabrickproyect.entity.DTO.BonificoDTO;
import com.aizoon.fabrickproyect.entity.DTO.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



@Service
public class FabrickService implements IFabrickService{

    private final HttpClient client;

    @Value("${url.saldo}") //https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/
    private String urlBanking;

    @Value("${api.key}")
    private String apiKey;

    public FabrickService(HttpClient client) {
        this.client = client;
    }


    @Override
    public ResponseDTO getSaldo(Long accountId){

        try {

            String url = urlBanking+accountId.toString()+"/balance";
            HttpResponse<String> response = getRequest(url);
            ObjectMapper mapper = new ObjectMapper ();
            return mapper.readValue(response.body(), ResponseDTO.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO bonifico(Long accountId,BonificoDTO bonifico) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

            String json = writer.writeValueAsString(bonifico);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlBanking+accountId+"/payments/money-transfers"))
                    .headers("Content-Type", "application/json",
                            "Auth-Schema", "S2S",
                            "Api-Key", apiKey,
                            "X-Time-Zone","Europe/Rome")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), ResponseDTO.class);

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO transazioni(Long accountId, String fromDate, String toDate) {
        try {

            String params = "/transactions?fromAccountingDate="+fromDate+"&toAccountingDate="+toDate;
            String url = urlBanking+accountId.toString()+params;

            HttpResponse<String> response = getRequest(url);
            ObjectMapper mapper = new ObjectMapper ();
            return mapper.readValue(response.body(), ResponseDTO.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getRequest(String url){

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .headers("Content-Type", "application/json",
                            "Auth-Schema", "S2S",
                            "Api-Key", apiKey)
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
