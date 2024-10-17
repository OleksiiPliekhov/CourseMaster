CREATE TABLE teacher (
    user_id INTEGER PRIMARY KEY REFERENCES "user"(id),
    degree VARCHAR(50),
    experience_years INTEGER
);

INSERT INTO "teacher" (user_id, degree, experience_years) VALUES (1, 'Master', 7);
