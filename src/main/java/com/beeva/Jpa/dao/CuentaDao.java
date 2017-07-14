package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Cuenta;

public abstract class CuentaDao {
	
	public abstract void save (Cuenta cuenta);
	public abstract Cuenta getCuentaById(int id);
	public abstract void getListCuentaByClienteId(int id);
	public abstract Cuenta getCuentaByUserId(int id);
	public abstract void UpdateDeposito (Cuenta cuenta, double d);
	public abstract void UpdateRetiro (Cuenta cuenta, double d);
	
}
