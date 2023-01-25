INSERT INTO tb_role(authority) VALUES ('VISITOR');
INSERT INTO tb_role(authority) VALUES ('MEMBER');

INSERT INTO tb_user(name, email, password) VALUES ('Bob','bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user(name, email, password) VALUES ('Ana','ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 2);

INSERT INTO tb_genre(name) VALUES ('Ação');

INSERT INTO tb_movie(title, sub_title, year, img_url, synopsis, genre_id) VALUES ('O grande dragão branco', 'Torneio de luta em hong kong', 1988, 'https://m.media-amazon.com/images/I/51PgfcykceL._AC_SY580_.jpg', 'O soldado americano Frank Dux vai a Hong Kong para ser aceito no Kumite, uma competição de artes marciais altamente secreta e extremamente violenta. Enquanto tenta ganhar acesso ao mundo subterrâneo dos combatentes clandestinos, ele também tem que fugir dos oficiais militares que o consideram um desertor. Depois de suportar um treinamento difícil e começar um romance com a jornalista Janice Kent, é dada a Frank a oportunidade de lutar.', 1);

INSERT INTO tb_review(text, user_id, movie_id) VALUES ('Ótimo filme', 2, 1);