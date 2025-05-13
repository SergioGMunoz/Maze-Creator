package controller;

import model.ConnectionDB;

public class Launcher {

	public static void main(String[] args) {
		
		//Conexión BBDD
		ConnectionDB connectionDB = new ConnectionDB("user", "pwd", "localhost", "3306", "DataBaseName");
		
		//Lanzar Primera View
		
		
	}

}
