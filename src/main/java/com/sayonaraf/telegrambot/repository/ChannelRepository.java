package com.sayonaraf.telegrambot.repository;

import com.sayonaraf.telegrambot.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
