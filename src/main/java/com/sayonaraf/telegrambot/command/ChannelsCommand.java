package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ChannelsCommand implements Command {
    private final SendBotMessageService messageService;
    public final static String CHANNELS_MESSAGE = "Выбор каналов";

    public ChannelsCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), CHANNELS_MESSAGE);
    }
}
