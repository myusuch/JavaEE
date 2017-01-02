/*
// javaClub.sql
// =================
// all SQL statements used for this assignment.
//
// AUTHOR: Myungsuk Choi (myusuch@gmail.com, 991 388 329)
// CREATED: 2016-07-29
// UPDATED: 2016-07-30
*/

USE ejd;

DROP TABLE IF EXISTS jcUser;

CREATE TABLE jcUser
(
    id VARCHAR(25) NOT NULL PRIMARY KEY,
    password VARCHAR(128) NOT NULL,
    firstName VARCHAR(25) NOT NULL,
    lastName VARCHAR(25) NOT NULL,
    email VARCHAR(50) NOT NULL
);
