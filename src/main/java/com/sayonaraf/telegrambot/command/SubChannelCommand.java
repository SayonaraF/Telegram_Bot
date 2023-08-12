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

    public SubChannelCommand(SendBotMessageService messageService, SubChannelService subChannelService) {
        this.messageService = messageService;
        this.subChannelService = subChannelService;
    }

    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().equalsIgnoreCase(SUB_CHANNEL.getCommandName())) {
            sendChannelNamesList(update.getMessage().getChatId());
        }

        if (update.getMessage().getText().split(" ").length > 1) {
            String[] query = update.getMessage().getText().split(" ");
            subCurrentChannel(update.getMessage().getChatId(), query);
        }
    }

    private void subCurrentChannel(Long chatId, String[] query) {
        List<String> channelNames = new ArrayList<>();
        for (ChannelName name : ChannelName.values()) {
            channelNames.add(name.toString());
        }

        String channelNameFromQuery = query[1].toUpperCase();
        String message;
        if (channelNames.contains(channelNameFromQuery)) {
            subChannelService.save(chatId, channelNameFromQuery);

            message = "Вы подписались на канал: " + channelNameFromQuery;
        } else {
            message = "В моем списке нет такого канала.";
        }

        messageService.sendMessage(chatId, message);
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
