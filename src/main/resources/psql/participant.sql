CREATE TABLE participant(
    user_id INT references "user"(id),
    course_id INT references course(course_id),
    UNIQUE(user_id, course_id)
)