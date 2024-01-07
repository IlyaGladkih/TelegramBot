package com.example.demo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Fact {

    private int temp;
    private int feels_like;
    private int wind_speed;
    private String wind_dir;
    private int pressure_mm;
    private String condition;


}
