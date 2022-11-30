-- Assignment 3.alter
-- Part 1 Create User

CREATE USER 'gradebook-admin' IDENTIFIED BY 'Grad3B00k!';
GRANT ALL ON gradebook to 'gradebook-admin';

-- Part 2 Triggers
DELIMITER $$
CREATE TRIGGER check_grade BEFORE UPDATE ON ENROLLMENT
FOR EACH ROW 
BEGIN
        IF  EXISTS (SELECT * FROM ENROLLMENT WHERE NEW.Total_Points > 100) THEN
               signal sqlstate '45000' set message_text = 'Grade cannot be more than 100';
       END IF;
       
END; $$
DELIMITER ;
drop trigger update_letter_grade;
use gradebook;
DELIMITER $$
CREATE TRIGGER update_letter_grade BEFORE UPDATE ON ENROLLMENT
FOR EACH ROW 
BEGIN
				select A_Min, B_Min, C_Min, D_Min from course, enrollment where Course_Id = course.Course;
       
               update enrollment set new.Final_Grade = 'A' where new.Total_Points >= course.A.Min;
		
               update enrollment set Final_Grade = 'B' where new.Total_Points >= B.Min;
		
               update enrollment set Final_Grade = 'C' where new.Total_Points >= C.Min;
		
               update enrollment set Final_Grade = 'D' where new.Total_Points >= D.Min;
		
               update enrollment set Final_Grade = 'F' where new.Total_Points < D.Min;
END;$$
DELIMITER ;
update enrollment
set Total_Points = 80
where Student = '1234561';
-- delete from enrollment where Student = '1234560';


DELIMITER $$
CREATE TRIGGER check_grade_ins BEFORE INSERT ON ENROLLMENT
FOR EACH ROW 
BEGIN
        IF  EXISTS (SELECT * FROM ENROLLMENT WHERE NEW.Total_Points > 100) THEN
               signal sqlstate '45000' set message_text = 'Grade cannot be more than 100';
       END IF;
       
END; $$
DELIMITER ;


