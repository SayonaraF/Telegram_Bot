package com.sayonaraf.telegrambot.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tg_user", schema = "dev-bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "active")
    private boolean active;

}
