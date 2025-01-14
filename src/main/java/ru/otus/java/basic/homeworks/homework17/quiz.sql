CREATE DATABASE quiz;

CREATE TABLE IF NOT EXISTS questions (
    id SERIAL PRIMARY KEY ,
    question VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS answers (
    id SERIAL PRIMARY KEY ,
    answer_text VARCHAR NOT NULL,
    question_id INT REFERENCES questions(id) NOT NULL,
    is_right BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS tests (
    id SERIAL PRIMARY KEY ,
    title VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS test_questions (
    test_id INT REFERENCES tests(id),
    question_id INT REFERENCES questions(id),
    PRIMARY KEY (test_id, question_id)
);