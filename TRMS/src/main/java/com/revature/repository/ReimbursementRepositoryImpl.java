package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class ReimbursementRepositoryImpl implements ReimbursementRepository {

	
	@Override
	public void insert(Reimbursement reimbursement) {
		Connection conn = null;
		PreparedStatement stmt = null;
		/*
		 * Note that a PreparedStatement is parameterized. This means that we do need to
		 * pass in the values for those parameters later.
		 */
		final String SQL = "insert into reimbursement values(default, ?, ?, ?, ?)";

		try {
			// Attempt to get a connection.
			conn = ConnectionFactory.getConnection();
			// Use our connection to create a SQL query
			stmt = conn.prepareStatement(SQL);
			stmt.setFloat(1, reimbursement.getAmount());
			stmt.setInt(2, reimbursement.getEmployee_id());
			stmt.setString(3, reimbursement.getStatus());
			stmt.setString(4, reimbursement.getReciept());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}
	
	
}
