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
primary key (ssn)
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
 
--insert data
insert into bank values('1234','Pitt Bank', '111 University St');

insert into customer values('123456789', 'John Smith', '555-535-5263','100 University St');

insert into account values('111222','123456789', '1234', '10-sep-08', 500, null);  
