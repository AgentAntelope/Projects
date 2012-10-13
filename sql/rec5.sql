--this is to drop the table if it has existed
drop table bank cascade constraints;

--create table bank
create table bank(
code char(4) not null,
name varchar2(20),
addr varchar2(30),
primary key(code),
unique(name)
);


