package com.beeva.Jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.Jpa.dao.ClienteDao;
import com.beeva.Jpa.model.Cliente;
import com.beeva.Jpa.mongo.MongoUtils;


@Repository
public class ClienteDaoImpl extends ClienteDao {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Cliente save(Cliente user) {
		em.persist(user);
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml");
		MongoUtils mongo = (MongoUtils) context.getBean("utils");
		mongo.insertRecord(context, user);
		return user;
	}

	@Transactional
	public Cliente getUserById(int id) {
		
		return em.find(Cliente.class, id);
	}

	@Transactional
	@Override
	public void getListCliente() {
		TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
    	List<Cliente> results = query.getResultList();
    	System.out.println("¿A que cliente se le va a asignar la cuenta?");
    	for (Cliente cliente2 : results) {
			System.out.print(cliente2.getIdcliente()+". ");
			System.out.print(cliente2.getApellido() +" ");
			System.out.println(cliente2.getNombre() +" ");
		}
		
	}
}
