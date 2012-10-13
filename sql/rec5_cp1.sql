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

--table customer
drop table customer cascade constraints;
create table customer(
ssn char(9) not null,
name varchar2(30),
phone varchar2(15),
addr varchar2(30),
primary key (ssn),
unique(phone)
);

--table account
drop table account cascade constraints;
create table account(
acc_no varchar2(15), 
ssn  char(9),
code char(4),
open_date date,
balance number(15,2),
close_date date,
primary key(acc_no),
foreign key (ssn) references customer(ssn),
foreign key(code) references bank(code),
check((close_date is null) or (close_date > open_date)),
check(balance >= 0)
);
 

