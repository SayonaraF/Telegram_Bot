package com.sayonaraf.telegrambot.command;

import com.sayonaraf.telegrambot.service.SendBotMessageService;
import com.sayonaraf.telegrambot.service.SubChannelService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sayonaraf.telegrambot.command.CommandName.SUB_CHANNEL;

public class SubChannelCommand implements Command {
    private final SendBotMessageService messageService;
    private final SubChannelService subChannelService;
    public static final String SUB_CHANNEL_MESSAGE = "Команда";

    public SubChannelCommand(SendBotMessageService messageService, SubChannelService subChannelService) {
        this.messageService = messageService;
        this.subChannelService = subChannelService;
    }

    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().equalsIgnoreCase(SUB_CHANNEL.getCommandName())) {
            sendChannelNamesList(update.getMessage().getChatId());
        }

        String channelName = update.getMessage().getText().split(" ")[1].toUpperCase();

        List<String> channelNames = new ArrayList<>();
        for (ChannelName name : ChannelName.values()) {
            channelNames.add(name.toString());
        }

        String message;
        long chatId = update.getMessage().getChatId();
        if (channelNames.contains(channelName)) {
            subChannelService.save(chatId, channelName);

            message = "Вы подписались на канал: " + channelName;
        } else {
            message = "В моем списке нет такого канала.";
        }

        messageService.sendMessage(update.getMessage().getChatId(), message);
    }

    private void sendChannelNamesList(Long chatId) {
        String message = "Чтобы подписаться на RSS канал передайте определенную команду вместе с названием канала.\n\n" +
                "Пример: '/subChannel RBK'\n\n" +
                "Вот список всех каналов, которые у меня есть (в дальнейшем он будет больше):\n\n" +
                "%s";
        String channelsList = Arrays.stream(ChannelName.values()).map(name -> String.format("%s - %s\n", name, name.getChannelName()))
                .collect(Collectors.joining());

        messageService.sendMessage(chatId, String.format(message, channelsList));
    }
}
