package com.sayonaraf.telegrambot.bot;

import com.sayonaraf.telegrambot.command.CommandContainer;
import com.sayonaraf.telegrambot.service.RSSReaderService;
import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sayonaraf.telegrambot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    @Value("${bot.name}")
    private String botName;

    @Autowired
    public TelegramBot(@Value("${bot.token}") String botToken, TelegramUserService userService, RSSReaderService readerService) {
        super(botToken);
        this.commandContainer = new CommandContainer(userService, new SendBotMessageService(this), readerService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String identifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(identifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
