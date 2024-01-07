package com.example.demo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Day_short{
    private int temp;
    private int temp_min;
    private int wind_speed;
    private String wind_dir;
    private int pressure_mm;
    private String condition;
}
