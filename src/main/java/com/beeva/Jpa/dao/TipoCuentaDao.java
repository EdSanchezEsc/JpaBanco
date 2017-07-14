package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Tipocuenta;



public abstract class TipoCuentaDao {
	public abstract void save (Tipocuenta tipo);
	public abstract Tipocuenta getTipoCuentaById(int id);
	public abstract void getListTipo();
	public abstract void loadTipos();
}
