package com.beeva.Jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.Jpa.dao.BancoDao;
import com.beeva.Jpa.model.Banco;

@Repository
public class BancoDaoImpl extends BancoDao{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void save(Banco banco) {
			em.persist(banco);
	}
	
	@Transactional
	public void loadBanco() {
			Banco banco0 = new Banco("Bancomer");
			Banco banco1 = new Banco("Banamex");
			Banco banco2 = new Banco("HSBC");
			em.persist(banco0);
			em.persist(banco1);
			em.persist(banco2);
	}
	
	

	@Transactional
	public Banco getBancoById(int id) {
		
		return em.find(Banco.class, id);
	}

	@Override
	@Transactional
	public void getListBanco() {
		TypedQuery<Banco> query = em.createNamedQuery("Banco.findAll", Banco.class);
    	List<Banco> results = query.getResultList();
    	for (Banco banco2 : results) {
			System.out.print(banco2.getIdbanco()+". ");
			System.out.println(banco2.getNombre());			
		}
		
	}
	
}
