package com.sayonaraf.telegrambot.models;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleArgs {
    private String title;
    private String link;
    private String description;
    private Date date;
    private String channelLink;
}
