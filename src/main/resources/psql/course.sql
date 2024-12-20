CREATE TABLE course(
    course_id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    description text,
    max_students_amount INTEGER,
    teacher_id INTEGER
);

INSERT INTO course (name, description, max_students_amount, teacher_id) VALUES ('Test Course', 'super course', 30, 1);

ALTER TABLE course ADD COLUMN cost DECIMAL(10, 2);
ALTER TABLE  course RENAME COLUMN cost TO price;