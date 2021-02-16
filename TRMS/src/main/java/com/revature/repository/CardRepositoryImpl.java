package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Card;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

/*
 * This class follows the Data Access Object (DAO) design pattern. The goal of
 * using this class is to have an object whose single responsibility is to
 * interact with our data source.
 */
public class CardRepositoryImpl implements CardRepository {

	@Override
	public void insert(Card card) {
		/*
		 * Generally, there is JDBC workflow that is easy to follow. You are always, for
		 * instance, going to need to grab a JDBC connection. After that, you will
		 * execute a SQL query. If my query should return a result set, I will store
		 * that result set.
		 */

		/*
		 * NOTE: We will modify this method so that we are using a PreparedStatement
		 * instead of a simple Statement. A PreparedStatement protects against SQL
		 * injection by precompiling our SQL query and allowing us to parameterize our
		 * user input. A PreparedStatement can also be re-used with new parameter
		 * values, which can make it faster than using a simple Statement.
		 */

		Connection conn = null;
		PreparedStatement stmt = null;
		/*
		 * Note that a PreparedStatement is parameterized. This means that we do need to
		 * pass in the values for those parameters later.
		 */
		final String SQL = "insert into card values(default, ?, ?, ?, ?)";

		try {
			// Attempt to get a connection.
			conn = ConnectionFactory.getConnection();
			// Use our connection to create a SQL query
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, card.getName());
			stmt.setInt(2, card.getType_id());
			stmt.setBoolean(3, card.isFaceup());
			stmt.setDate(4, card.getDate());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}

	@Override
	public List<Card> findAll() {
		List<Card> cards = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from card";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);

			while (set.next()) {
				cards.add(new Card(set.getInt(1), set.getString(2), set.getInt(3), set.getBoolean(4), set.getDate(5)));
			}

			/*
			 * Please DO NOT do this. There is not a reason to sort through cards, remove
			 * cards, add cards, etc. to the data in your data layer.
			 * 
			 * Because:
			 * 
			 * 1. Your findAll method now is boxed in to the one implementation in which it
			 * always removes cards from the List. This means that you don't have a truly
			 * have a method which allows you to findAll cards anymore.
			 */
//			cards.removeIf((c) -> c.getType_id() < 2);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
			ConnectionClosers.close(set);
		}

		return cards;
	}

	@Override
	public Card findByName(String name) {

		Card returnedCard = null;

		Connection conn = null;
		Statement stmt = null;
		// We need a ResultSet this time to store the returned records.
		ResultSet set = null;
		final String SQL = "select * from card where card_name = '" + name + "'";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);

			if (set.next()) {
				// Extract the data from the row the cursor is positioned at
				returnedCard = new Card(set.getInt(1), set.getString(2), set.getInt(3), set.getBoolean(4),
						set.getDate(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
			ConnectionClosers.close(set);
		}

		return returnedCard;
	}

	@Override
	public void update(Card card) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Card card) {
		// TODO Auto-generated method stub

	}

}
