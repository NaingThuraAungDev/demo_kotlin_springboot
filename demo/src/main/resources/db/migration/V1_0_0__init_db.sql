DROP TABLE IF EXISTS author;
CREATE TABLE author (
   id BIGINT NOT NULL AUTO_INCREMENT,
   age SMALLINT,
   description VARCHAR(512),
   image VARCHAR(512),
   name VARCHAR(512),
   PRIMARY KEY (id)
);

DROP TABLE IF EXISTS books;
CREATE TABLE book (
   isbn VARCHAR(19) NOT NULL,
   description VARCHAR(2048),
   image VARCHAR(512),
   title VARCHAR(512),
   author_id BIGINT,
   PRIMARY KEY (isbn)
);
