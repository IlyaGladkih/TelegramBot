package com.example.demo.service;

import com.example.demo.dto.Fact;

public class WeatherHandler {

    public static String windDirectionHandler(String dir){
        switch (dir){
            case "nw": return  "северо-западное";
            case "n": return  "северное";
            case "ne": return  "северо-восточное";
            case "e": return  "восточное";
            case "se": return  "юго-восточное";
            case "s": return  "южное";
            case "sw": return  "юго-западное";
            case "w": return  "западное";
            case "c": return  "штиль";
            default: return "";
        }

    }

    public static String conditionHandler(String condition){
        switch (condition){
            case "clear": return  "ясно";
            case "partly-cloudy": return  "малооблачно";
            case "cloudy": return  "облачно с прояснениями";
            case "overcast": return  "пасмурно";
            case "light-rain": return  "небольшой дождь.";
            case "rain": return  "дождь";
            case "heavy-rain": return  "сильный дождь";
            case "showers": return  "ливень";
            case "wet-snow": return  "дождь со снегом";
            case "light-snow": return  "небольшой снег";
            case "snow": return  "снег";
            case "snow-showers": return  "снегопад";
            case "hail": return  "град";
            case "thunderstorm": return  "гроза";
            case "thunderstorm-with-rain": return  "дождь с грозой";
            case "thunderstorm-with-hail": return  "гроза с градом";
            default: return "";
        }

    }
}
