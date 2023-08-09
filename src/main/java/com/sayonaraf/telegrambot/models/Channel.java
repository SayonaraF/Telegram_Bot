package com.sayonaraf.telegrambot.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Table(name = "channels", schema = "dev-bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Channel {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
    @Column(name = "last_link")
    private String lastLink;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "channel_x_user",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<TelegramUser> users;

    public void addUser(TelegramUser telegramUser) {
        if (isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(telegramUser);
    }
}
