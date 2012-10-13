drop table EMP cascade constraint;
create table EMP(
emp_name varchar2(20) not null,
emp_salary number(9,2),
emp_dept   varchar2(10),
primary key(emp_name)
);

drop table MGR cascade constraint;
create table MGR(
dept	varchar2(10) not null,
dept_manager varchar2(20),
primary key(dept),
foreign key (dept_manager) references EMP(emp_name)
);

alter table EMP
add foreign key(emp_dept) references MGR(dept);

insert into MGR
values('dept1', null);

insert into MGR
values('dept2', null);

--EMP

insert into EMP
values('John', 40000, 'dept1');

insert into EMP
values('Mary', 50000, 'dept1');

insert into EMP
values('Joe', 60000, 'dept2');

insert into EMP
values('Alice', 50000, 'dept2');


update MGR set dept_manager = 'John' where dept = 'dept1';
update MGR set dept_manager = 'Alice' where dept = 'dept2';

commit;






