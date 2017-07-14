package com.beeva.Jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.Jpa.dao.TipoCuentaDao;
import com.beeva.Jpa.model.Banco;
import com.beeva.Jpa.model.Cliente;
import com.beeva.Jpa.model.Cuenta;
import com.beeva.Jpa.model.Tipocuenta;

@Repository
public class TipoCuentaDaoiImpl extends TipoCuentaDao {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void save(Tipocuenta tipo) {
		em.persist(tipo);
	}
	
	@Transactional
	public void loadTipos() {
			Tipocuenta cuenta0 = new Tipocuenta("Ahorro");
			Tipocuenta cuenta1 = new Tipocuenta("Cheques");
			em.persist(cuenta0);
			em.persist(cuenta1);
	}
	
	@Override
	@Transactional
	public Tipocuenta getTipoCuentaById(int id) {
		return em.find(Tipocuenta.class, id);
	}

	@Transactional
	@Override
	public void getListTipo() {
		System.out.println("¿Que tipo de cuenta es?");
		TypedQuery<Tipocuenta> query = em.createNamedQuery("Tipocuenta.findAll", Tipocuenta.class);
    	List<Tipocuenta> results = query.getResultList();
    	for (Tipocuenta tipo : results) {
			System.out.print(tipo.getIdtipocuenta()+". ");
			System.out.println(tipo.getNombre()+" ");
		}
	}

}
