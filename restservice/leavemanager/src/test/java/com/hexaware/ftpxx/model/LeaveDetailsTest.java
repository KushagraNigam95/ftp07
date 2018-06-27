package com.hexaware.ftp07.model;
import com.hexaware.ftp07.persistence.LeaveDetailsDAO;
import org.junit.Before;
import org.junit.Test;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.util.ArrayList;

/**
 * Test class for LeaveDetails.
 */
@RunWith(JMockit.class)
public class LeaveDetailsTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the leaveDetails class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testLeaveDetails() throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(1, 1000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
        new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
        LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED);
    LeaveDetails l101 = new LeaveDetails(1, 1000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
        new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
        LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED);
    assertEquals(l100.hashCode(), new LeaveDetails(1, 1000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
        new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
        LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED).hashCode());
    assertEquals(1, l100.getLevId());
    l101.setLevId(1);
    assertEquals(1000, l100.getEmplId());
    l101.setEmplId(1000);
    assertEquals(new java.sql.Date(sf.parse("2017/12/15").getTime()), l100.getLevStartDate());
    l101.setLevStartDate(new java.sql.Date(sf.parse("2017/12/15").getTime()));
    assertEquals(new java.sql.Date(sf.parse("2017/12/17").getTime()), l100.getLevEndDate());
    l101.setLevEndDate(new java.sql.Date(sf.parse("2017/12/17").getTime()));
    assertEquals(2, l100.getLevNoOfDays());
    l101.setLevNoOfDays(2);
    assertEquals("SICK", l100.getLevReason());
    l101.setLevReason("SICK");
    assertEquals(LeaveType.EL, l100.getLevType());
    l101.setLevType(LeaveType.EL);
    assertEquals(new java.sql.Date(sf.parse("2017/12/11").getTime()), l100.getLevAppliedOn());
    l101.setLevAppliedOn(new java.sql.Date(sf.parse("2017/12/11").getTime()));
    assertEquals("enjoy", l100.getLevMgrComments());
    l101.setLevMgrComments("enjoy");
    assertEquals(LeaveStatus.APPROVED, l100.getLevStatus());
    l101.setLevStatus(LeaveStatus.APPROVED);
    String toS =  "leave id              : " + 1 + "\n" + "Employee ID           : " + 1000 + "\n"
                + "leave start date      : " + "2017-12-15" + "\n" + "leave end date        : " + "2017-12-17" + "\n"
                + "leave no of days      : "  + 2 + "\n" + "leave reason          : " + "SICK" + "\n"
                + "leave type            : " + "EL" + "\n"
                + "leave applied on      : " + "2017-12-11"  + "\n" + "leave manager comment : " + "enjoy" + "\n"
                + "leave status          : "  + "APPROVED" + "\n";
    String s = l100.toString();
    assertEquals(toS, s);
    System.out.println("To string method of employee tested");
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListByLeaveId(@Mocked final LeaveDetailsDAO dao) {
    final LeaveDetails e100 = new LeaveDetails(100);
    new Expectations() {
      {
        dao.send(100); result = e100;
        dao.send(-1); result = null;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails e = LeaveDetails.listByLeaveId(100);
    assertEquals(e100, e);

    e = LeaveDetails.listByLeaveId(-1);
    assertNull(e);
  }
  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistLeaveDetails(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.list(1000);
        ArrayList<LeaveDetails> ls = new ArrayList<LeaveDetails>();
        ls.add(new LeaveDetails(1, 1000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
            new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
            LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED));
        ls.add(new LeaveDetails(2, 2000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
            new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
            LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED));
        result = ls;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ls = LeaveDetails.listLeaveDetails(1000);
    LeaveDetails[] ls1 = new LeaveDetails[2];
    ls1[0] = new LeaveDetails(1, 1000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
        new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
        LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED);
    ls1[1] = new LeaveDetails(2, 2000, new java.sql.Date(sf.parse("2017/12/15").getTime()),
        new java.sql.Date(sf.parse("2017/12/17").getTime()), 2, "SICK",
        LeaveType.EL, new java.sql.Date(sf.parse("2017/12/11").getTime()), "enjoy", LeaveStatus.APPROVED);
    assertArrayEquals(ls1, ls);
    System.out.println("List_Leave_Details Test");
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testPendingApplication(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.finds(3000);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(5, 2000, new java.sql.Date(sf.parse("2018/01/01").getTime()),
                                new java.sql.Date(sf.parse("2018/01/02").getTime()), 6, "sick",
                                LeaveType.EL, new java.sql.Date(sf.parse("2017/12/14").getTime()),
                                "approved", LeaveStatus.PENDING));
        ld.add(new LeaveDetails(6, 3000, new java.sql.Date(sf.parse("2018/01/01").getTime()),
                                new java.sql.Date(sf.parse("2018/01/02").getTime()), 6, "sick",
                                LeaveType.EL, new java.sql.Date(sf.parse("2017/12/14").getTime()),
                                "approved", LeaveStatus.PENDING));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listPendingApplications(3000);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(5, 2000, new java.sql.Date(sf.parse("2018/01/01").getTime()),
                                new java.sql.Date(sf.parse("2018/01/02").getTime()), 6, "sick",
                                LeaveType.EL, new java.sql.Date(sf.parse("2017/12/14").getTime()),
                                "approved", LeaveStatus.PENDING);
    ld1[1] = new LeaveDetails(6, 3000, new java.sql.Date(sf.parse("2018/01/01").getTime()),
                                new java.sql.Date(sf.parse("2018/01/02").getTime()), 6, "sick",
                                LeaveType.EL, new java.sql.Date(sf.parse("2017/12/14").getTime()),
                                "approved", LeaveStatus.PENDING);
    assertArrayEquals(ld1, ld);
    System.out.println("Pending leave Test");
  }
/**
   * Tests that a fetch approve method works correctly.
   * @throws ParseException to handle parse exception.
   * @param dao mocking the dao class
   */
  @Test
  public final void testApproveLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l1 = new LeaveDetails(1, 2000, new java.sql.Date(sf.parse("2017/12/22").getTime()),
        new java.sql.Date(sf.parse("2017/12/26").getTime()), 5, "out", LeaveType.EL,
        new java.sql.Date(sf.parse("2017/12/11").getTime()),
        "ENJOY", LeaveStatus.APPROVED);
    String ld = l1.approveLeave("ENJOY", 1, 2000);
    String l2 = "LEAVE IS APPROVED";
    assertEquals(ld, l2);

  }
  /**
   * Tests that a fetch deny method works correctly.
   * @throws ParseException to handle parse exception.
   * @param dao mocking the dao class
   */
  @Test
  public final void testDenyLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    /*new Expectations() {
      {

        dao.deny("SORRY", "DENIED", 1);
        dao.change(2000, 7);

      }
    };*/
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l3 = new LeaveDetails(1, 2000, new java.sql.Date(sf.parse("2017/12/22").getTime()),
        new java.sql.Date(sf.parse("2017/12/26").getTime()), 5, "out", LeaveType.EL,
        new java.sql.Date(sf.parse("2017/12/11").getTime()),
        "ENJOY", LeaveStatus.DENIED);
    String ld1 = l3.denyLeave("SORRY", 1, 2000);
    String l4 = "LEAVE IS DENIED";
    assertEquals(ld1, l4);

  }
}
