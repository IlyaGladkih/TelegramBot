package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDTO {
    private String now;
    private Fact fact;
    private List<Forecast> forecasts;
    private Geo_object geo_object;
}
