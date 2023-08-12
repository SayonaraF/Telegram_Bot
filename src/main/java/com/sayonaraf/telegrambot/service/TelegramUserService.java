package com.sayonaraf.telegrambot.service;

import com.sayonaraf.telegrambot.models.TelegramUser;
import com.sayonaraf.telegrambot.repository.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserService {
    private final TelegramUserRepository userRepository;

    @Autowired
    public TelegramUserService(TelegramUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(TelegramUser telegramUser) {
        userRepository.save(telegramUser);
    }

    public Optional<TelegramUser> getByChatId(Long chatId) {
        return userRepository.findById(chatId);
    }

    public List<TelegramUser> getAllActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }
}
