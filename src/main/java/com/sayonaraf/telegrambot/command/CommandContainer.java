package com.sayonaraf.telegrambot.command;

import com.google.common.collect.ImmutableMap;
import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.SubChannelService;
import com.sayonaraf.telegrambot.service.TelegramUserService;

import static com.sayonaraf.telegrambot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(TelegramUserService userService, SendBotMessageService messageService, SubChannelService subChannelService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService, userService))
                .put(STOP.getCommandName(), new StopCommand(messageService, userService))
                .put(SUB_CHANNEL.getCommandName(), new SubChannelCommand(messageService, subChannelService))
                .put(HELP.getCommandName(), new HelpCommand(messageService))
                .put(STAT.getCommandName(), new StatCommand(messageService, userService))
                .put(NO.getCommandName(), new NoCommand(messageService))
                .build();

        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String identifier) {
        return commandMap.getOrDefault(identifier, unknownCommand);
    }
}
