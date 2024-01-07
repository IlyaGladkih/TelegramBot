package com.example.demo.config;

import com.example.demo.actions.Action;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
public class WeatherConfig {

    @Value("${weather.service.url}")
    private String url;

    @Value("${weather.service.header}")
    private String header;

    @Value("${weather.service.token}")
    private String token;

    @Bean
    @Scope(value = "prototype")
    public RestTemplate restTemplate(){
        RestTemplate rest = new RestTemplateBuilder().defaultHeader(header,token).build();
        rest.getMessageConverters().add(0,mappingJacksonHttpMessageConverter());
        return rest;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    @Bean
    public Map<String, Action> setUpMapping(){
        return new HashMap<>();
    }
}
