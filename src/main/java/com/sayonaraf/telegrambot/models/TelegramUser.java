package com.sayonaraf.telegrambot.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tg_user", schema = "dev-bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TelegramUser {
    @Id
    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "active")
    private boolean active;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Channel> channels;
}
