INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO tb_course(name, img_uri, img_gray_uri) VALUES ('Bootscamp HTML', 'http://www.enoisnafita.com.br/wp-content/uploads/2021/05/banner-curso-online-1.png', 'https://cdn-wordpress-info.futurelearn.com/wp-content/uploads/how-to-start-an-online-course-606x303.jpg.optimal.jpg');

INSERT INTO tb_offer(edition, start_moment, end_moment, course_id) VALUES('1.0', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-09-15T09:50:07.12345Z', 1);
INSERT INTO tb_offer(edition, start_moment, end_moment, course_id) VALUES('1.0', TIMESTAMP WITH TIME ZONE '2021-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2022-01-04T09:50:07.12345Z', 1);

INSERT INTO tb_notification(text, moment, read, route, user_id) VALUES('NOTIFICAÇÃO DE COMPRA DO BOOTCAMP', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', true, '/notification', 1);