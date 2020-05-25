INSERT INTO USER (id, username, password, first_name, last_name) VALUES (1, 'admin', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'admin', 'admin');
INSERT INTO ROLE (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO ROLE (id, role_name, description) VALUES (2, 'ROLE_USER', 'General user');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);

INSERT INTO CITY (id, name, country, description) VALUES (1, 'Amsterdam', 'Netherlands', 'Amsterdam (/ˈæmstərdæm/, UK also /ˌæmstərˈdæm/;[10][11] Dutch: [ɑmstərˈdɑm] (About this soundlisten)) is the capital and most populous city of the Netherlands with a population of 872,680[12] within the city proper, 1,380,872 in the urban area[5] and 2,410,960 in the metropolitan area.');
INSERT INTO CITY (id, name, country, description) VALUES (2, 'Berlin', 'Germany', 'Berlin (/bɜːrˈlɪn/; German: [bɛʁˈliːn] (About this soundlisten)) is the capital and largest city of Germany by both area and population.[5][6] Its 3,769,495 (2019)[2] inhabitants make it the most populous city proper of the European Union.[7] The city is one of Germany''s 16 federal states.');
INSERT INTO CITY (id, name, country, description) VALUES (3, 'New York', 'United States', 'New York City (NYC), often called The City or simply New York (NY), is the most populous city in the United States. With an estimated 2019 population of 8,336,817 distributed over about 302.6 square miles (784 km2), New York is also the most densely populated major city in the United States');


INSERT INTO COMMENT (id, body, city_id) VALUES (1, 'Amsterdam comment', 1);
INSERT INTO COMMENT (id, body, city_id) VALUES (2, 'One more amsterdam comment', 1);
INSERT INTO COMMENT (id, body, city_id) VALUES (3, 'Even more comments about amsterdam', 1);
INSERT INTO COMMENT (id, body, city_id) VALUES (4, 'Berlin comment', 2);
INSERT INTO COMMENT (id, body, city_id) VALUES (5, 'New York comment', 3);
INSERT INTO COMMENT (id, body, city_id) VALUES (6, 'One more Berlin comment', 2);



