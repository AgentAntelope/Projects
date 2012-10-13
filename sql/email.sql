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
foreign key(msgID) references messages(msgID) on delete cascade,
foreign key(email_addr) references contacts(email_addr)
);

------
drop table labels cascade constraints;

create table labels(
msgID		varchar2(20) not null,
email_addr	varchar2(30) not null,
label_name	varchar2(30) not null,
primary key (msgID, email_addr, label_name),
foreign key (msgID, email_addr) references recipients(msgID, email_addr) on delete cascade
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

--contacts
insert into contacts values ('super@pittmail.com','fsuper','lsuper', null, 'pittsburgh','us');
insert into contacts values ('verycool@pittmail.com','fcool','lcool', null, 'phili','us');
insert into contacts values ('thao@pittmail.com','thao','pham', null, 'pittsburgh','us');
--insert into contacts values ('panos@pittmail.com','panos','chrys', null, 'pittsburgh','us');
--insert into contacts values ('alex@pittmail.com','alex','labri', null, 'pittsburgh','us');

--messages
insert into messages values ('m1', 'super@pittmail.com', 'CS1555', timestamp'2010-09-12 13:20:05', null);
insert into messages values ('m2', 'verycool@pittmail.com', 'hey', timestamp'2010-09-18 10:20:05', null);


--recipients
insert into recipients values ('m1','verycool@pittmail.com', null, null,0);
--insert into recipients values ('m1','thao@pittmail.com', null, null,0);
insert into recipients values ('m2','thao@pittmail.com', timestamp'2010-09-19 10:20:05', null,0);
--insert into recipients values ('m2','panos@pittmail.com', timestamp'2010-10-18 10:20:05', null,0);
--insert into recipients values ('m2','super@pittmail.com', timestamp'2010-09-20 10:20:05', null,0);

--labels
--insert into labels values ('m1','thao@pittmail.com','lala');

--filters
insert into filters values ('thao@pittmail.com', null, null, 'CS1555', 'class');
