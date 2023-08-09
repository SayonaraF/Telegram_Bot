package com.sayonaraf.telegrambot.service;

import com.sayonaraf.telegrambot.models.ArticleArgs;
import com.sayonaraf.telegrambot.util.RSSReaderUtil;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RSSReaderService {
    @Value("${source}")
    private String source;

    public ArticleArgs getRSS() {
        List<ArticleArgs> articles;
        try {
            articles = RSSReaderUtil.read(source);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }

        return articles.get(0);
    }
}
