INSERT INTO user (city, number, street, zipcode, firstname, password, surname, username)
values ('Oslo', 2, 'Kjellveien', '4404', 'AdminfirstName', 'password', 'adminSurname', ' adminUsername'),
       ('Nordli', 2, 'Ekkegata', '3304', 'ClientfirstName', 'password2', 'clientSurname', ' clientUsername'),
       ('Nordli', 2, 'Ekkegata', '3304', 'Expeditor', 'password2', 'expeditorName', ' expeditorUsername');
INSERT INTO user_roles
values ('1', 'ADMIN'),
       ('1', 'EXPEDITOR');
INSERT INTO user_roles
values ('2', 'CLIENT');
INSERT INTO user_roles
values ('2', 'EXPEDITOR');