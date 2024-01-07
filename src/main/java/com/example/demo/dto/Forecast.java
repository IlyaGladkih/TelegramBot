package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Forecast {
    private Date date;
    private int week;
    private Parts parts;

}
