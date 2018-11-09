DROP TABLE USER IF EXISTS;
CREATE TABLE USER (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  birth_date DATETIME,
  PRIMARY KEY (id)
);

INSERT INTO USER VALUES (1001, 'User1', 'password1', '2001-01-01 01:01:01');
INSERT INTO USER VALUES (1002, 'User2', 'password2', '2002-02-02 02:02:02');
INSERT INTO USER VALUES (1003, 'User3', 'password3', '2003-03-03 03:03:03');

-----------------------------------------------------------------------------

DROP TABLE `ORDER` IF EXISTS;
CREATE TABLE `ORDER` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  quantity INT DEFAULT 0,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES USER(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO `ORDER` VALUES (1, 'User1Order1', 11, 1001);
INSERT INTO `ORDER` VALUES (2, 'User1Order2', 12, 1001);
INSERT INTO `ORDER` VALUES (3, 'User2Order1', 21, 1002);

-----------------------------------------------------------------------------

DROP TABLE ROLE IF EXISTS;
CREATE TABLE ROLE (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO ROLE VALUES (1, 'ADMIN');
INSERT INTO ROLE VALUES (2, 'USER');

-----------------------------------------------------------------------------

DROP TABLE USER_ROLE IF EXISTS;
CREATE TABLE USER_ROLE (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

INSERT INTO USER_ROLE VALUES (1001, 1);
INSERT INTO USER_ROLE VALUES (1001, 2);
INSERT INTO USER_ROLE VALUES (1002, 1);