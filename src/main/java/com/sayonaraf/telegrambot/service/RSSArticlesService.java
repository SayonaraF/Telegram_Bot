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
        List<ArticleArgs> newPosts = findNewPosts(channel.getLink(), channel);

        List<String> messages = new ArrayList<>();
        for (ArticleArgs post : newPosts) {
            String message = String.format("Вышла новая статья на сайте %s.\n\n" +
                    "Описание: %s\n" +
                            "%s\n\n" +
                    "Ссылка: %s\n", post.getChannelLink(), post.getTitle(), post.getDescription(),
                    post.getLink());
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

        if (!newPosts.isEmpty()) {
            channel.setLastLink(newPosts.get(0).getLink());
            channel.setPubDate(newPosts.get(0).getDate());
            subChannelService.save(channel);
        }
    }

    public static List<ArticleArgs> findNewPosts(String source, Channel channel) {
        List<ArticleArgs> allPosts = getRSS(source);

        List<ArticleArgs> newPosts = new ArrayList<>();
        for (ArticleArgs post : allPosts) {
            if (!post.getLink().equalsIgnoreCase(channel.getLastLink()) &&
                    post.getDate().after(channel.getPubDate())) {
                newPosts.add(post);
            } else {
                break;
            }
        }

        return newPosts;
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
