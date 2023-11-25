package com.patent.db;

import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.GenericJDBCException;

import com.patent.model.PatentDetails;

public class SaveDataToDB {

	SessionFactory sf;
	Session session;

	public SaveDataToDB() {
		super();
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
	}

	public void saveData(Map<String, PatentDetails> pdMap, Map<String, String> status)
			throws PersistenceException, GenericJDBCException {

		PatentDetails pd = null;
		try {
			session.beginTransaction();
			for (String appNum : pdMap.keySet()) {
				try {
					pd = pdMap.get(appNum);
					if (!session.contains(pd) & session.getTransaction().isActive()) {
						session.save(pd);
						status.put(appNum, "Success");
						System.out.println("successfully saved data of application number : " + appNum);
					} else {
						System.out.println("Failed saved data of application number : " + appNum);
					}

				} catch (Exception e) {
					session.evict(pd);
					status.put(appNum, "Failure due to exception : " + e.getMessage());
					System.out.println(
							"Failed inserting data of application number : " + appNum + "		" + e.getMessage());
					e.printStackTrace();
				}
				System.out.println("\n" + "\n");
			}
			session.flush();
			session.getTransaction().commit();
			session.clear();
			System.out.println("Inserted 50 records successfully ................");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean commitAndCoseTransaction() {
		// session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return true;
	}
}
