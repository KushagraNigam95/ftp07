package com.hexaware.ftp07.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.ftp07.model.LeaveDetails;
import com.hexaware.ftp07.model.LeaveStatus;
import com.hexaware.ftp07.model.LeaveType;
/**
 * Mapper class to map from result set to employee object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
    /**
     * @param idx the index
     * @param rs the resultset
     * @param ctx the context
     * @return the mapped employee object
     * @throws SQLException in case there is an error in fetching data from the resultset
     */
  public final LeaveDetails map(final int idx, final ResultSet rs, final      StatementContext ctx)
  throws SQLException {

    String s1 = rs.getString("LEV_TYPE");
    LeaveType l1 = LeaveType.valueOf(s1);

    String s2 = rs.getString("LEV_STATUS");
    LeaveStatus l2 = LeaveStatus.valueOf(s2);
        /**
         * @return LeaveHistory
         */
    return new LeaveDetails(rs.getInt("LEV_ID"), rs.getInt("EMP_ID"), rs.getDate("LEV_START"), rs.getDate("LEV_END"),
                rs.getInt("LEV_NO_OF_DAYS"), rs.getString("LEV_REASON"),
                l1, rs.getDate("LEV_APPLIEDDATE"), rs.getString("LEV_COMMENT"), l2);

  }
}
