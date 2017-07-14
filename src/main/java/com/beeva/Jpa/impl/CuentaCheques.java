package com.beeva.Jpa.impl;

import java.util.Calendar;

import com.beeva.Jpa.dao.OperacionesDao;
import com.beeva.Jpa.model.Cuenta;

public class CuentaCheques implements OperacionesDao {

	public double deposito(Cuenta cuenta, double n) {
		double balance = cuenta.getBalance();
		cuenta.setBalance(balance + n);
		System.out.println("Se efectuo un deposito de :"+n);
		return cuenta.getBalance();
	}

	public double retiro(Cuenta cuenta, double n) {
		double balance = cuenta.getBalance();
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7)
		{
			System.out.println("No se puede retirar en sabado ni domingo");
			return cuenta.getBalance();
		}else{
			cuenta.setBalance(balance - n);
			System.out.println("Se efectuo un retiro de :"+n);
			return cuenta.getBalance();
		}
	}

}
