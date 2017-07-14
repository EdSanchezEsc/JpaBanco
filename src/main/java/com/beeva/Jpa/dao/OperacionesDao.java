package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Cuenta;

public interface OperacionesDao {
	
	public double deposito(Cuenta cuenta , double n);
	public double retiro(Cuenta cuenta , double n);
}
