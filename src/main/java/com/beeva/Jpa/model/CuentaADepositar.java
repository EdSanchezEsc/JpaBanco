package com.beeva.Jpa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CuentaADepositar {
	private int idcuenta;
	private double balance;
	private Cliente cliente;
	private Tipocuenta tipocuenta;
	private double deposito;

	public CuentaADepositar() {
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
	
	public double getDeposito() {
		return deposito;
	}

	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}
	
	public void displayMensaje(String d){
		
		if(d.equals("media")){
			System.out.println("Deposito sospecha media");
		}else if(d.equals("nada")){
			System.out.println("Deposito sin sospecha");
		}else if(d.equals("alta")){
			System.out.println("Deposito sospecha alta");
		}else if(d.equals("baja")){
			System.out.println("Deposito sospecha baja");
		}
		else{
			System.out.println("Deposito  listo");
		}
		
	}
}
