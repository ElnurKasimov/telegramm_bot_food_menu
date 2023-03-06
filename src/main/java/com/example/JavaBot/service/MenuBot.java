package com.example.JavaBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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


        if ("/start".equals(message.getText())) {
            sendMessage.enableMarkdown(true);
            ReplyKeyboardMarkup replyKeyboardMarkup = getMenuKeyboard();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText("Welcome to Receipt Bot! Please chose meal of the day:\n");
        }

        if ("Breakfast".equals(message.getText())) {
            String menu = "Breakfast menu:\n";
            menu+="1. Banana-Nut Smothie\n";
            menu+="2. Omlet + Greens\n";
            menu+="3. Avocado Crispy Eggs Toast";
            sendMessage.setText(menu);
        }
        if ("Dinner".equals(message.getText())) {
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

    private ReplyKeyboardMarkup getMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Breakfast");
        keyboardRow.add("Dinner");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Lunch");
        keyboardSecondRow.add("Supper");

        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;

    }


}
