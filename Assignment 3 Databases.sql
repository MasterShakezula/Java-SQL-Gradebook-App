-- Assignment 3.alter
-- Part 1 Create User

CREATE USER 'gradebook-admin' IDENTIFIED BY 'Grad3B00k!';
GRANT ALL ON gradebook.* to 'gradebook-admin';
drop user 'gradebook-admin';
-- Part 2 Triggers

use gradebook;
DELIMITER $$
create procedure `Calculate_Grades` (in course_id varchar(20) )
begin
declare letter_grade char;
declare A_Min float; declare B_Min float; declare C_Min float; declare D_Min float; declare grade float;
declare stud varchar(10);
declare sect int;
declare finished integer default 0;
declare students_cursor cursor for
select e.Total_Points, e.Section_No, e.Student, c.A_Min, c.B_Min, c.C_Min, c.D_Min from enrollment e
inner join section s on e.Section_No = s.Section_No 
inner join course c on s.Course = c.Course_Id 
where s.Course = Course_Id; --
declare continue handler for not found set finished = 1;
start transaction;
open students_cursor;
std_loop : loop  fetch students_cursor into stud, sect, A_Min, B_Min, C_Min, D_Min, grade;
if finished = 1 then leave std_loop;
end if;
			if (grade >= A_Min) then
				set letter_grade = 'A';
			elseif (grade >= B_Min) then
				set letter_grade = 'B';
			elseif (grade >= C_Min) then
				set letter_grade = 'C';
			elseif (grade >= D_Min) then
				set letter_grade = 'D';
                end if;
                update enrollment set Final_Grade = letter_grade where Student = stud and section_No = sect;
                end loop std_loop;
                close students_cursor;


END$$;

DELIMITER ;


