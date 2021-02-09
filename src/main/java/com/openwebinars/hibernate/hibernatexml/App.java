package com.openwebinars.hibernate.hibernatexml;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Mapeo de entidades con ficheros XML (Hibernate nativo)
 * www.openwebinars.net
 * @LuisMLopezMag
 *
 */
public class App {
	public static void main(String[] args) {
		
		
		// Inicializamos un objeto SessionFactory con la configuración
		// del fichero hibernate.cfg.xml
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		//SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		//SessionFactory sf =new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		//Por stackoverflow 9/02/2021
		/*
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		org.hibernate.service.ServiceRegistry  serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();        
		SessionFactory sf= configuration.buildSessionFactory(serviceRegistry);
		*/
		//Legacy
		//SessionFactory sf = new Configuration().configure().buildSessionFactory();

		
		// Iniciamos una sesión
		SessionFactory sf=HibernateUtil.getSessionFactory();
		
		Session session = sf.openSession();

		// Construimos un objeto de tipo User
		User user1 = new User();
		user1.setId(1);
		user1.setUserName("Pepe");
		user1.setUserMessage("Hello world from Pepe - Julio");

		// Construimos otro objeto de tipo User
		User user2 = new User();
		user2.setId(2);
		user2.setUserName("Juan");
		user2.setUserMessage("Hello world from Juan - Julio");

		// Iniciamos una transacción dentro de la sesión
		session.beginTransaction();

		// Almacenamos los objetos
		session.save(user1);
		session.save(user2);

		// Commiteamos la transacción
		session.getTransaction().commit();

		// Cerramos todos los objetos
		session.close();
		sf.close();
	}
}
