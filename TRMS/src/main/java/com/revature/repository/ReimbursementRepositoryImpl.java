package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;
import com.revature.util.HibernateSessionFactory;

public class ReimbursementRepositoryImpl implements ReimbursementRepository {

	//@Override
	public void insert2(Reimbursement reimbursement) {
		Connection conn = null;
		PreparedStatement stmt = null;
		/*
		 * Note that a PreparedStatement is parameterized. This means that we do need to
		 * pass in the values for those parameters later.
		 */
		final String SQL = "insert into reimbursement values(default, ?, ?, ?, 0)";

		try {
			// Attempt to get a connection.
			conn = ConnectionFactory.getConnection();
			// Use our connection to create a SQL query
			stmt = conn.prepareStatement(SQL);
			stmt.setFloat(1, reimbursement.getAmount());
			stmt.setInt(2, reimbursement.getEmployee_id());
			stmt.setString(3, reimbursement.getStatus());
			stmt.setString(4, reimbursement.getReciept());
			// stmt.setInt(5, 0);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}

	//@Override
	public List<Reimbursement> findAll2() {
		List<Reimbursement> reimbursements = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;

		final String SQL = "select * from reimbursement";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);

			while (set.next()) {
				// extract data from the row the cursor is positioned at
				reimbursements.add(new Reimbursement(set.getInt(1), set.getFloat(2), set.getInt(3), set.getString(4),
						set.getString(5), set.getInt(6)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
			ConnectionClosers.close(set);
		}

		return reimbursements;
	}
	
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbursements = new ArrayList<>();

		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			reimbursements = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}

		return reimbursements;
	}
	
	@Override
	public void insert(Reimbursement r) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.save(r);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
	}
	
	@Override
	public void update(int id, String status) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update reimbursement set status = (?) where reimbursement_id = (?)";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, status);
			stmt.setInt(2, id);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}

}
