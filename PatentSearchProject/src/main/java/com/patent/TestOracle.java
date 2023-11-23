package com.patent;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.patent.model.Inventor;
import com.patent.model.TestModel;

public class TestOracle {
	public static void main(String[] args) {

		TestModel tm = new TestModel();
		Inventor inventor = new Inventor();
		inventor.setAddress("27");
		inventor.setName("aiamsi");
		inventor.setCountry("bharat");
		inventor.setNationality("bhartiye");

		tm.setApplicationNumber("95959");
		tm.setTestName("SUMMARY OF THE INVENTION\r\n" + "5\r\n"
								+ "… FIRST ROTATING MACHINE, MG2 … SECOND ROTATING MACHINE, &#61508;STRK\r\n" + "");
		tm.getInventor().add(inventor);

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		System.out.println("session created");
		Session session = sf.openSession();

		session.beginTransaction();
		Serializable x = session.save(tm);
		session.getTransaction().commit();
		session.close();
		System.out.println("session ended");

	}
}
