package com.sayonaraf.telegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.sayonaraf.telegrambot.command.ChannelsCommand.CHANNELS_MESSAGE;
import static com.sayonaraf.telegrambot.command.CommandName.CHANNELS;

@DisplayName("Unit-level testing for ChannelsCommand")
public class ChannelsCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CHANNELS.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return CHANNELS_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new ChannelsCommand(messageService);
    }
}
