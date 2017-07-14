package com.beeva.Jpa.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c"),
	@NamedQuery(name="Cuenta.findCuentaByUser", query="SELECT c FROM Cuenta c WHERE c.cliente.idcliente = :idcliente")
})
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcuenta;

	private double balance;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Tipocuenta
	@ManyToOne
	@JoinColumn(name="idtipocuenta")
	private Tipocuenta tipocuenta;

	public Cuenta() {
	}

	public int getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tipocuenta getTipocuenta() {
		return this.tipocuenta;
	}

	public void setTipocuenta(Tipocuenta tipocuenta) {
		this.tipocuenta = tipocuenta;
	}


}