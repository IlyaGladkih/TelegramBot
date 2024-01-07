package com.example.demo.actions;

import com.example.demo.annotations.MappedAt;
import com.example.demo.config.WeatherConfig;
import com.example.demo.dto.WeatherDTO;
import com.example.demo.locations.Location;
import com.example.demo.service.WeatherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@MappedAt(mapping = "/today")
public class TodayAction implements Action{

    private RestTemplate restTemplate;

    private WeatherConfig weatherConfig;

    @Autowired
    public TodayAction(RestTemplate restTemplate, WeatherConfig weatherConfig) {
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }
    @Override
    public String makeAction(double lat, double lon) {
        WeatherDTO dto = restTemplate.getForObject(weatherConfig.getUrl()
                , WeatherDTO.class, lat, lon, 1);
        return "По данным Яндекс.Погоды \nПогода в:\n" +
                "Страна:" + dto.getGeo_object().getCountry().getName() + "\nРегион: " + dto.getGeo_object().getProvince().getName()
                + "\nНаселенный пункт: " + dto.getGeo_object().getLocality().getName() + "\n" +
                "Погода: "+ WeatherHandler.conditionHandler(dto.getFact().getCondition())+"\n" +
                "Температура составляет: "+dto.getFact().getTemp()+"\n" +
                "Ощущяется как: "+dto.getFact().getFeels_like()+"\n" +
                "Атмосферное давление составляет: "+dto.getFact().getPressure_mm()+" миллиметров ртутного столба\n" +
                "Направление ветра: " + WeatherHandler.windDirectionHandler(dto.getFact().getWind_dir()) +"\n" +
                "Скорость ветра: " + dto.getFact().getWind_speed() + " м/с";
    }
}
