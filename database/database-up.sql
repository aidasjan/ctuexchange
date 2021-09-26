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