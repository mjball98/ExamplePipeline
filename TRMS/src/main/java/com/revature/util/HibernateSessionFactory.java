package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {

	/*
	 * This is a utility class for buiding a SessionFactory and returning Hibernate
	 * Sessions. Note that our SessionFactory will be a singleton as
	 * SessionFactory(ies) are expensive build and we don't need more than one
	 * SessionFactory to retrieve sessions in our application.
	 */

	
	private static SessionFactory sessionFactory;

	public static Session getSession() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:postgresql://jan25instance.cbqll05ud0pv.us-east-2.rds.amazonaws.com:5432/postgres")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "password").buildSessionFactory();
		}

		return sessionFactory.getCurrentSession();
	}
}
