package com.sayonaraf.telegrambot.command;

public enum ChannelName {

    RBK("rbk.ru"),
    LENTA("lenta.ru"),
    SPORTS("sports.ru");

    private final String channelName;

    ChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
