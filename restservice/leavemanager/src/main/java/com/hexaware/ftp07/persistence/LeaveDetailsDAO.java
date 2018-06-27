package com.hexaware.ftp07.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;
import com.hexaware.ftp07.model.LeaveDetails;

/**
 * The DAO class for employee.
 */
public interface LeaveDetailsDAO {

    /**
     * return all the details of the selected employee.
     *
     * @param empID the id of the employee
     * @return the employee object
     */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE EMP_ID = :empID")
    @Mapper(LeaveDetailsMapper.class)
    List<LeaveDetails> list(@Bind("empID") int empID);

    /**
    * return all the details of the selected employee.
    *
    * @param empId the id of the employee
    * @return the employee object
    */
  @SqlQuery("SELECT * "
            +
            "FROM LEAVE_HISTORY "
            +
            "INNER JOIN EMPLOYEE ON EMPLOYEE.EMP_ID = LEAVE_HISTORY.EMP_ID "
            +
            "WHERE "
            +
            "EMPLOYEE.EMP_MGRID = :empId "
            +
            "AND "
            +
            "LEAVE_HISTORY.LEV_STATUS IN ('PENDING', 'DENIED', 'APPROVED')")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> finds(@Bind("empId") int empId);

  /**
    * return all the leave details of the selected employee.
    * @param levId the id of the employee
    * @return the LeaveDetails object
    */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE LEV_ID = :levId")
    @Mapper(LeaveDetailsMapper.class)
    LeaveDetails send(@Bind("levId") int levId);
  /**
     * @param mgrComments the id of the employee
     * @param levId the id of the Leave application
     * @param status is the status of application
     * @param empId is the employee ID of employee.
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEV_COMMENT = :mgrComments, "
            +
            "    LEV_STATUS = :status"
            +
            "    WHERE LEV_ID = :levId and EMP_ID = :empId")
    void approve(@Bind("mgrComments") String mgrComments, @Bind("status") String status,
      @Bind("levId") int levId, @Bind("empId") int empId);

    /**
     * @param mgrComments the id of the employee
     * @param levId the id of the Leave application
     * @param status is the status of application
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEV_COMMENT = :mgrComments, "
            +
            "    LEV_STATUS = :status"
            +
            "    WHERE LEV_ID = :levId")
    void deny(@Bind("mgrComments") String mgrComments, @Bind("status") String status,
      @Bind("levId") int levId);

    /**
     * @param  emplID of the employee.
     * @param newLevBal the id of the Leave balance.
     */
  @SqlUpdate("UPDATE EMPLOYEE SET"
            +
            "    EMP_LEAVEBALANCE = :newLevBal "
            +
            "    WHERE EMP_ID = :emplID")
    void change(@Bind("emplID") int emplID, @Bind("newLevBal") int newLevBal);


  /**
   * return all the leave details of the selected employee.
   * @param emplID the id of the manager
   * @param levId the id of the employee
   * @return the leave detail object
   */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE LEV_ID = :levId"
          + " AND EMP_ID = :emplID")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails appdeny(@Bind("levId") int levId,
                      @Bind("emplID") int emplID);
    /**
     * close with no args is used to close the connection.
     */
  void close();
}
