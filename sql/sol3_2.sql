-- ----------------------------------------------------------
-- File: solution.hw3.sql
-- Desc: SAMPLE SOLUTIONS to third assignment in CS1555 class
-- Date: October 25, 2010
-- ----------------------------------------------------------

-- ----------------------------------------------------------
-- Question #1:
-- ----------------------------------------------------------

-- ----------------------------------------------------------
-- Question #2:
-- ----------------------------------------------------------

--if recipient_predicate is to be considered also, then this trigger is:
create or replace trigger trig_2
after insert
on filters
for each row
begin

insert into Labels
(
select m.msgid, r.email_addr, :new.label_name
 from messages m, recipients r, recipients other_r
 where m.msgid = r.msgid
       and r.email_addr  = :new.email_addr
       and other_r.msgid = m.msgid
       and (:new.sender_predicate is null or :new.sender_predicate = m.sender_email)
       and (:new.subject_predicate is null or :new.subject_predicate = m.subject)  
       and (:new.recipient_predicate is null or :new.recipient_predicate = other_r.email_addr)
);

end;
/



