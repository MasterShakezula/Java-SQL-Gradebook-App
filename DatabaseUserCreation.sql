-- 
-- Part 1 Create User

CREATE USER 'gradebook-admin' IDENTIFIED BY 'Grad3B00k!';
GRANT ALL ON gradebook.* to 'gradebook-admin';
drop user 'gradebook-admin';
-- Part 2 Triggers

use gradebook;


DELIMITER $$
CREATE TRIGGER check_grade_upgrade
BEFORE UPDATE ON ENROLLMENT
FOR EACH ROW
BEGIN
	DECLARE A float;
    DECLARE B float;
    DECLARE C float;
    DECLARE D float;
    
	SELECT A_Min INTO A FROM COURSE WHERE Course_Id = NEW.Course;
	SELECT B_Min INTO B FROM COURSE WHERE Course_Id = NEW.Course;
	SELECT C_Min INTO C FROM COURSE WHERE Course_Id = NEW.Course;
	SELECT D_Min INTO D FROM COURSE WHERE Course_Id = NEW.Course;

	IF New.Total_Points >= A THEN
		SET NEW.Final_Grade = 'A';
	ELSEIF New.Total_Points >= B AND New.Total_Points < A THEN
		SET NEW.Final_Grade = 'B';
	ELSEIF New.Total_Points >= C AND New.Total_Points < B THEN
		SET NEW.Final_Grade = 'C';
	ELSEIF New.Total_Points >= D AND New.Total_Points < C THEN
		SET NEW.Final_Grade = 'D';
	ELSE
		SET NEW.Final_Grade = 'F';
	END IF;
END; $$
DELIMITER ;
update enrollment
set Total_Points = 88
where Student = '1234561';
