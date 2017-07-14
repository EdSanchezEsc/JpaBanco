/**Clase que define que tipo de cuenta es.
 * Eduardo Sanchez 05-07-2017
 */

package com.beeva.Jpa.factory;

import com.beeva.Jpa.dao.OperacionesDao;
import com.beeva.Jpa.impl.CuentaAhorro;
import com.beeva.Jpa.impl.CuentaCheques;
import com.beeva.Jpa.model.Cuenta;

public class CuentaFactory {
	
/**
 * Regresa una CuentaDAO permitiendo hacer operaciones Deposito y Retiro.
 * @param cuenta
 * @return CuentaDAO
 */
	public OperacionesDao getImplement(Cuenta cuenta)
	{
		int tipo = cuenta.getTipocuenta().getIdtipocuenta();		
		if(tipo == 1){
			
			OperacionesDao cuentaAhorro = new CuentaAhorro();
			return cuentaAhorro;
		}	
		else{
			OperacionesDao cuentaCheque = new CuentaCheques();
			return cuentaCheque;
		}
			
	}
}
