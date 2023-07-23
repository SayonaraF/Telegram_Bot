package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {
    private final SendBotMessageService messageService;
    private final static String UNKNOWN_MESSAGE = "Я не знаю такой команды, напишите /help";

    public UnknownCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), UNKNOWN_MESSAGE);
    }
}
