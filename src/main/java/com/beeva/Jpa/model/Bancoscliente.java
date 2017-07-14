package com.beeva.Jpa.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the bancosclientes database table.
 * 
 */
@Entity
@Table(name="bancosclientes")
@NamedQueries({
	@NamedQuery(name="Bancoscliente.findAll", query="SELECT b FROM Bancoscliente b"),
	@NamedQuery(name="Bancoscliente.findClienteByBanco", query="SELECT b FROM Bancoscliente b WHERE b.banco.idbanco = :idbanco")
})
public class Bancoscliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idbancosclientes;

	//bi-directional many-to-one association to Banco
	@ManyToOne
	@JoinColumn(name="idbanco")
	private Banco banco;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	public Bancoscliente() {
	}

	public int getIdbancosclientes() {
		return this.idbancosclientes;
	}

	public void setIdbancosclientes(int idbancosclientes) {
		this.idbancosclientes = idbancosclientes;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}