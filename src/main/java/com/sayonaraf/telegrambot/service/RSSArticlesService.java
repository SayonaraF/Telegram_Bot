package com.sayonaraf.telegrambot.service;

import com.sayonaraf.telegrambot.models.ArticleArgs;
import com.sayonaraf.telegrambot.models.Channel;
import com.sayonaraf.telegrambot.models.TelegramUser;
import com.sayonaraf.telegrambot.util.RSSReaderUtil;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RSSArticlesService {
    private final SubChannelService subChannelService;
    private final SendBotMessageService messageService;

    @Autowired
    public RSSArticlesService(SubChannelService subChannelService, SendBotMessageService messageService) {
        this.subChannelService = subChannelService;
        this.messageService = messageService;
    }


    public void findNewArticles() {
        List<Channel> channels = subChannelService.getChannelLinks();

        for (Channel channel : channels) {
            sendNewArticles(channel);
        }
    }

    private void sendNewArticles(Channel channel) {
        List<ArticleArgs> args = findNewPosts(channel.getLink(), channel.getLastLink());

        List<String> messages = new ArrayList<>();
        for (ArticleArgs articleArgs : args) {
            String message = String.format("Вышла новая статья на сайте %s.\n\n" +
                    "Описание: %s\n\n" +
                    "Ссылка: %s", articleArgs.getChannelLink(), articleArgs.getTitle(),
                    articleArgs.getLink());
            messages.add(message);
        }

        // TODO: переписать блок кода в нормальном виде
        List<TelegramUser> users = channel.getUsers();
        for (TelegramUser user : users) {
            if (user.isActive()) {
                for (String message : messages) {
                    messageService.sendMessage(user.getChatId(), message);
                }
            }
        }

        if (!args.isEmpty()) {
            channel.setLastLink(args.get(0).getLink());
            subChannelService.save(channel);
        }
    }

    public static List<ArticleArgs> findNewPosts(String source, String lastLink) {
        List<ArticleArgs> articleArgs = getRSS(source);

        List<ArticleArgs> result = new ArrayList<>();
        for (ArticleArgs args : articleArgs) {
            if (!args.getLink().equalsIgnoreCase(lastLink)) {
                result.add(args);
            } else {
                break;
            }
        }

        return result;
    }

    private static List<ArticleArgs> getRSS(String source) {
        List<ArticleArgs> articles;
        try {
            articles = RSSReaderUtil.read(source);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }

        return articles;
    }
}
