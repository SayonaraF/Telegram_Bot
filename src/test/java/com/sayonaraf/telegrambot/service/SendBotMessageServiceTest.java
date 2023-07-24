package com.sayonaraf.telegrambot.service;

import com.sayonaraf.telegrambot.bot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
    private TelegramBot telegramBot;
    private SendBotMessageService messageService;

    @BeforeEach
    public void init() {
        this.telegramBot = Mockito.mock(TelegramBot.class);
        this.messageService = new SendBotMessageService(telegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        long chatId = 123;
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        messageService.sendMessage(chatId, message);

        Mockito.verify(telegramBot).execute(sendMessage);
    }

}
