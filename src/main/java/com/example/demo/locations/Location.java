package com.example.demo.locations;

import lombok.Getter;

@Getter
public enum Location {

    EKATERINBURG(60.612201f,56.851901f);

    private float lon;

    private float lat;

    Location(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }


}
