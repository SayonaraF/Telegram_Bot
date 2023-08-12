package com.sayonaraf.telegrambot.service;

import com.sayonaraf.telegrambot.models.Channel;
import com.sayonaraf.telegrambot.models.TelegramUser;
import com.sayonaraf.telegrambot.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class SubChannelService {
    private final ChannelRepository channelRepository;
    private final TelegramUserService telegramUserService;

    @Autowired
    public SubChannelService(ChannelRepository channelRepository, TelegramUserService telegramUserService) {
        this.channelRepository = channelRepository;
        this.telegramUserService = telegramUserService;
    }

    @Transactional
    public void save(long chatId, String channelName) {
        TelegramUser telegramUser = telegramUserService.getByChatId(chatId).orElseThrow(NotFoundException::new);
        Channel channel = channelRepository.findById(channelName).orElseThrow(NotFoundException::new);

        channel.addUser(telegramUser);

        channelRepository.save(channel);
    }
}
