package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sayonaraf.telegrambot.command.CommandName.*;

public class HelpCommand implements Command {
    private final SendBotMessageService messageService;
    private final static String HELP_MESSAGE = String.format("Доступные команды:\n\n"
                                    + "%s - начать работу со мной\n"
                                    + "%s - закончить работу со мной\n"
                                    + "%s - получить помощь\n", START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}
