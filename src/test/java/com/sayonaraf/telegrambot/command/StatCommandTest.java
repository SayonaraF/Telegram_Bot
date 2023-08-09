package com.sayonaraf.telegrambot.command;

import static com.sayonaraf.telegrambot.command.CommandName.STAT;
import static com.sayonaraf.telegrambot.command.StatCommand.STAT_MESSAGE;

public class StatCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(messageService, userService);
    }
}
