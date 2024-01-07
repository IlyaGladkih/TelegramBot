package com.example.demo.actions;

import com.example.demo.annotations.MappedAt;
import org.springframework.stereotype.Component;

@Component
@MappedAt(mapping = "/hello")
public class HelloAction implements Action{

    @Override
    public String makeAction(double lat, double lon) {
        return "Вы можете использовать следующие команды:\n" +
                "/today_EKB - прогноз погоды в Екатеринбурге на сегняшний день\n" +
                "/tomorrow_EKB - прогноз погоды в Екатеринбурге на завтрашний день\n" +
                "/week_EKB - прогноз погоды в Екатеринбурге на неделю\n" +
                "/today - прогноз погоды по вашему местоположению (Требуется подтверждение местоположения)\n " +
                "/tomorrow - прогноз погоды на завтра по вашему местоположению (Требуется подтверждение местоположения)\n " +
                "/week - прогноз погоды на неделю по вашему местоположению (Требуется подтверждение местоположения) ";
    }
}
