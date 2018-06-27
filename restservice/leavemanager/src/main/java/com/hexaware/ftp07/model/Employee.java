package com.hexaware.ftp07.model;

import com.hexaware.ftp07.persistence.DbConnection;
import com.hexaware.ftp07.persistence.EmployeeDAO;

import java.util.Objects;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.concurrent.TimeUnit;
//import com.hexaware.ftp07.util.CliMain;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empID;

  /**
   * empName to store employee name.
   */
  private String empName;

  /**
   * empMail to store employee mail.
   */
  private String empMail;

  /**
   * empPhone to store employee phone.
   */
  private long empPhone;

  /**
   * empDoj to store employee date of joining.
   */
  private String empDoj;

  /**
   * empLeaveBalance to store employee leave balance.
   */
  private int empLeaveBalance;

  /**
   * empDept to store employee department name.
   */
  private String empDept;

  /**
   * empMgrId to store employee manager id.
   */
  private int empMgrId;

  /**
   * Constructor.
   */
  public Employee() {

  }

   /**
   * @param argEmpID to initialize employee id.
   */
  public Employee(final int argEmpID) {
    this.empID = argEmpID;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empID, emp.empID) && Objects.equals(empName, emp.empName)
        && Objects.equals(empMail, emp.empMail) && Objects.equals(empPhone, emp.empPhone)
        && Objects.equals(empDoj, emp.empDoj) && Objects.equals(empDept, emp.empDept)
        && Objects.equals(empMgrId, emp.empMgrId)
        && Objects.equals(empLeaveBalance, emp.empLeaveBalance)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empID, empName, empMail,
    empPhone, empDoj, empDept, empMgrId, empLeaveBalance);
  }

  /**
   * @param argEmpID to initialize employee id.
   * @param argEmpName to initialize employee name.
     @param argEmpMail to initialize employee mail.
     @param argEmpPhone to initialize employee phone number.
     @param argEmpDoj to initialize employee Date of joining.
     @param argEmpDept to initialize employee Department.
     @param argEmpLeaveBalance to initialize employee leave balance.
     @param argEmpMgrId to initialize employee manager id.
   */
  public Employee(final int argEmpID, final String argEmpName, final String argEmpMail,
      final long argEmpPhone, final String argEmpDoj,
      final int argEmpLeaveBalance, final String argEmpDept, final int argEmpMgrId) {
    this.empID = argEmpID;
    this.empName = argEmpName;
    this.empMail = argEmpMail;
    this.empPhone = argEmpPhone;
    this.empDoj = argEmpDoj;
    this.empLeaveBalance = argEmpLeaveBalance;
    this.empDept = argEmpDept;
    this.empMgrId = argEmpMgrId;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpID() {
    return empID;
  }

  /**
   *
   * @param argEmpID to set employee id.
   */
  public final void setEmpID(final int argEmpID) {
    this.empID = argEmpID;
  }

  /**
   * Gets the EmployeeName.
   * @return this Employee's Name.
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   *
   * @param argEmpName to set employee Name.
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }

  /**
   * Gets the EmployeeMail.
   * @return this Employee's Mail.
   */
  public final String getEmpMail() {
    return empMail;
  }

  /**
   *
   * @param argEmpMail to set employee mail.
   */
  public final void setEmpMail(final String argEmpMail) {
    this.empMail = argEmpMail;
  }

  /**
   * Gets the EmployeePhone.
   * @return this Employee's Phone.
   */
  public final long getEmpPhone() {
    return empPhone;
  }

  /**
   *
   * @param argEmpPhone to set employee phone.
   */
  public final void setEmpPhone(final long argEmpPhone) {
    this.empPhone = argEmpPhone;
  }

  /**
   * Gets the EmployeeDoj.
   * @return this Employee's Date Of Joining.
   */
  public final String getEmpDoj() {
    return empDoj;
  }

  /**
   *
   * @param argEmpDoj to set employee date of joining.
   */
  public final void setEmpDoj(final String argEmpDoj) {
    this.empDoj = argEmpDoj;
  }

  /**
   * Gets the EmployeeLeaveBalance.
   * @return this Employee's Leave Balance.
   */
  public final int getEmpLeaveBalance() {
    return empLeaveBalance;
  }

  /**
   *
   * @param argEmpLeaveBalance to set employee leave balance.
   */
  public final void setEmpLeaveBalance(final int argEmpLeaveBalance) {
    this.empLeaveBalance = argEmpLeaveBalance;
  }

  /**
   * Gets the EmployeeDepartment.
   * @return this Employee's Department.
   */
  public final String getEmpDept() {
    return empDept;
  }

  /**
   *
   * @param argEmpDept to set employee employee department.
   */
  public final void setEmpDept(final String argEmpDept) {
    this.empDept = argEmpDept;
  }

  /**
   * Gets the EmployeeManagerId.
   * @return this Employee's Manager Id.
   */
  public final int getEmpMgrId() {
    return empMgrId;
  }

  /**
   *
   * @param argEmpMgrId to set employee manager id.
   */
  public final void setEmpMgrId(final int argEmpMgrId) {
    this.empMgrId = argEmpMgrId;
  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

  /**
   * list employee details by id.
   * @param empId id to get employee details.
   * @return Employee
   */
  public static Employee findMgr(final int empId) {
    return dao().findMgr(empId);
  }

  /**
   * list the manager id for particular emp.
   * @return this list.
   * @param empId to get empId.
   */
  public static Employee manager(final int empId) {
    return dao().findMngr(empId);
  }



  /**
  * insert employee leave details.
  * @param startDate to get Start date.
  * @param endDate to get End Date.
  * @param reason to get leave reason.
  * @param empID to get employee id.
  * @param levTotalDays to get employee id.
  * @throws ParseException throws Parse Exception.
  * @return s to return the message.
  */
  public static final String checkLeave(final String startDate, final String endDate,
      final String reason, final int empID, final int levTotalDays) throws ParseException {
    String s = null;
    Employee employee = Employee.listById(empID);
    int levBalance = employee.getEmpLeaveBalance();
    if (levBalance > 0) {
      int newBalance = levBalance - levTotalDays;
      int empMgr;
      empMgr = employee.getEmpMgrId();
      if (levTotalDays < employee.getEmpLeaveBalance()) {
        s = employee.applyLeave(startDate, endDate, reason, empID, levTotalDays, newBalance, empMgr);
      } else {
        System.out.println(" -----------------------------------------");
        System.out.println("|      Insufficient leave balance         |");
        System.out.println(" -----------------------------------------");
        s = "Insufficient Leave Balance";
      }
    }
    return s;
  }
  /**
  * insert employee leave details.
  * @param startDate to get Start date.
  * @param endDate to get End Date.
  * @param levReason to get leave reason.
  * @param empId to get employee id.
  * @param totalDays to get employee id.
  * @param newBalance to update leave balance.
  * @param empMgr to get manager id.
  * @throws ParseException throws Parse Exception.
  * @return s to return the message.
  */
  public static String applyLeave(final String startDate, final String endDate,
      final String levReason, final int empId, final int totalDays,
      final int newBalance, final int empMgr) throws ParseException {
    String s = null;
    java.util.Date d1 = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
    java.sql.Date leaStart = new java.sql.Date(d1.getTime());
    java.util.Date d2 = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);
    java.sql.Date leaEnd = new java.sql.Date(d2.getTime());
    long diff = d2.getTime() - d1.getTime();
    int noOfDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    LeaveDetails[] levDetails = LeaveDetails.listLeaveDetails(empId);
    for (LeaveDetails l : levDetails) {
      if ((leaStart.before(l.getLevEndDate()) || leaStart.equals(l.getLevEndDate()))
          && (leaEnd.after(l.getLevStartDate()) || leaEnd.equals(l.getLevStartDate()))) {
        System.out.println(" ----------------------------------------------------------------");
        System.out.println("|Please Check!! You have already applied for leave in this period|");
        System.out.println(" ----------------------------------------------------------------");
        s = "You have already applied for leave in this period";
        return s;
      }
    }
    java.sql.Date leaApplied = new java.sql.Date(new java.util.Date().getTime());
    if (leaStart.after(leaApplied) && leaEnd.after(leaStart) | leaEnd.equals(leaStart) && totalDays <= noOfDays + 1) {
      dao().insert(leaStart, leaEnd, totalDays, leaApplied, levReason, empId);
      dao().levBalance(newBalance, empId);
      if (empMgr != 0) {
        s = "Leave applied successfully";
        System.out.println(" ---------------------------------------");
        System.out.println("|      Leave Applied Successfuly!!!!    |");
        System.out.println(" ---------------------------------------");
      } else if (empMgr == 0) {
        dao().autoApprove(empId);
        s = "Leave Auto Approved";
        System.out.println(" --------------------------------------");
        System.out.println("|     Your request is auto approved    |");
        System.out.println(" --------------------------------------");
      }
    } else {
      System.out.println("Either Start date or End date is incorrect: Please enter correct dates");
      s = "please enter correct dates";
    }
    return s;
  }

}
