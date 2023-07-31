package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.models.TelegramUser;
import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public class StopCommand implements Command {
    private final SendBotMessageService messageService;
    private final TelegramUserService userService;
    public final static String STOP_MESSAGE = "Я прекращаю свою работу";

    public StopCommand(SendBotMessageService messageService, TelegramUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId(), STOP_MESSAGE);
        Optional<TelegramUser> userOptional = userService.retrieveByChatId(update.getMessage().getChatId().toString());

        if (userOptional.isPresent()) {
            TelegramUser user = userOptional.get();
            user.setActive(false);
            userService.save(user);
        }
    }
}
