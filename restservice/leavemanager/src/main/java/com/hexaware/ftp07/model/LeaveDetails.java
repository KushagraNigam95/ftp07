package com.hexaware.ftp07.model;

import com.hexaware.ftp07.persistence.DbConnection;
import com.hexaware.ftp07.persistence.LeaveDetailsDAO;

import java.util.Objects;
import java.sql.Date;
import java.util.List;

/**
 * Leave Details class to store employee leave details.
 * @author hexware
 */
public class LeaveDetails {

/**
 * levId to store leave id.
 */
  private int levId;

/**
 * levStartDate to store leave start date.
 */
  private Date levStartDate;

/**
 * levEndDate to store leave end date.
 */
  private Date levEndDate;

/**
 * levNoOfDays to store number of days.
 */
  private int levNoOfDays;

/**
 * levReason to store employee leave reason.
 */
  private String levReason;

/**
 * levType to store employee leave type.
 */
  private LeaveType levType;

/**
 * levAppliedOn to store employee leave applied on.
 */
  private Date levAppliedOn;

/**
 * levMgrComments to store employee leave manager comments.
 */
  private String levMgrComments;

/**
 * levStatus to store employee leave status.
 */
  private LeaveStatus levStatus;

/**
 * emplId to store leave id.
 */
  private int emplId;

/**
  * Default Constructor.
  */
  public LeaveDetails() {

  }

/**
  * @param argLevId to initialize employee id.
  */
  public LeaveDetails(final int argLevId) {
    this.levId = argLevId;
  }
  @Override
public final boolean equals(final Object ob) {
    if (ob == null) {
      return false;
    }
    if (getClass() != ob.getClass()) {
      return false;
    }
    LeaveDetails lev = (LeaveDetails) ob;
    if (Objects.equals(levId, lev.levId) && Objects.equals(levStartDate, lev.levStartDate)
        && Objects.equals(levEndDate, lev.levEndDate) && Objects.equals(levNoOfDays, lev.levNoOfDays)
        && Objects.equals(levReason, lev.levReason) && Objects.equals(levType, lev.levType)
        && Objects.equals(levAppliedOn, lev.levAppliedOn) && Objects.equals(levMgrComments, lev.levMgrComments)
        && Objects.equals(levStatus, lev.levStatus) && Objects.equals(emplId, lev.emplId)) {
      return true;
    }
    return false;
  }
  @Override
public final int hashCode() {
    return Objects.hash(levId, levStartDate, levEndDate,
    levNoOfDays, levReason, levType, levAppliedOn, levMgrComments, levStatus, emplId);
  }
  @Override
public final String toString() {
    return "leave id              : " + levId + "\n" + "Employee ID           : " + emplId + "\n"
        + "leave start date      : "
        + levStartDate + "\n" + "leave end date        : " + levEndDate + "\n"
        + "leave no of days      : "  + levNoOfDays + "\n" + "leave reason          : "
        + levReason + "\n" + "leave type            : "
        + levType + "\n"
        + "leave applied on      : " + levAppliedOn  + "\n" + "leave manager comment : " + levMgrComments + "\n"
        + "leave status          : "  + levStatus + "\n";
  }
/**
 * @param argLevId to initialize leave id.
 * @param argEmplId to initialize employee id.
 * @param argLevStartDate to initialize leave start date.
 * @param argLevEndDate to initialize leave end date.
 * @param argLevNoOfDays to initialize leave number of days.
 * @param argLevReason to initialize leave reason.
 * @param argLevType to initialize leave type.
 * @param argLevAppliedOn to initialize leave applied on.
 * @param argLevMgrComments to initialize leave manager comments.
 * @param argLevStatus to initialize leave status.
 */
  public LeaveDetails(final int argLevId, final int argEmplId, final Date argLevStartDate, final Date argLevEndDate,
                    final int argLevNoOfDays, final String argLevReason, final LeaveType argLevType,
                    final Date argLevAppliedOn, final String argLevMgrComments, final LeaveStatus argLevStatus) {
    this.levId = argLevId;
    this.emplId = argEmplId;
    this.levStartDate = argLevStartDate;
    this.levEndDate = argLevEndDate;
    this.levNoOfDays = argLevNoOfDays;
    this.levReason = argLevReason;
    this.levType = argLevType;
    this.levAppliedOn = argLevAppliedOn;
    this.levMgrComments = argLevMgrComments;
    this.levStatus = argLevStatus;

  }

/**
 * The dao for Leave details.
 */
  private static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }


/**
 * Gets the Leave Id.
 * @return this Employee's ID.
 */
  public final int getLevId() {
    return levId;
  }

/**
 *
 * @param argLevId to set leave id.
 */
  public final void setLevId(final int argLevId) {
    this.levId = argLevId;
  }

  /**
 * Gets the employee Id.
 * @return this Employee's ID.
 */
  public final int getEmplId() {
    return emplId;
  }

/**
 *
 * @param argEmplId to set leave id.
 */
  public final void setEmplId(final int argEmplId) {
    this.emplId = argEmplId;
  }

/**
 * Gets the leave start date.
 * @return this leave start date.
 */
  public final Date getLevStartDate() {
    return levStartDate;
  }

