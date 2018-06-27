package com.hexaware.ftp07.util;
import java.util.Scanner;

import com.hexaware.ftp07.model.Employee;
import com.hexaware.ftp07.model.LeaveDetails;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() throws ParseException {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply for Leave");
    System.out.println("4. View Leave History");
    System.out.println("5. View Pending Leave");
    System.out.println("6. Aprrove/Deny Leave");
    System.out.println("7.Exit");
    System.out.println("Enter your choice:");
    String s9 = option.next();
    int menuOption = 0;
    try {
      menuOption = Integer.parseInt(s9);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Choice     |");
      System.out.println(" --------------------------------");
      mainMenu();
    }
    mainMenuDetails(menuOption);
  }
  private void mainMenuDetails(final int selectedOption) throws ParseException {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        apply();
        break;
      case 4:
        showHistory();
        break;
      case 5:
        viewPending();
        break;
      case 6:
        approveDeny();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose either 1, 2, 3, 4, 5, 6 or 7");
    }
    mainMenu();
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println(" -----------------------");
      System.out.println("|Sorry, No such employee|");
      System.out.println(" -----------------------");
    } else {
      System.out.println(employee.getEmpID() + " " + employee.getEmpName() + " " + employee.getEmpMail() + " "
              + employee.getEmpPhone() + " " + employee.getEmpDoj() + " " + employee.getEmpDept() + " "
              + employee.getEmpMgrId() + " " + employee.getEmpLeaveBalance());
    }
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.getEmpID() + " " + e.getEmpName() + " " + e.getEmpMail() + " "
              + e.getEmpPhone() +  " " + e.getEmpDoj() + " " + e.getEmpDept() + " "
              + e.getEmpMgrId() + " " + e.getEmpLeaveBalance());
    }
  }

  private void apply() throws ParseException {
    System.out.println("Enter an Employee Id");
    String s1 = option.next();
    int empID = 0;
    try {
      empID = Integer.parseInt(s1);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Employee ID|");
      System.out.println(" --------------------------------");
      apply();
    }
    Employee employee = Employee.listById(empID);

    if (employee == null) {
      System.out.println(" -----------------------");
      System.out.println("|Sorry, No such employee|");
      System.out.println(" -----------------------");
    } else {
      if (employee.getEmpLeaveBalance() > 0) {
        String startDate = start();
        String endDate = endDt();
        System.out.println("Enter the number of Days");
        String s8 = option.next();
        int levTotalDays = 0;
        try {
          levTotalDays = Integer.parseInt(s8);
        } catch (NumberFormatException e) {
          System.out.println(" -----------------------------------");
          System.out.println("|Please Enter Correct number of days|");
          System.out.println(" -----------------------------------");
          apply();
        }
        System.out.println("Enter the Reason");
        String reason1 = option.nextLine();
        String reason2 = option.nextLine();
        String reason = reason1 + reason2;
        Employee.checkLeave(startDate, endDate, reason, empID, levTotalDays);
      }
    }
  }
  private String start() {
    System.out.println("Enter Start Date");
    String startDate = option.next();
    try {
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      df.setLenient(false);
      df.parse(startDate);
    } catch (ParseException e) {
      System.out.println(" -----------------------------------------");
      System.out.println("|       Invalid Start Date                |");
      System.out.println(" -----------------------------------------");
      start();
    }
    return startDate;
  }
  private String endDt() {
    System.out.println("Enter End Date");
    String endDate = option.next();
    try {
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      df.setLenient(false);
      df.parse(endDate);
    } catch (ParseException e) {
      System.out.println(" -----------------------------------------");
      System.out.println("|       Invalid End Date                  |");
      System.out.println(" -----------------------------------------");
      endDt();
    }
    return endDate;
  }
  private void showHistory() {
    System.out.println("Enter an Employee Id");
    String s2 = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s2);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Employee ID|");
      System.out.println(" --------------------------------");
      showHistory();
    }

    Employee employee = Employee.listById(empId);

    if (employee == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
    } else {
      LeaveDetails[] levDetails = LeaveDetails.listLeaveDetails(empId);

      for (LeaveDetails ld : levDetails) {
        System.out.println(ld.toString());
      }
    }

  }

  private void viewPending() {
    System.out.println("Enter your Manager Id");
    String s = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Manager ID |");
      System.out.println(" --------------------------------");
      viewPending();

    }
    Employee employee = Employee.listById(empId);
    Employee emp = Employee.findMgr(empId);
    if (emp == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
      viewPending();
    }
    if (employee == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
    } else {
      LeaveDetails[] levDetails = LeaveDetails.listPendingApplications(empId);
      try {
        if (levDetails.length == 0) {
          throw new IllegalArgumentException("No applications are pending");
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e);
        viewPending();
      }
      for (LeaveDetails ld : levDetails) {
        System.out.println(ld.toString());
      }
    }
  }
  private void approveDeny() throws ParseException {
    System.out.println("Enter Manager Id");
    String s3 = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s3);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Manager ID |");
      System.out.println(" --------------------------------");
      approveDeny();
    }
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
    } else {
      LeaveDetails[] levDetails = LeaveDetails.listPendingApplications(empId);
      if (levDetails.length == 0) {
        System.out.println(" --------------------------------");
        System.out.println("| Sorry, No Pending Applications |");
        System.out.println(" --------------------------------");
      } else {
        for (LeaveDetails ld : levDetails) {
          System.out.println(ld.toString());
        }
        System.out.println("Enter the Leave id of the application you want to approve");
        int levId = option.nextInt();
        LeaveDetails l = LeaveDetails.listByLeaveId(levId);
        if (l == null) {
          System.out.println(" ----------------------------------------");
          System.out.println("| Sorry, No Such Leave Application Exist |");
          System.out.println(" ----------------------------------------");
        } else {
          System.out.println("\n***************************");
          System.out.println("1. Approve the application ");
          System.out.println("2. Deny the Application");
          System.out.println("***************************");
          int menuOption = option.nextInt();
          menuDetails(menuOption, levId);
        }
      }
    }
  }
  private void menuDetails(final int menuOption, final int levId) throws ParseException {
    switch (menuOption) {
      case 1:
        approve(levId);
        break;
      case 2:
        deny(levId);
        break;
      default:
        System.out.println("Choose either 1, 2");
    }
    mainMenu();
  }

  private void approve(final int levId) {
    System.out.println("\nEnter the employee id ");
    String s4 = option.next();
    int emplID = 0;
    try {
      emplID = Integer.parseInt(s4);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Employee ID|");
      System.out.println(" --------------------------------");
      approve(levId);
    }
    Employee employee = Employee.listById(emplID);
    if (employee == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
      approve(levId);
    } else {
      LeaveDetails lv = LeaveDetails.listByLeaveId(levId);
      if (lv == null) {
        System.out.println(" ----------------------------------------");
        System.out.println("| Sorry, No Such Leave Application Exist |");
        System.out.println(" ----------------------------------------");
      } else {
        System.out.println("\nEnter your comments here");
        String mgrComments1 = option.nextLine();
        String mgrComments2 = option.nextLine();
        String mgrComments = mgrComments1 + mgrComments2;
        LeaveDetails.approveLeave(mgrComments, levId, emplID);
        System.out.println("\n *************  Approved Successfuly!!  **************");
      }
    }
  }

  private void deny(final int levId) {
    System.out.println("***************************");
    System.out.println("Enter the employee id ");
    String s5 = option.next();
    int emplID = 0;
    try {
      emplID = Integer.parseInt(s5);
    } catch (NumberFormatException e) {
      System.out.println(" --------------------------------");
      System.out.println("|Please Enter Correct Employee ID|");
      System.out.println(" --------------------------------");
      deny(levId);
    }
    Employee employee = Employee.listById(emplID);
    if (employee == null) {
      System.out.println(" --------------------------------");
      System.out.println("|     Sorry, No such Employee    |");
      System.out.println(" --------------------------------");
      deny(levId);
    } else {
      System.out.println("\nEnter your comments here");
      String mgrComments1 = option.nextLine();
      String mgrComments2 = option.nextLine();
      String mgrComments = mgrComments1 + mgrComments2;
      LeaveDetails.denyLeave(mgrComments, levId, emplID);
      System.out.println("\n ************  Leave has been Denied!  ***************");
    }
  }

  /**
   * The main entry point.
   * @param ar the list of arguments
   * @throws ParseException which throws Exception
   */
  public static void main(final String[] ar) throws ParseException {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
