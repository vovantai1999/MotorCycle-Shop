create database case_md3;
use case_md3;

create table products(
id int not null auto_increment,
img varchar(300) not null,
title varchar(300) not null,
quantity int,
price double,
primary key(id));
