package com.beeva.Jpa.mongo;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongoConn extends MongoClient{
	private String host;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	private int port;
	
	MongoConn() throws UnknownHostException{
		MongoClient mongo = new MongoClient(host,port);
	}
	
}
