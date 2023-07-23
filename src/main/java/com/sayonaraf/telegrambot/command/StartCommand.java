package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    private final SendBotMessageService messageService;
    private final static String START_MESSAGE = "Я начал свою работу";

    public StartCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}
