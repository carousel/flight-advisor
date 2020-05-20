CREATE TABLE ROLE
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) DEFAULT NULL,
    role_name   varchar(100) DEFAULT NULL
);


CREATE TABLE USER
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL
);


CREATE TABLE USER_ROLE
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES USER (id),
    CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

CREATE TABLE CITY
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   varchar(50) UNIQUE NOT NULL,
    country   varchar(50) DEFAULT NULL,
    description text(1024) DEFAULT NULL
);
