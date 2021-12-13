-- CLEANUP --

DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS universities;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS student_courses;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS course_tags;

-- SCHEMA --

CREATE TABLE students (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `surname` varchar(255),
    `university_id` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE universities (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `city` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE courses (
    `id` int NOT NULL AUTO_INCREMENT,
    `code` varchar(255),
    `name` varchar(255),
    `credits` int,
    `university_id` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE student_courses (
    `id` int NOT NULL AUTO_INCREMENT,
    `student_id` int NOT NULL,
    `course_id` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tags (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE course_tags (
    `id` int NOT NULL AUTO_INCREMENT,
    `tag_id` int NOT NULL,
    `course_id` int NOT NULL,
    PRIMARY KEY (`id`)
);

-- SEED --

INSERT INTO universities (id, name, city) VALUES (1, 'Czech Technical University in Prague', 'Prague');
INSERT INTO universities (id, name, city) VALUES (2, 'Kaunas University of Technology', 'Kaunas');

INSERT INTO students (id, name, surname, university_id) VALUES (1, 'John', 'Smith', 1);
INSERT INTO students (id, name, surname, university_id) VALUES (2, 'William', 'Johnson', 2);

INSERT INTO courses (id, code, name, credits, university_id) VALUES (1, 'BIE-TJV', 'Java Technology', 5, 1);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (2, 'NIE-AM1', 'Middleware Architectures', 6, 1);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (3, 'MIE-IBE', 'Information Security', 6, 1);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (4, 'P000B001', 'Math', 6, 2);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (5, 'P000B002', 'Informatics', 6, 2);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (6, 'P000B003', 'Chemistry', 6, 2);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (7, 'P000B004', 'Software Engineering', 6, 2);
INSERT INTO courses (id, code, name, credits, university_id) VALUES (8, 'P000B005', 'C#', 6, 2);

INSERT INTO tags (id, name) VALUES (1, 'IT');
INSERT INTO tags (id, name) VALUES (2, 'Chemistry');
INSERT INTO tags (id, name) VALUES (3, 'Programming');
INSERT INTO tags (id, name) VALUES (4, 'Math');

INSERT INTO course_tags (id, tag_id, course_id) VALUES (1, 1, 1);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (2, 1, 2);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (3, 1, 3);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (4, 1, 5);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (5, 1, 7);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (6, 1, 8);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (7, 2, 6);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (8, 3, 1);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (9, 3, 2);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (10, 3, 8);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (11, 4, 4);
INSERT INTO course_tags (id, tag_id, course_id) VALUES (12, 4, 5);

INSERT INTO student_courses (id, student_id, course_id) VALUES (1, 1, 1);
INSERT INTO student_courses (id, student_id, course_id) VALUES (2, 1, 1);
INSERT INTO student_courses (id, student_id, course_id) VALUES (3, 1, 1);
INSERT INTO student_courses (id, student_id, course_id) VALUES (4, 1, 2);
INSERT INTO student_courses (id, student_id, course_id) VALUES (5, 2, 7);
INSERT INTO student_courses (id, student_id, course_id) VALUES (6, 2, 8);