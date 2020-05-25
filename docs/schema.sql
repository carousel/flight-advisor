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
    name        varchar(128) UNIQUE NOT NULL,
    country     varchar(50) DEFAULT NULL,
    description text(1024) DEFAULT NULL
);

CREATE TABLE AIRPORT
(
    id         bigint AUTO_INCREMENT PRIMARY KEY,
    airport_id bigint         NOT NULL,
    name       varchar(128)   NOT NULL,
    city       varchar(128)   NOT NULL,
    country    varchar(50)    NOT NULL,

    IATA       varchar(3)  DEFAULT NULL,
    ICAO       varchar(4)  DEFAULT NULL,

    latitude   decimal(10, 8) NOT NULL,
    longitude  decimal(11, 8) NOT NULL,

    altitude   integer(6)  DEFAULT 0,
    timezone   integer(6)  DEFAULT 0,
    dst        enum ('E','A','S','O','Z', 'N','U') DEFAULT 'N',
    tz         varchar(24) DEFAULT 'N',

    type       varchar(12)    NOT NULL,
    source     varchar(12)    NOT NULL
);
-- 2B,410,AER,2965,KZN,2990,,0,CR2,95.87
-- CREATE TABLE ROUTE
-- (
--     id             bigint AUTO_INCREMENT PRIMARY KEY,
--     airline        varchar(3)     NOT NULL,
--     airline_id     bigint         NOT NULL,
--     source_airport varchar(6)     NOT NULL,
--     source_airport_ID varchar(6)     NOT NULL,
--
--
-- );