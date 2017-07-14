package com.beeva.Jpa;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.Jpa.dao.BancoDao;
import com.beeva.Jpa.dao.BancosClienteDao;
import com.beeva.Jpa.dao.ClienteDao;
import com.beeva.Jpa.dao.CuentaDao;
import com.beeva.Jpa.dao.TipoCuentaDao;
import com.beeva.Jpa.impl.BancoDaoImpl;
import com.beeva.Jpa.impl.BancosClienteDaoImpl;
import com.beeva.Jpa.impl.ClienteDaoImpl;
import com.beeva.Jpa.impl.CuentaDaoImpl;
import com.beeva.Jpa.impl.TipoCuentaDaoiImpl;
import com.beeva.Jpa.model.Banco;
import com.beeva.Jpa.model.Bancoscliente;
import com.beeva.Jpa.model.Cliente;
import com.beeva.Jpa.model.Cuenta;
import com.beeva.Jpa.model.Tipocuenta;
import com.beeva.Jpa.factory.CuentaFactory;


public class App 
{

	public static void main( String[] args )
    {   
    	ApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml");
	    ClienteDao userdao = (ClienteDao)context.getBean(ClienteDaoImpl.class);
    	BancoDao bancodao = (BancoDao)context.getBean(BancoDaoImpl.class);
    	CuentaDao cuentadao = (CuentaDao)context.getBean(CuentaDaoImpl.class);
    	TipoCuentaDao tipocuentadao = (TipoCuentaDao)context.getBean(TipoCuentaDaoiImpl.class);
    	BancosClienteDao bancosclientedao = (BancosClienteDao)context.getBean(BancosClienteDaoImpl.class);
    	CuentaFactory factory = (CuentaFactory)context.getBean("cuentaFactory");
    	Tipocuenta tipocuenta;
    	//Comentar las dos siguientes lineas si ya se corrio al menos una vez.
    	bancodao.loadBanco();
    	tipocuentadao.loadTipos();
    	//
    	Scanner sc = new Scanner(System.in);
    	int banc, id, idtipo, opr, op=0;
    	do{
    		System.out.println("¿Que desea realizar?");
    		System.out.println("1.- Alta");
    		System.out.println("2.- Consulta");
    		System.out.println("3.- Operaciones");
    		System.out.println("4.- Salir");
    		op = sc.nextInt();
    		switch (op) {
			case 1:
				System.out.println("¿Que tipo de alta?");
	    		System.out.println("1.- Banco");
	    		System.out.println("2.- Cliente");
	    		System.out.println("3.- Cuenta");
	    		System.out.println("4.- Regresar");
	    		int op2 = sc.nextInt();
	    		switch (op2){
	    		case 1:
	    			Banco banco = new Banco();
	    			System.out.println("¿Como se llama el banco? (Sin espacios)");
	    			String bank = sc.next();
	    			if (bank.matches("[a-zA-Z]+$")) {
	    				banco.setNombre(bank);
		    	    	bancodao.save(banco);
		    	    	System.out.println("Se agrego exitosamente el banco");
		    			break;
	    			}
	    			else{
	    				System.out.println("No se permiten numeros");
	    				break;
	    			}
	    				
	    	    	
	    		case 2:
	    			Cliente cliente=new Cliente();
	    			System.out.println("¿Cual es el nombre del cliente?");
	    			String name = sc.next();
	    			System.out.println("¿Cual es el apellido del cliente?");
	    			String apellido = sc.next();
	    			if (name.matches("[a-zA-Z]+$") && apellido.matches("[a-zA-Z]+$")) {
	    				cliente.setApellido(apellido);
		    	    	cliente.setNombre(name);
		    	    	cliente = userdao.save(cliente);
		    	    	System.out.println("¿Desea agregar cuenta por defecto? Y/N (Balance=0 Tipo=Ahorro)");
		    	    	String j = sc.next();
		    	    	if(j.equals("Y") || j.equals("y")){
		    	    		Cuenta cuenta = new Cuenta();
		    	    		tipocuenta = tipocuentadao.getTipoCuentaById(1);
		    	        	cuenta.setBalance(0.00);
		    	        	cuenta.setTipocuenta(tipocuenta);
		    	        	cuenta.setCliente(cliente);
		    	        	cuentadao.save(cuenta);
		    	    	}
		    	    	try{
		    	    		System.out.println("¿De que banco es?");
			    	    	bancodao.getListBanco();
			    	    	int bc;
			    	    	bc= sc.nextInt();
			    	    	Bancoscliente bancoscliente = new Bancoscliente();
			    	    	bancoscliente.setBanco(bancodao.getBancoById(bc));
			    	    	bancoscliente.setCliente(cliente);
			    	    	bancosclientedao.save(bancoscliente);
			    	    	System.out.println("Se agrego exitosamente el cliente");
			    			break;
		    	    	}catch(NullPointerException e){
		    	    		System.out.println("Dato invalido, intenta de nuevo.");
		    	    		break;
		    	    	}
	    			}
	    	    	
	    		case 3:
	    			try{
	    				Cuenta cuenta = new Cuenta();
		    			userdao.getListCliente();
		    			cliente = userdao.getUserById(sc.nextInt());
		    			tipocuentadao.getListTipo();
		    			tipocuenta = tipocuentadao.getTipoCuentaById(sc.nextInt());
		    			System.out.println("¿Con que saldo inicial?");
		    			String s = sc.next();
		    			if(s.matches("\\d+")){
		    				cuenta.setBalance(Double.parseDouble(s));
			    			cuenta.setCliente(cliente);
			    			cuenta.setTipocuenta(tipocuenta);
			    			cuentadao.save(cuenta);
			    			break;
		    			}
		    			else{
		    				System.out.println("No se permiten letras");
		    				break;
		    			}
	    			}catch(NullPointerException e)
	    			{
	    				System.out.println("Datos invalidos");
	    				break;
	    			}
	    		case 4:
	    			break;
	    		default:
	    			System.out.println("Opcion invalida");
	    			break;
	    		}
	    		
				break;
			case 2:
				Cuenta cuenta = new Cuenta();
				Cliente cliente=new Cliente();
				System.out.println("¿De que banco es tu cliente?");
				bancodao.getListBanco();
				banc = sc.nextInt();
				bancosclientedao.getBancosClientesList(banc);
				id = sc.nextInt();
				cliente = userdao.getUserById(id);
				cuenta = cuentadao.getCuentaByUserId(id);
				cuentadao.getListCuentaByClienteId(id);
				idtipo = sc.nextInt();
				
				try{
					if(idtipo ==1 || idtipo==2){
						System.out.println("Cliente: "+cliente.getNombre() + " "+ cliente.getApellido());
						System.out.println("Cuenta: ");
						System.out.println(tipocuentadao.getTipoCuentaById(idtipo).getNombre());
						System.out.println("Balance: ");
						System.out.println(cuenta.getBalance());
						break;		
					
					}else{
						System.out.println("Datos invalidos, Intenta de nuevo");
						break;
					}
						
				}catch(NullPointerException e){
					System.out.println("Datos invalidos, intenta de nuevo.");
					break;
				}				
			case 3:
				System.out.println("¿De que banco es tu cliente?");
				bancodao.getListBanco();
				banc = sc.nextInt();
				bancosclientedao.getBancosClientesList(banc);
				id = sc.nextInt();
				cliente = userdao.getUserById(id);
				cuenta = cuentadao.getCuentaByUserId(id);
				cuentadao.getListCuentaByClienteId(id);
				idtipo = sc.nextInt();
				System.out.println("¿Que operacion quieres realizar?");
				System.out.println("1. Deposito");
				System.out.println("2. Retiro");
				opr = sc.nextInt();
				System.out.println("¿Que cantidad?");
				
				double total;
				double cant = sc.nextDouble();
				if (cant <=0 ){
					System.out.println("Monto invalido");
					break;
				}
				if (opr==1 && (idtipo==2 ||idtipo==1)){
					try{
						total = factory.getImplement(cuenta).deposito(cuenta, cant);
						cuentadao.UpdateDeposito(cuenta, total);
						break;
					}catch(NullPointerException e){
						System.out.println("Cliente invalido");
						break;
					}
				}else if (opr==2 && (idtipo==2 ||idtipo==1)){
					try{
						total = factory.getImplement(cuenta).retiro(cuenta, cant);
						cuentadao.UpdateRetiro(cuenta, total);
						break;
					}catch(NullPointerException e){
						System.out.println("Cliente invalido");
						break;
					}
				}else{
					System.out.println("Opcion invalida.");
					break;
				}
				
			case 4:
				break;
			default:
				System.out.println("Opcion invalida\n");
			}
    		
    	}while(op!=4);
    	System.out.println("Gracias");
    }
    
}
