--note that the sizes of the varchar2 types specified for some attribute are not realistic, 
--they are small so that the data can be printed nicely when you test your queries
--Please make sure the length of the data you insert does not exceed the size of the corresponding attribute type.


drop table contacts cascade constraints;

create table contacts(
email_addr 	varchar2(30) not null,
fname		varchar2(10),
lname		varchar2(10),
cell		varchar2(15),
city		varchar2(20),
country		varchar2(20),
primary key (email_addr)
);

------
drop table messages cascade constraints;

create table messages(
msgID 		varchar2(20) not null,
sender_email 	varchar2(30),
subject 	varchar2(30),
timestamp_sent	timestamp(0),
msg_text 	varchar2(30),
primary key (msgID),
foreign key(sender_email) references contacts(email_addr)
);

----
drop table recipients cascade constraints;

create table recipients(
msgID		varchar2(20) not null,
email_addr	varchar2(30) not null,
timestamp_read	timestamp(0),
spam		number(1,0),
important 	number(1,0),
primary key (msgID, email_addr),
foreign key(msgID) references messages(msgID),
foreign key(email_addr) references contacts(email_addr)
);

------
drop table labels cascade constraints;

create table labels(
msgID		varchar2(20) not null,
email_addr	varchar2(30) not null,
label_name	varchar2(30) not null,
primary key (msgID, email_addr, label_name),
foreign key (msgID, email_addr) references recipients(msgID, email_addr)
);

-----
drop table filters cascade constraints;

create table filters(
email_addr		varchar2(30),
sender_predicate 	varchar2(30),
recipient_predicate	varchar2(30),
subject_predicate	varchar2(30),
label_name		varchar2(30),
foreign key (email_addr) references contacts(email_addr)
);
