CREATE Table "user"(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    password VARCHAR(50),
    role varchar(10),
    balance NUMERIC(12, 2)
);

INSERT INTO "user" (firstname, lastname, password, role, balance) VALUES ('Alex', 'Pliekhov', 'password', 'STUDENT', 123);

INSERT INTO "user" (firstname, lastname, password, role, balance) VALUES ('Ivan', 'Ivanov', 'password', 'TEACHER', 0);
