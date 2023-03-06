package com.example.JavaBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MenuBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "menu_receipt_bot";
    }

    @Override
    public String getBotToken() {
        return "6247224664:AAHERbcx1Qz_m7Pd-_gsOY1kqBLmW-CpLb0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println("Message received - " + "\"" + message.getText() + "\"");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello user! I've received Your message " + "\"" + message.getText() + "\"");
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        if ("breakfast".equals(message.getText())) {
            String menu = "Breakfast menu:\n";
            menu+="1. Banana-Nut Smothie\n";
            menu+="2. Omlet + Greens\n";
            menu+="3. Avocado Crispy Eggs Toast";
            sendMessage.setText(menu);
        }
        if ("dinner".equals(message.getText())) {
            String menu = "Dinner menu:\n";
            menu+="1. Creamy Lemon Chgiken Pasta\n";
            menu+="2. Turkey Tacos\n";
            menu+="3. Vegetarian Lasagna";
            sendMessage.setText(menu);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

            e.printStackTrace();
        }
    }
}
