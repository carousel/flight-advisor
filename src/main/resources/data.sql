-- USER
-- hashed password: letmein
-- INSERT INTO USER (id, username, password, first_name, last_name)
-- VALUES (1, 'jimihendrix', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jimi', 'Hendrix');

-- ROLES

INSERT INTO ROLE (id, role_name, description)
VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO ROLE (id, role_name, description)
VALUES (2, 'ROLE_USER', 'General user role');

-- INSERT INTO USER_ROLE (user_id, role_id)
-- VALUES (1, 1); -- give admin ROLE_ADMIN

DELETE FROM CITY;
DELETE FROM AIRPORT;
-- INSERT INTO AIRPORT (airport_id, name, city, country, IATA, ICAO, latitude, longitude, altitude, timezone, dst, tz, type, source) VALUES (20, 'Vestmannaeyjar Airport','Vestmannaeyjar','Iceland','VEY','BIVM',63.42430114746094,-20.278900146484375,326,0,'N','Atlantic/Reykjavik','airportBean','OurAirports');
-- INSERT INTO AIRPORT (airport_id, name, city, country, IATA, ICAO, latitude, longitude, altitude, timezone, dst, tz, type, source) VALUES (5, 'Port Moresby Jacksons International AirportBean','Port Moresby','Papua New Guinea','POM','AYPY',-9.443380355834961,147.22000122070312,146,10,'U','Pacific/Port_Moresby','airportBean','OurAirports');
-- INSERT INTO AIRPORT (airport_id, name, city, country, IATA, ICAO, latitude, longitude, altitude, timezone, dst, tz, type, source) values (8, 'Godthaab / Nuuk AirportBean', 'Godthaab', 'Greenland', 'GOH', 'BGGH', 64.19090271, -51.6781005859, 283, -3, 'E', 'America/Godthab', 'airportBean', 'OurAirports');
-- INSERT INTO AIRPORT (airport_id, name, city, country, IATA, ICAO, latitude, longitude, altitude, timezone, dst, tz, type, source) VALUES(10,'Thule Air Base','Thule','Greenland','THU','BGTL',76.5311965942,-68.7032012939,251,-4,'E','America/Thule','airportBean','OurAirports');

INSERT INTO CITY (id, name, country, description) VALUES (1, 'Shansan', 'Papua New Guinea', 'Goroka is the capital of the Eastern Highlands Province of Papua New Guinea');
INSERT INTO CITY (id, name, country, description) VALUES (2, 'Yingkou', 'Greenland', 'Godthaab became the seat of government for the Danish colony of South Greenland');

-- INSERT INTO COMMENT (id, body, city_id) VALUES (1, 'Goroka comment', 1);
-- INSERT INTO COMMENT (id, body, city_id) VALUES (2, 'Goroke comment', 1);
-- INSERT INTO COMMENT (id, body, city_id) VALUES (3, 'Godthaab comment', 2);
-- INSERT INTO COMMENT (id, body, city_id) VALUES (4, 'Godthaab comment', 2);
-- INSERT INTO COMMENT (id, body, city_id) VALUES (5, 'Godthaab comment', 2);
-- INSERT INTO COMMENT (id, body, city_id) VALUES (6, 'Godthaab comment', 2);



