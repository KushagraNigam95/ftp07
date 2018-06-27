package com.hexaware.ftp07.model;

//import com.hexaware.ftp07.model.Employee;
//import com.hexaware.ftp07.model.Employee;
import com.hexaware.ftp07.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;
import java.text.ParseException;

import java.util.ArrayList;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployee() {
    Employee e100 = new Employee(100, "Mahesh", "mah12@gmail.com",
            9981893034L, "2017/09/14", 5, "hexavarsity", 101);
    Employee e101 = new Employee(101);
    assertNotEquals(e100, null);
    assertNotEquals(e100, new Integer(100));
    assertEquals(e100, new Employee(100, "Mahesh", "mah12@gmail.com",
            9981893034L, "2017/09/14", 5, "hexavarsity", 101));
    assertNotEquals(e101, new Employee(100, "Mahesh", "mah12@gmail.com",
            9981893034L, "2017/09/14", 5, "hexavarsity", 101));
    assertEquals(e100.hashCode(), new Employee(100, "Mahesh", "mah12@gmail.com",
            9981893034L, "2017/09/14", 5, "hexavarsity", 101).hashCode());
    assertEquals(e100.getEmpID(), new Employee(100).getEmpID());
    e101.setEmpID(100);
    e101.setEmpName("Mahesh");
    e101.setEmpMail("mah12@gmail.com");
    e101.setEmpPhone(9981893034L);
    e101.setEmpLeaveBalance(5);
    e101.setEmpDoj("2017/09/14");
    e101.setEmpDept("hexavarsity");
    e101.setEmpMgrId(101);
    assertEquals(e101, new Employee(100, "Mahesh", "mah12@gmail.com",
            9981893034L, "2017/09/14", 5, "hexavarsity", 101));
    assertEquals("ID mismatch", 9981893034L, e100.getEmpPhone());
    assertEquals("name mismatch", "Mahesh", e100.getEmpName());
    assertEquals("maild id mismatch", "mah12@gmail.com", e100.getEmpMail());
    assertEquals("Phone mismatch", 100, e100.getEmpID());
    assertEquals("date of joining mismatch", "2017/09/14", e100.getEmpDoj());
    assertEquals("Employee Leave Balance mismatch", 5, e100.getEmpLeaveBalance());
    assertEquals("Employeee department mismatch", "hexavarsity", e100.getEmpDept());
    assertEquals("Manager id mismatch", 101, e100.getEmpMgrId());
    assertNotEquals(102, e100.getEmpName());
  }

  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListManager(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.findMgr(100); result = e100;
        dao.findMgr(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.findMgr(100);
    assertEquals(e100, e);

    e = Employee.findMgr(-1);
    assertNull(e);
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        es.add(new Employee(1, "Mahesh", "mah12@gmail.com", 9981893034L, "2017/09/14", 5, "hexavarsity", 101));
        es.add(new Employee(10, "Ramesh", "ram12@gmail.com", 9484894034L, "2017/09/14", 10, "stg", 111));
        es.add(new Employee(100));
        dao.list(); result = es;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(3, es.length);
    assertEquals(new Employee(1, "Mahesh", "mah12@gmail.com", 9981893034L, "2017/09/14", 5, "hexavarsity", 101), es[0]);
    assertEquals(new Employee(10, "Ramesh", "ram12@gmail.com", 9484894034L, "2017/09/14", 10, "stg", 111), es[1]);
    assertEquals(new Employee(100), es[2]);
    assertNotEquals(new Employee(1, "Mahesh", "mah12@gmail.com", 9981893034L,
        "2017/09/14", 5, "hexavarsity", 101), es[2]);
  }

  /**
   * Test that insertion of employee details works correctly.
   * @param dao mocking the dao class.
   * @throws ParseException to handle the exception.
   */
  @Test
  public final void testApplyLeave(@Mocked final EmployeeDAO dao) throws ParseException {
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee emp = new Employee(1000, "SALONI", "saloni25gupta@gmail.com", 9754092430L, "2017/11/14", 15, "HR", 3001);
    Employee emp1 = new Employee(3000, "KUSHAGRA", "kushagra.n1995@gmail.com",
        8989468819L, "2017/11/14", 6, "BPO", 0);
    Employee emp2 = new Employee(2000, "RIYA", "riya.n1995@gmail.com",
        8989478881L, "2017/11/14", 3, "BPO", 3000);
    Employee emp3 = new Employee(2001, "SIDDHANT", "sidhant.n1995@gmail.com",
        8989468819L, "2017/11/14", 7, "BPO", 3001);
    Employee emp4 = new Employee(3001, "MIDHUN", "midhun.n1995@gmail.com",
        8989468819L, "2017/11/14", 5, "BPO", 3000);
    Employee emp5 = new Employee(1000, "SALONI", "saloni.n1995@gmail.com",
        8989468819L, "2017/11/14", 5, "BPO", 3000);
    String s = "Leave applied successfully";
    String s5 = "You have already applied for leave in this period";
    String s1 = "Leave Auto Approved";
    String s2 = "please enter correct dates";
    String str = emp.applyLeave("2018/05/25", "2018/05/30", "sick", 1000, 3, 12, 3001);
    String str1 = emp1.applyLeave("2018/05/05", "2018/05/10", "sick", 3000, 3, 3, 0);
    String str2 = emp2.applyLeave("2018/05/25", "2018/05/28", "sick", 2000, 3, 3, 3000);
    String str3 = emp3.applyLeave("2018/05/05", "2018/05/10", "sick", 2001, 3, 3, 3001);
    String str4 = emp4.applyLeave("2018/05/05", "2018/05/10", "sick", 3001, 3, 3, 3000);
    String str5 = emp5.applyLeave("2018/04/11", "2018/04/25", "sick", 1000, 17, 0, 3001);
    assertEquals("first test case", s, str);
    assertEquals("second test case", s1, str1);
    assertEquals("first test case", s, str2);
    assertEquals("first test case", s, str3);
    assertEquals("first test case", s, str4);
    assertEquals(s2, str5);
    assertNotEquals(s1, str5);
    assertNotEquals(s, str1);
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.find(100); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(100);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }
}
