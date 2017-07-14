package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Banco;

public abstract class BancoDao {
	
	public abstract void save (Banco banco);
	public abstract void loadBanco();
	public abstract Banco getBancoById(int id);
	public abstract void getListBanco();
}
