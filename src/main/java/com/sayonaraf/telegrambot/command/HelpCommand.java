package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sayonaraf.telegrambot.command.CommandName.*;

public class HelpCommand implements Command {
    private final SendBotMessageService messageService;
    public final static String HELP_MESSAGE = String.format("Доступные команды:\n\n"
                                    + "Включение/Выключение:"
                                    + "%s - начать работу с ботом\n"
                                    + "%s - закончить работу с ботом\n\n"
                                    + "Подписки:\n"
                                    + "%s - выбрать подписки\n\n"
                                    + "%s - получить помощь\n"
                                    + "%s - статистика пользователей", START.getCommandName(),
                                    STOP.getCommandName(), SUB_CHANNEL.getCommandName(), HELP.getCommandName(), STAT.getCommandName());

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}
