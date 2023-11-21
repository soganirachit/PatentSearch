package com.patent.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.patent.model.PatentDetails;

public class SaveDataToDB {

	SessionFactory sf;
	Session session;

	public SaveDataToDB() {
		super();
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		session.beginTransaction();
	}

	public boolean saveData(PatentDetails pd) {

		session.save(pd);
		
		return true;
	}

	public boolean commitAndCoseTransaction() {
		session.getTransaction().commit();
		session.close();
		return true;
	}

}
