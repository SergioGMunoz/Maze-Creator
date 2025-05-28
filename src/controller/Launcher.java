package controller;

import model.ConnectionDB;
import view.PantallaInicio;


public class Launcher {

	public static void main(String[] args) {
		
		//Conexión BBDD si es correcta abre el programa
		if (ConnectionDB.connect("JavaLaberinto", "Java12345", "localhost", "3306", "Laberinto") != null) {
			//Lanzar Primera Pantalla de View
			PantallaInicio pi = new PantallaInicio();
			pi.setVisible(true);
		}
	}

} 