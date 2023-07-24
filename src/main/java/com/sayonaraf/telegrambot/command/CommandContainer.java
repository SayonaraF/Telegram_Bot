package com.sayonaraf.telegrambot.command;

import com.google.common.collect.ImmutableMap;
import com.sayonaraf.telegrambot.service.SendBotMessageService;

import static com.sayonaraf.telegrambot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService messageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService))
                .put(STOP.getCommandName(), new StopCommand(messageService))
                .put(HELP.getCommandName(), new HelpCommand(messageService))
                .put(NO.getCommandName(), new NoCommand(messageService))
                .build();

        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String identifier) {
        return commandMap.getOrDefault(identifier, unknownCommand);
    }
}
