CREATE Table "user"(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    password VARCHAR(50),
    balance NUMERIC(12, 2)
);

INSERT INTO "user" (firstname, lastname, password, balance) VALUES ('Alex', 'Pliekhov', 'password', 123);

INSERT INTO "user" (firstname, lastname, password, balance) VALUES ('Ivan', 'Ivanov', 'password', 0);