package com.hexaware.ftp07.util;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp07.model.Employee;
import com.hexaware.ftp07.model.LeaveDetails;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/employees")
public class EmployeeRest {

  /**
   * Returns a list of all the employees.
   * @return a list of all the employees
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Employee[] employeesList() {
    System.out.println("Employees List");
    final Employee[] employees = Employee.listAll();
    return employees;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Employee employeeListById(@PathParam("id") final int id) {
    final Employee empl = Employee.listById(id);
    if (empl == null) {
      throw new NotFoundException("No such Employee ID: " + id);
    }
    return empl;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the manager.
   * @return the employee details
   */
  @GET
  @Path("/manager/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Employee employeeListByManager(@PathParam("id") final int id) {
    final Employee emp2 = Employee.manager(id);
    if (emp2 == null) {
      throw new NotFoundException("No such Employee ID: " + id);
    }
    return emp2;
  }



/**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the employee details
   */

  @GET
  @Path("/viewpending/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] employeeLeaveHistory(@PathParam("id") final int id) {
    final Employee employee = Employee.listById(id);
    final Employee emp = Employee.findMgr(id);
    if (employee == null) {
      throw new NotFoundException("No such Employee : " + id);
    }
    if (emp == null) {
      throw new NotFoundException("No such Manager : " + id);
    }
    final LeaveDetails[] levDetails = LeaveDetails.listPendingApplications(id);
    return levDetails;
  }

    /**
   * Returns a specific employee's details.
   * @param empID the id of the employee
   * @param startDate for start date.
   * @param endDate for end date.
   * @param reason for leave reason.
   * @param levTotalDays for total number of days.
   * @param ld for leave details.
   * @return result which returns String.
   * @throws ParseException which throws ParseException
   */

  @POST
  @Path("/applyleave/{empID}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String employeeApplyLeave(@PathParam("startDate") final String startDate,
      @PathParam("endDate") final String endDate,
      @PathParam("reason") final String reason,
      @PathParam("empID") final int empID,
      @PathParam("levTotalDays") final int levTotalDays, final LeaveDetails ld) throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    String result = Employee.checkLeave(sf.format(ld.getLevStartDate()), sf.format(ld.getLevEndDate()),
          ld.getLevReason(), empID, ld.getLevNoOfDays());
    return result;
  }
  /**
   * Returns a specific employee's leave history.
   * @param id the id of the employee
   * @return the employee leave history
   */
  @GET
   @Path("/LeaveHistory/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public final LeaveDetails[] leaveListById(@PathParam("id") final int id) {
    final LeaveDetails[] levDetails = LeaveDetails.listLeaveDetails(id);
    return levDetails;
  }
 /**
   * Returns a list of all the employees.
   * @param levId the id of the employee.
   * @param l list of leave details.
   * @return s a list of all the employees
   */
  @POST
  @Path("/leavedetails/{levId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String approveStatus(@PathParam("levId") final int levId, final LeaveDetails l) {
    final String s = LeaveDetails.approveLeave(l.getLevMgrComments(), levId, l.getEmplId());
    System.out.println(s);
    return s;
  }
  /**
   * Returns a specific employee's details.
   * @param levId the id of the employee
   * @param emplID the id of the employee
   * @return the employee details
   */
  @GET
  @Path("/leavedetails/{levId}/{emplID}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails leaveDetailListByApplication(@PathParam("levId") final int levId,
                                        @PathParam("emplID") final int emplID) {
    final LeaveDetails ld = LeaveDetails.listByApplication(levId, emplID);
    if (ld == null) {
      throw new NotFoundException("No such Leave Details");
    }
    return ld;
  }
/**
   * Returns a list of all the employees.
   * @param id the id of the employee.
   * @param l is the object of leavedetails.
   * @return s a list of all the employees.
   */
  @POST
  @Path("/denyleave/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String denyStatus(@PathParam("id") final int id,
                                 final LeaveDetails l) {
    final String s = LeaveDetails.denyLeave(l.getLevMgrComments(), id, l.getEmplId());
    System.out.println(s);
    return s;
  }

   /**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the employee details
   */

  @GET
  @Path("/listempbyid/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Set<Employee> leaveHistoryDetails(@PathParam("id") final int id) {
    Set<Employee> a = new HashSet<Employee>();
    final LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(id);
    for (int i = 0; i < leaDetails.length; i++) {
      final Employee employee = Employee.listById(leaDetails[i].getEmplId());
      a.add(employee);
    }
    return a;
  }
  /**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the employee details
   */

  @GET
  @Path("/listemppending/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final List<LeaveDetails[]> empHistoryDetails(@PathParam("id") final int id) {
    List<LeaveDetails[]> b = new ArrayList<LeaveDetails[]>();
    final LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(id);
    Set<Integer> empList = new TreeSet<Integer>();
    for (int i = 0; i < leaDetails.length; i++) {
      empList.add(leaDetails[i].getEmplId());
    }
    System.out.println(empList.size());
    Iterator<Integer> itr = empList.iterator();
    while (itr.hasNext()) {
      final LeaveDetails[] leaveDetailsByEmployee = LeaveDetails.listLeaveDetails(itr.next());
      b.add(leaveDetailsByEmployee);
    }
    System.out.println(b.size());
    return b;
  }
}
