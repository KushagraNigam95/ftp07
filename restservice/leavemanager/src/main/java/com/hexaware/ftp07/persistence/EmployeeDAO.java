package com.hexaware.ftp07.persistence;

import com.hexaware.ftp07.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;
import java.util.Date;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * return all the details of the selected employee.
   * @param empId the id of the employee
   * @return the managerId array
   */
  @SqlQuery("SELECT DISTINCT * FROM EMPLOYEE WHERE EMP_MGRID IN ( :empId)")
  @Mapper(EmployeeMapper.class)
  Employee findMgr(@Bind("empId") int empId);


  /**
   * @param empId the id of the manager.
   * @return returns the list.
   */
  @SqlQuery("select * from employee where EMP_ID = (select  EMP_MGRID from employee where EMP_ID in (:empId)); ")
    @Mapper(EmployeeMapper.class)
    Employee findMngr(@Bind("empId") int empId);


  /**
   * insert all the details of the employee.
   * @param leaStart the start date of the employee
   * @param leaEnd the end date of the employee
   * @param totalDays the number of days
   * @param appliedDate the applied date of the employee
   * @param levReason the reason for leave
   * @param empId the employee id
   */
  @SqlUpdate("insert into LEAVE_HISTORY "
             +
             "             (LEV_START, "
             +
             "              LEV_END, "
             +
             "              LEV_NO_OF_DAYS, "
             +
             "              LEV_APPLIEDDATE, "
             +
             "              LEV_REASON, "
             +
             "              EMP_ID) "
             +
             "values       ( "
             +
             "              :leaStart, "
             +
             "              :leaEnd, "
             +
             "              :totalDays, "
             +
             "              :appliedDate, "
             +
             "              :levReason, "
             +
             "              :empId)")
  void insert(@Bind("leaStart") Date leaStart,
              @Bind("leaEnd") Date leaEnd,
              @Bind("totalDays") int totalDays,
              @Bind("appliedDate") Date appliedDate,
              @Bind("levReason") String levReason,
              @Bind("empId") int empId);

  /**
   * @param  empId of the employee.
   * @param newBalance the id of the Leave balance.
   */
  @SqlUpdate("UPDATE EMPLOYEE SET"
            +
            "    EMP_LEAVEBALANCE = :newBalance "
            +
            "    WHERE EMP_ID = :empId")
    void levBalance(@Bind("newBalance") int newBalance, @Bind("empId") int empId);

  /**
   * @param  empId of the employee.
   */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEV_STATUS = 'APPROVED' "
            +
            "    WHERE EMP_ID = :empId")
    void autoApprove(@Bind("empId") int empId);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
