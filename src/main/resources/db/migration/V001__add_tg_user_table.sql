-- ensure that the table with this name doesn't exist
DROP TABLE IF EXISTS tg_user;

-- Create tg_user table
CREATE TABLE tg_user (
    chat_id BIGINT,
    active BOOLEAN,
    PRIMARY KEY (chat_id)
);