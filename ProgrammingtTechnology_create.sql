CREATE TABLE course_catalog (
    id int    NOT NULL ,
    general_course_catalog_id int    NOT NULL ,
    teacher_id int    NOT NULL ,
    CONSTRAINT course_catalog_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE general_course_catalog (
    id int    NOT NULL ,
    title varchar(50)    NOT NULL ,
    requirements varchar(200)    NOT NULL ,
    CONSTRAINT general_course_catalog_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE mark (
    id int    NOT NULL ,
    mark int    NOT NULL ,
    student_id int    NOT NULL ,
    course_catalog_id int    NOT NULL ,
    CONSTRAINT mark_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE student (
    id int    NOT NULL ,
    main_course_1_id int    NOT NULL ,
    main_course_2_id int    NOT NULL ,
    main_course_3_id int    NOT NULL ,
    main_course_4_id int    NOT NULL ,
    add_course_1_id int    NOT NULL ,
    add_course_2_id int    NOT NULL ,
    user_id int    NOT NULL ,
    CONSTRAINT student_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE teacher (
    id int    NOT NULL ,
    position varchar(50)    NOT NULL ,
    department varchar(50)    NOT NULL ,
    user_id int    NOT NULL ,
    CONSTRAINT teacher_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE user (
    id int    NOT NULL ,
    username varchar(50)    NOT NULL ,
    password varchar(50)    NOT NULL ,
    role varchar(50)    NOT NULL ,
    first_name varchar(50)    NOT NULL ,
    last_name varchar(50)    NOT NULL ,
    patronymic varchar(50)    NOT NULL ,
    CONSTRAINT user_pk PRIMARY KEY (id)
)DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


ALTER TABLE course_catalog ADD CONSTRAINT course_catalog_general_course_catalog FOREIGN KEY course_catalog_general_course_catalog (general_course_catalog_id)
    REFERENCES general_course_catalog (id);


ALTER TABLE course_catalog ADD CONSTRAINT course_catalog_teacher FOREIGN KEY course_catalog_teacher (teacher_id)
    REFERENCES teacher (id);


ALTER TABLE mark ADD CONSTRAINT mark_course_catalog FOREIGN KEY mark_course_catalog (course_catalog_id)
    REFERENCES course_catalog (id);


ALTER TABLE mark ADD CONSTRAINT mark_student FOREIGN KEY mark_student (student_id)
    REFERENCES student (id);


ALTER TABLE student ADD CONSTRAINT student_user FOREIGN KEY student_user (user_id)
    REFERENCES user (id);


ALTER TABLE teacher ADD CONSTRAINT teacher_user FOREIGN KEY teacher_user (user_id)
    REFERENCES user (id);


