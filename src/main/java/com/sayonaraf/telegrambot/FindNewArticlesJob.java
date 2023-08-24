package com.sayonaraf.telegrambot;

import com.sayonaraf.telegrambot.service.RSSArticlesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewArticlesJob {

    private final RSSArticlesService articlesService;

    @Autowired
    public FindNewArticlesJob(RSSArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @Scheduled(fixedRateString = "${bot.updateTime}")
    public void findNewArticles() {

        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started");

        articlesService.findNewArticles();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new article job ended. Took time: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
