package com.beeva.Jpa.mongo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.beeva.Jpa.model.Cliente;
import com.beeva.Jpa.model.Cuenta;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoUtils {

	public void insertRecord(ApplicationContext context,Object o){
		MongoClient mongo = (MongoClient) context.getBean("db");
		DB db = mongo.getDB("BancoLog");
		DBCollection table = db.getCollection("LogCollection");
		BasicDBObject document = new BasicDBObject();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String fecha = date.toString();
		if (o instanceof Cliente){
			document.put("Mensaje", "Se creo un cliente");
			document.put("Fecha", fecha);
			document.put("IdCliente", ((Cliente) o).getIdcliente());
			document.put("Nombre", ((Cliente) o).getNombre());
			document.put("Apellido", ((Cliente) o).getApellido());
			table.insert(document);
			mongo.close();
		}			
		else if(o instanceof Cuenta){
			document.put("Mensaje", "Se creo una cuenta");
			document.put("Fecha", fecha);
			document.put("IdCuenta", ((Cuenta) o).getCliente().getIdcliente());
			document.put("IdCliente", ((Cuenta) o).getCliente().getIdcliente());
			document.put("IdTipoCuenta", ((Cuenta) o).getTipocuenta().getIdtipocuenta());
			document.put("Balance", ((Cuenta) o).getBalance());
			table.insert(document);
			mongo.close();
		}
	}
}
