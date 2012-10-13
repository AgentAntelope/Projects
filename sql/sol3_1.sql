-- ----------------------------------------------------------
-- File: solution.hw3.sql
-- Desc: SAMPLE SOLUTIONS to third assignment in CS1555 class
-- Date: October 25, 2010
-- ----------------------------------------------------------

-- ----------------------------------------------------------
-- Question #1:
-- ----------------------------------------------------------
create or replace trigger trig_1
after insert
on recipients
for each row
begin
insert into labels
(select m.msgid, :new.email_addr, fi.label_name
 from messages m, filters fi
 where m.msgid = :new.msgid
       and fi.email_addr = :new.email_addr
       and (fi.sender_predicate is null or fi.sender_predicate  = m.sender_email)
       and (fi.subject_predicate is null or fi.subject_predicate = m.subject)
);

end;
/





