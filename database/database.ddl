-- change this to your team id
use FTP07;

-- comment this line for the very first time
drop table if exists EMPLOYEE;

-- create the table EMPLOYEE
CREATE TABLE EMPLOYEE(EMP_ID INT PRIMARY KEY,
EMP_NAME VARCHAR(30) NOT NULL,
EMP_MAIL VARCHAR(30) NOT NULL,
EMP_PHONE BIGINT NOT NULL,
EMP_DOJ DATE NOT NULL,
EMP_LEAVEBALANCE INT NOT NULL,
EMP_DEPT VARCHAR(10) NOT NULL,
EMP_MGRID INT );

--Adding Foreign Key Constraint
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMP_MGRID FOREIGN KEY (EMP_MGRID)
REFERENCES EMPLOYEE(EMP_ID);

--Create table LEAVE_HISTORY 
CREATE TABLE LEAVE_HISTORY
(
	LEV_ID INT PRIMARY KEY,
    EMP_ID INT NOT NULL,
    LEV_APPLIEDDATE DATE NOT NULL,
    LEV_START DATE NOT NULL,
    LEV_END DATE NOT NULL,
    LEV_NO_OF_DAYS INT,
    LEV_TYPE ENUM('EARNED_LEAVE') NOT NULL,
    LEV_REASON VARCHAR(50),
    LEV_STATUS ENUM('APPROVED','DENIED','PENDING') NOT NULL,
    LEV_COMMENT VARCHAR(50)

);

--Adding Foreign Key Constraint
ALTER TABLE LEAVE_HISTORY ADD CONSTRAINT FK_EMP_ID FOREIGN KEY (EMP_ID)
REFERENCES EMPLOYEE(EMP_ID);

