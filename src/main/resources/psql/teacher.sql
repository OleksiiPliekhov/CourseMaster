CREATE TABLE teacher (
    user_id INTEGER PRIMARY KEY REFERENCES "user"(id),
    qualification VARCHAR(50),
    experience INTEGER
);

INSERT INTO "teacher" (user_id, qualification, experience) VALUES (1, 'Master', 7);
