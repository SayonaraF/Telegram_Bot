package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {
    private final SendBotMessageService messageService;
    public final static String STOP_MESSAGE = "Я прекращаю свою работу";

    public StopCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), STOP_MESSAGE);
    }
}
