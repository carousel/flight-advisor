CREATE TABLE ROLE
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) DEFAULT NULL,
    role_name   varchar(100) DEFAULT NULL
);


CREATE TABLE USER
(
    id         bigint AUTO_INCREMENT PRIMARY KEY,
    username   varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL
);


CREATE TABLE USER_ROLE
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES USER (id),
    CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

CREATE TABLE CITY
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    name       varchar(128)       NOT NULL,
    country     varchar(50) DEFAULT NULL,
    description text(1024) DEFAULT NULL
);

CREATE TABLE AIRPORT
(
    airport_id bigint AUTO_INCREMENT PRIMARY KEY,
    name       varchar(128)       NOT NULL,
    city       varchar(128)       NOT NULL,
    country    varchar(50) UNIQUE NOT NULL,
    IATA       varchar(3) DEFAULT NULL,
    ICAO       varchar(4) DEFAULT NULL,
    latitude   decimal(10, 8)     NOT NULL,
    longitude  decimal(11, 8)     NOT NULL,
    altitude   integer(6)         NOT NULL,
    timezone   integer(2)         NOT NULL,
    dst        enum ('E','A','S','O','Z', 'N','U'),
    tz         varchar(3)         NOT NULL,
    type       varchar(3)         NOT NULL,
    source     varchar(3)         NOT NULL
);
