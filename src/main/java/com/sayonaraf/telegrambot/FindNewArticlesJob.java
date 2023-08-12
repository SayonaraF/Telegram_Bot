package com.sayonaraf.telegrambot;

import com.sayonaraf.telegrambot.service.RSSArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FindNewArticlesJob {

    private final RSSArticlesService articlesService;

    @Autowired
    public FindNewArticlesJob(RSSArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @Scheduled(fixedRateString = "${bot.updateTime}")
    public void findNewArticles() {

        // TODO: добавить логи
        articlesService.findNewArticles();
    }
}
