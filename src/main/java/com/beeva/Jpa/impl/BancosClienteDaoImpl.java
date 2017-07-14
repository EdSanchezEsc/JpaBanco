package com.beeva.Jpa.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.Jpa.dao.BancosClienteDao;
import com.beeva.Jpa.model.Bancoscliente;
import com.beeva.Jpa.model.Banco;
import com.beeva.Jpa.model.Cliente;
@Repository
public class BancosClienteDaoImpl extends BancosClienteDao{

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Bancoscliente bancocliente) {
		bancocliente.getBanco().getIdbanco();
		em.persist(bancocliente);
	}

	@Override
	@Transactional
	public Cliente getClientesById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void getBancosClientesList(int idbanco) {
		TypedQuery<Bancoscliente> query = em.createNamedQuery("Bancoscliente.findClienteByBanco", Bancoscliente.class);
		query.setParameter("idbanco", idbanco);
    	List<Bancoscliente> results = query.getResultList();
    	System.out.println("¿De que cliente quiere saber?");
    	for (Bancoscliente bancoscliente2 : results) {
    		System.out.print(bancoscliente2.getCliente().getIdcliente()+". ");
    		System.out.print(bancoscliente2.getCliente().getNombre()+" ");
    		System.out.println(bancoscliente2.getCliente().getApellido());
    	}
		
	}

}
