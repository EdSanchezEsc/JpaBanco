package com.beeva.Jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.Jpa.dao.CuentaDao;
import com.beeva.Jpa.model.Banco;
import com.beeva.Jpa.model.Bancoscliente;
import com.beeva.Jpa.model.Cuenta;
import com.beeva.Jpa.model.CuentaADepositar;
import com.beeva.Jpa.mongo.MongoUtils;
import com.mongodb.MongoClient;

@Repository
public class CuentaDaoImpl extends CuentaDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void save(Cuenta cuenta) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml");
		MongoUtils mongo = (MongoUtils) context.getBean("utils");
		mongo.insertRecord(context, cuenta);
		em.persist(cuenta);
	}

	@Transactional
	public Cuenta getCuentaById(int id) {
		return em.find(Cuenta.class, id);
	}

	@Transactional
	@Override
	public void getListCuentaByClienteId(int id) {
		TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findCuentaByUser", Cuenta.class);
		query.setParameter("idcliente", id);
    	List<Cuenta> results = query.getResultList();
    	System.out.println("¿De que cuenta?");
    	for (Cuenta cuenta : results) {
    		System.out.print(cuenta.getTipocuenta().getIdtipocuenta()+". ");
    		System.out.println(cuenta.getTipocuenta().getNombre());
    	}
	}

	@Transactional
	@Override
	public Cuenta getCuentaByUserId(int id) {
		TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findCuentaByUser", Cuenta.class);
		query.setParameter("idcliente", id);
    	List<Cuenta> results = query.getResultList();
    	for (Cuenta cuenta : results) {
    		return cuenta;
    	}
    	return null;
	}

	@Transactional
	public void UpdateDeposito(Cuenta cuenta, double saldo) {
		
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rule");
        FactHandle factl;
        
        
        CuentaADepositar cuenta2 = new CuentaADepositar();
        cuenta2.setBalance(cuenta.getBalance());
        cuenta2.setDeposito(saldo);
        cuenta2.setTipocuenta(cuenta.getTipocuenta());
        
        factl = kSession.insert(cuenta2);
        kSession.fireAllRules();
		Cuenta c = em.find(Cuenta.class, cuenta.getIdcuenta());
		c.setBalance(saldo);
		em.flush();
	}
	
	@Transactional
	public void UpdateRetiro(Cuenta cuenta, double saldo){
		Cuenta c = em.find(Cuenta.class, cuenta.getIdcuenta());
		c.setBalance(saldo);
		em.flush();
	}

}
