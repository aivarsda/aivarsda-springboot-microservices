CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user"(
   id BIGSERIAL PRIMARY key,
   email VARCHAR(40) NOT NULL,
   username VARCHAR(15),
   password VARCHAR(100)
);

CREATE TABLE "role"(
   id BIGSERIAL PRIMARY key,
   name VARCHAR(60) NOT NULL
);

CREATE TABLE user_roles(
   id BIGSERIAL PRIMARY key,
   user_id BIGINT references "user"(id),
   role_id BIGINT references "role"(id)
);

INSERT INTO "role"(name) VALUES('ROLE_USER');
INSERT INTO "role"(name) VALUES('ROLE_PM');
INSERT INTO "role"(name) VALUES('ROLE_ADMIN');

INSERT INTO "user" (email,username,password) VALUES ('aivars@blabla.com','Aivars','123qwe');
INSERT INTO "user" (email,username,password) VALUES ('admin@blabla.com','admin','admin123');
INSERT INTO "user" (email,username,password) VALUES ('pm@blabla.com','pm','pm123');

INSERT INTO "user_roles" (user_id, role_id ) VALUES (1,1);
INSERT INTO "user_roles" (user_id, role_id ) VALUES (2,3);
INSERT INTO "user_roles" (user_id, role_id ) VALUES (3,2);

