package com.example.demo.telegram;

import com.example.demo.actions.Action;
import com.example.demo.actions.PrepareAction;
import com.example.demo.config.TelegramBotConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Set;


@Component
public class BotConnect extends TelegramLongPollingBot {

    private TelegramBotConfig config;

    private boolean today = false;
    private boolean toworrow = false;
    private boolean week = false;

    private PrepareAction prepareAction;

    private Map<String, Action> actionMap;

    private Set<String> set= Set.of("/today","/tomorrow","/week");


    @Autowired
    public BotConnect(PrepareAction prepareAction, TelegramBotConfig config, DefaultBotOptions options) {
        super(options, config.getBotToken());
        this.config = config;
        this.prepareAction = prepareAction;
    }

    @PostConstruct
    private void init(){
       actionMap =  prepareAction.prepareAnsverMap();
    }


    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            Message message = update.getMessage();
            Long id = message.getChat().getId();

            if(message.hasText()){
                String text = message.getText();
                if (set.contains(text)) sendButton(text,id);
                else {
                    Action action = actionMap
                            .getOrDefault(text, (a , b) -> "Используйте комманду /hello для получения списка доступных комманд");
                    sendMessage(new SendMessage(String.valueOf(id),action.makeAction(0,0)));
                }
            }else if (message.hasLocation()) locationAnswer(message,id);
        }

    }

    private void sendMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        super.clearWebhook();

    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    private void locationAnswer(Message message, long id){
        double latitude = message.getLocation().getLatitude();
        double longitude = message.getLocation().getLongitude();
        if (today){
            today = false;
            sendMessage(new SendMessage(
                    String.valueOf(id),
                    actionMap.get("/today").makeAction(latitude,longitude)));
        }else if (toworrow){
            toworrow = false;
            sendMessage(new SendMessage(
                    String.valueOf(id),
                    actionMap.get("/tomorrow").makeAction(latitude,longitude)));
        }else if (week) {
            week = false;
            sendMessage(new SendMessage(
                    String.valueOf(id),
                    actionMap.get("/week").makeAction(latitude, longitude)));
        }
    }

    private void sendButton(String text, long id){
        switch (text){
            case ("/today")->{today = true;}
            case ("/tomorrow")->{toworrow = true;}
            case ("/week")->{week = true;}
        }
        SendMessage message1 = new SendMessage(String.valueOf(id), "Скажи где ты");
        message1.setReplyMarkup(setButton());
        sendMessage(message1);
    }

    public ReplyKeyboardMarkup setButton( ) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("Поделиться своим местоположением");
        button.setRequestLocation(true);

        keyboardFirstRow.add(button);

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

}
