package com.aizoon.fabrickproyect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class Config {

    @Bean
    public HttpClient CloseableHttpClient  () {
        return HttpClient.newHttpClient();
    }

}
