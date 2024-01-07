package com.example.demo;

import com.example.demo.telegram.BotConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;


@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		TelegramBotsApi bean = run.getBean(TelegramBotsApi.class);
		LongPollingBot bot = run.getBean(LongPollingBot.class);
		try{
			 bean.registerBot(bot);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

}
