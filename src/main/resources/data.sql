-- USER
-- hashed password: letmein
INSERT INTO USER (id, username, password, first_name, last_name) VALUES
(1, 'jimihendrix', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jimi', 'Hendrix');

-- ROLES

INSERT INTO ROLE (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO ROLE (id, role_name, description) VALUES (2, 'ROLE_USER', 'General user role');

INSERT INTO USER_ROLE (user_id, role_id) VALUES
 (1, 1) -- give admin ROLE_ADMIN