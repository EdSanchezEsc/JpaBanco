package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Cliente;

public abstract class ClienteDao {
	
	public abstract Cliente save (Cliente cliente);
	public abstract Cliente getUserById(int id);
	public abstract void getListCliente();

}
