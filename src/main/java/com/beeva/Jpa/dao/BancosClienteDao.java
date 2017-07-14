package com.beeva.Jpa.dao;

import com.beeva.Jpa.model.Bancoscliente;
import com.beeva.Jpa.model.Cliente;

public abstract class BancosClienteDao {

	public abstract void save (Bancoscliente bancocliente);
	public abstract Cliente getClientesById(int id);
	public abstract void getBancosClientesList(int idbanco);
}
