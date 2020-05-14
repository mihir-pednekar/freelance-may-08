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
amount INT(6) DEFAULT 0,
skills VARCHAR(500),
message VARCHAR(500),
FOREIGN KEY (uid) REFERENCES users(id)
);

CREATE TABLE jobs (
jobid bigint primary key,
title VARCHAR(30) NOT NULL,
skills VARCHAR(30),
description VARCHAR(64) NOT NULL,
payment INT(5) NOT NULL,
createdby bigint,
acceptedby bigint,
jobstatus VARCHAR(30) NOT NULL,
FOREIGN KEY (createdby) REFERENCES provider(pid),
FOREIGN KEY (acceptedby) REFERENCES freelancer(uid)
);

CREATE TABLE jobapps (
aid bigint NOT NULL AUTO_INCREMENT primary key,
jobid BIGINT DEFAULT 0  NOT NULL,
fid BIGINT DEFAULT 0  NOT NULL,
FOREIGN KEY (jobid) REFERENCES jobs(jobid),
FOREIGN KEY (fid) REFERENCES freelancer(uid)
);