/**
 *
 * @param argLevStartDate to set leave start date.
 */
  public final void setLevStartDate(final Date argLevStartDate) {
    this.levStartDate = argLevStartDate;
  }

/**
 * Gets the Leave End Date.
 * @return this Leave End Date.
 */
  public final Date getLevEndDate() {
    return levEndDate;
  }

/**
 *
 * @param argLevEndDate to set leave end date.
 */
  public final void setLevEndDate(final Date argLevEndDate) {
    this.levEndDate = argLevEndDate;
  }

  /**
 * Gets the Leave Applied on.
 * @return this Leave Applied on.
 */
  public final Date getLevAppliedOn() {
    return levAppliedOn;
  }

/**
 *
 * @param argLevAppliedOn to set Leave Applied on.
 */
  public final void setLevAppliedOn(final Date argLevAppliedOn) {
    this.levAppliedOn = argLevAppliedOn;
  }


/**
 * Gets the Leave Id.
 * @return this Employee's ID.
 */
  public final int getLevNoOfDays() {
    return levNoOfDays;
  }



/**
 *
 * @param argLevReason to set leave reason.
 */
  public final void setLevReason(final String argLevReason) {
    this.levReason = argLevReason;
  }

/**
 * Gets the Leave type.
 * @return this Leave Type.
 */
  public final LeaveType getLevType() {
    return levType;
  }

/**
 *
 * @param argLevType to set Leave Type.
 */
  public final void setLevType(final LeaveType argLevType) {
    this.levType = argLevType;
  }

/**
 * Gets the Manager comments on leave.
 * @return this Manager comments on leave.
 */
  public final String getLevMgrComments() {
    return levMgrComments;
  }

/**
 *
 * @param argLevMgrComments to set manager comments.
 */
  public final void setLevMgrComments(final String argLevMgrComments) {
    this.levMgrComments = argLevMgrComments;
  }

/**
 * Gets the leave staus.
 * @return this leave status.
 */
  public final LeaveStatus getLevStatus() {
    return levStatus;
  }

/**
 *
 * @param argLevStatus to set leave status.
 */
  public final void setLevStatus(final LeaveStatus argLevStatus) {
    this.levStatus = argLevStatus;
  }

/**
 *
 * @param argLevNoOfDays to set leave id.
 */
  public final void setLevNoOfDays(final int argLevNoOfDays) {
    this.levNoOfDays = argLevNoOfDays;
  }

/**
 * Gets the Leave Reason.
 * @return this Reason for Leave.
 */
  public final String getLevReason() {
    return levReason;
  }

/**
 * list all leave details.
 * @param empId for emp id
 * @return all leave details
 */
  public static LeaveDetails[] listLeaveDetails(final int empId) {
    List<LeaveDetails> ls = dao().list(empId);
    return ls.toArray(new LeaveDetails[ls.size()]);
  }

/**
  * list leave detail by id.
  * @param levId id to get leave details.
  * @param emplID id to get leave details.
  * @return LeaveDetail
  */
  public static LeaveDetails listByApplication(final int levId, final int emplID) {
    return dao().appdeny(levId, emplID);
  }


  /**
  * list all pending details.
  * @param empId for emp id
  * @return all pending details
  */
  public static LeaveDetails[] listPendingApplications(final int empId) {
    List<LeaveDetails> ls2 = dao().finds(empId);
    return ls2.toArray(new LeaveDetails[ls2.size()]);
  }
    /**
     * list leave details by id.
     * @param levId id to get employee details.
     * @return Employee
     */
  public static LeaveDetails listByLeaveId(final int levId) {
    return dao().send(levId);
  }


  /**
   * approve the leave application.
   * @param levId id to get leave details.
   * @param mgrComments id to update manager comments.
   * @param emplID id to get leave details.
   * @return String
   */
  public static String approveLeave(final String mgrComments, final int levId,
                                  final int emplID) {
    String status = "APPROVED";
    LeaveDetails leav = listByLeaveId(levId);
    Employee employee = Employee.listById(emplID);
    if (leav.getLevStatus() == LeaveStatus.DENIED) {
      int leaveBal = employee.getEmpLeaveBalance();
      int newLevBal = leaveBal - leav.getLevNoOfDays();
      dao().approve(mgrComments, status, levId, emplID);
      dao().change(emplID, newLevBal);
    } else if (leav.getLevStatus() == LeaveStatus.PENDING) {
      dao().approve(mgrComments, status, levId, emplID);
    }
    String s = "LEAVE IS APPROVED";
    return s;

  }

     /**
     * deny the leave application.
     * @param levId id to get leave details.
     * @param mgrComments to update manager comments.
     * @param emplID id to employee id.
     * @return String;
     */
  public static String denyLeave(final String mgrComments, final int levId, final int emplID) {
    String status = "DENIED";
    Employee employee = Employee.listById(emplID);
    int levBalance = employee.getEmpLeaveBalance();
    LeaveDetails lv = LeaveDetails.listByLeaveId(levId);
    int newLevBal = levBalance + lv.getLevNoOfDays();
    dao().deny(mgrComments, status, levId);
    dao().change(emplID, newLevBal);
    String s = "LEAVE IS DENIED";
    return s;
  }
}
