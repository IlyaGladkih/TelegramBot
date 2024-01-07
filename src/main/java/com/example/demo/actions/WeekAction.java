package com.example.demo.actions;

import com.example.demo.annotations.MappedAt;
import com.example.demo.config.WeatherConfig;
import com.example.demo.dto.Forecast;
import com.example.demo.dto.WeatherDTO;
import com.example.demo.locations.Location;
import com.example.demo.service.WeatherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;

@Component
@MappedAt(mapping = "/week")
public class WeekAction implements Action{

    private RestTemplate restTemplate;

    private WeatherConfig weatherConfig;

    @Autowired
    public WeekAction(RestTemplate restTemplate, WeatherConfig weatherConfig) {
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }
    @Override
    public String makeAction(double lat, double lon) {
        WeatherDTO dto = restTemplate.getForObject(weatherConfig.getUrl(),
                WeatherDTO.class, lat, lon, 7);
        StringBuilder sb = new StringBuilder("По данным Яндекс.Погоды:\n\n" + "Погода в \nСтрана:" + dto.getGeo_object().getCountry().getName()
                + "\nРегион: " + dto.getGeo_object().getProvince().getName()
                + "\nНаселенный пункт: " + dto.getGeo_object().getLocality().getName() + "\n");
        for(Forecast forecast:dto.getForecasts()){
            sb.append(prepareAnswer(forecast));
        }
        return sb.toString();
    }

    private String prepareAnswer(Forecast forecast){
        return  "Погода на " + forecast.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString() + "\n"+
                "День недели: " + forecast.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek()+ "\n\n" +
                "Денём: \n"+
                "Погода: "+ WeatherHandler.conditionHandler(forecast.getParts().getDay_short().getCondition())+"\n" +
                "Средняя температура составит: "+forecast.getParts().getDay_short().getTemp()+"\n" +
                "Минимальная температура составит: "+forecast.getParts().getDay_short().getTemp_min()+"\n" +
                "Атмосферное давление составит: "+forecast.getParts().getDay_short().getPressure_mm()+" миллиметров ртутного столба\n" +
                "Направление ветра: " + WeatherHandler.windDirectionHandler(forecast.getParts().getDay_short().getWind_dir()) +"\n" +
                "Скорость ветра: " + forecast.getParts().getDay_short().getWind_speed() + " м/с"+ "\n" + "\n" +
                "Ночью: "+  "\n"+
                "Погода: "+ WeatherHandler.conditionHandler(forecast.getParts().getNight_short().getCondition())+"\n" +
                "Средняя температура составит: "+forecast.getParts().getNight_short().getTemp()+"\n" +
                "Атмосферное давление составит: "+forecast.getParts().getNight_short().getPressure_mm()+" миллиметров ртутного столба\n" +
                "Направление ветра: " + WeatherHandler.windDirectionHandler(forecast.getParts().getNight_short().getWind_dir()) +"\n" +
                "Скорость ветра: " + forecast.getParts().getNight_short().getWind_speed() + " м/с\n\n";
    }
}
