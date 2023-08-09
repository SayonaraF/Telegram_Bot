package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.RSSReaderService;
import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService messageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService userService = Mockito.mock(TelegramUserService.class);
        RSSReaderService readerService = Mockito.mock(RSSReaderService.class);
        commandContainer = new CommandContainer(userService, messageService, readerService);
    }

    @Test
    public void shouldGetAllTheExistingCommand() {
        for (CommandName commandName : CommandName.values()) {
            Command command = commandContainer.retrieveCommand(commandName.getCommandName());
            Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
        }
    }

    @Test
    public void shouldReturnUnknownCommand() {
        String wrongCommand = "/asdmas";
        Command command = commandContainer.retrieveCommand(wrongCommand);

        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
