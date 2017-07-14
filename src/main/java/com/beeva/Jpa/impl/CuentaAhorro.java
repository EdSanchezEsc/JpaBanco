package com.beeva.Jpa.impl;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.beeva.Jpa.dao.OperacionesDao;
import com.beeva.Jpa.model.Cuenta;

public class CuentaAhorro implements OperacionesDao{

	public double deposito(Cuenta cuenta, double n) {
		System.out.println("Se intentara deposito.");
		boolean result = false;
		double balance = cuenta.getBalance();
		cuenta.setBalance(balance + n);
		System.out.println("Se efectuo un deposito de :"+n);
		
		/*try{
    		KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rule");
            
            Product  = new Product();
            product.setType("diamond");
            
            FactHandle factl;
            
            factl = kSession.insert(product);
            kSession.fireAllRules();
            System.out.println("The discount for the jewellery product "+
            		product.getType()+" is " + product.getDiscount());
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
		*/
		
		return cuenta.getBalance();
	}

	public double retiro(Cuenta cuenta, double n) {
		System.out.println("Se intentara retiro.");
		double balance = cuenta.getBalance();
		if (balance>5000){
			cuenta.setBalance(balance - n);
			System.out.println("Se efectuo un retiro de :"+n);
			return cuenta.getBalance();
		}else{
			System.out.println("No tiene un saldo minimo de 5000");
			return cuenta.getBalance();
		}
	}

}
