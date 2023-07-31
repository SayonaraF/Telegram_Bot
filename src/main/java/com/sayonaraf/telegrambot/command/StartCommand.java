package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.models.TelegramUser;
import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public class StartCommand implements Command {
    private final SendBotMessageService messageService;
    private final TelegramUserService userService;
    public final static String START_MESSAGE = "Я начал свою работу";

    public StartCommand(SendBotMessageService messageService, TelegramUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        Optional<TelegramUser> userOptional = userService.retrieveByChatId(chatId);

        TelegramUser user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new TelegramUser();
            user.setChatId(chatId);
        }

        user.setActive(true);
        userService.save(user);

        messageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}
