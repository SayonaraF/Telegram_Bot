-- ensure that the tables with these name are removed
DROP TABLE IF EXISTS channel_x_user;
DROP TABLE IF EXISTS channels;

CREATE TABLE channels(
    name VARCHAR(300),
    link VARCHAR(300),
    last_link VARCHAR(300),
    PRIMARY KEY (name)
);

CREATE TABLE channel_x_user(
    channel_id VARCHAR(300) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (channel_id) REFERENCES channels(name),
    FOREIGN KEY (user_id) REFERENCES tg_user(chat_id),
    UNIQUE (channel_id, user_id)
)
