package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {
    private final SendBotMessageService messageService;
    protected final static String NO_COMMAND = "Я понимаю команды, которые начинаются со слеша (/).\n"
                            + "Чтобы посмотреть список команд введите /help";

    public NoCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), NO_COMMAND);
    }
}
