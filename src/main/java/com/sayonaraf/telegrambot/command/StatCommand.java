package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command {
    private final SendBotMessageService messageService;
    private final TelegramUserService userService;
    public final static String STAT_MESSAGE = "Количество активных пользователей: %s";

    public StatCommand(SendBotMessageService messageService, TelegramUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        int activeUser = userService.getAllActiveUsers().size();
        messageService.sendMessage(update.getMessage().getChatId(), String.format(STAT_MESSAGE, activeUser));
    }
}
