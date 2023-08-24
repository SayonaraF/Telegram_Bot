package com.sayonaraf.telegrambot.util;

import com.sayonaraf.telegrambot.models.ArticleArgs;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSReaderUtil {

    public static List<ArticleArgs> read(String feedUrl) throws IOException, FeedException {
        URL url = new URL(feedUrl);
        
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(url));
        List<ArticleArgs> results = new ArrayList<>();
        
        for (Object o : syndFeed.getEntries()) {
            results.add(mapToArticle((SyndEntry) o, syndFeed));
        }

        return results;
    }

    private static ArticleArgs mapToArticle(SyndEntry syndEntry, SyndFeed syndFeed) {
        ArticleArgs articleArgs = new ArticleArgs();

        articleArgs.setTitle(syndEntry.getTitle());
        articleArgs.setLink(syndEntry.getLink());
        articleArgs.setDescription(syndEntry.getDescription().getValue());
        articleArgs.setDate(syndEntry.getPublishedDate());
        articleArgs.setChannelLink(syndFeed.getLink());

        return articleArgs;
    }
}
