/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mihir
 * Created: 10 Apr, 2020
 */

create database dev;
use dev;
create table user( username varchar(20), 
password varchar(20));

use dev;
CREATE TABLE users (
id bigint primary key,
username VARCHAR(30) unique,
firstname VARCHAR(15),
lastname VARCHAR(15),
salt VARCHAR(8),
password VARCHAR(64),
user_role VARCHAR(30),
reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

use dev;
CREATE TABLE provider (
pid bigint primary key,
amount INT(6),
FOREIGN KEY (pid) REFERENCES users(id)
);

CREATE TABLE freelancer (
uid bigint primary key,
skills VARCHAR(500),
message VARCHAR(500),
FOREIGN KEY (uid) REFERENCES users(id)
);
select * from users;
use dev;
drop table freelancer;
drop table provider;
drop table users;
