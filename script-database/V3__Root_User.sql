-- Insert root user
INSERT INTO user_ricettario(
	id, username, name, surname, email, password)
	VALUES (0, 'AdminSU', 'AdminSU', 'AdminSU', 'matteobern92@gmail.com', '$2a$10$qwUIzYIeDGOja23qUhNvgOaFTAzRMP57CG/VB/58ZvNkrovMDkebG');

-- Roles for root user
INSERT INTO user_role( id_user, id_role) VALUES (0, 0);
INSERT INTO user_role( id_user, id_role) VALUES (0, 1);