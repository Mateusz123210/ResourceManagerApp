--liquibase formatted sql
--changeset mateusz:1
INSERT INTO users(type, creation_time, modification_time, name, nick, surname)  VALUES (1, '2023-07-21 16:18:08.162511', '2023-07-21 16:18:08.162511', 'Ja', 'A', 'B');