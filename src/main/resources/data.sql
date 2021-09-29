INSERT INTO USER(name, email, password) VALUES('USER', 'user@email.com', '$2a$10$cZLj.o.teXSzSBeXhJrnPOoSQ/o8Qj9G6rm4GXVev92g1yHggVJMS');
INSERT INTO USER(name, email, password) VALUES('ADMIN', 'admin@email.com', '$2a$10$cZLj.o.teXSzSBeXhJrnPOoSQ/o8Qj9G6rm4GXVev92g1yHggVJMS');

INSERT INTO PROFILE(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO PROFILE(id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO USER_PROFILES(user_id, profiles_id) VALUES(1, 1);
INSERT INTO USER_PROFILES(user_id, profiles_id) VALUES(2, 2);

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programming');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');
INSERT INTO COURSE(name, category) VALUES('KPMG', 'Exam');

INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('DOUBT', 'Error creating project', '2019-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('DOUBT 2', 'Project does not compile', '2019-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('DOUBT 3', 'Tag HTML', '2019-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);
