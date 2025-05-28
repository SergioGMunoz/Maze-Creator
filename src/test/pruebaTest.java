package test;

import model.ConnectionDB;
import view.PantallaInicio;
import view.SeleccionLaberinto;

public class pruebaTest {

	public static void main(String[] args) {
		
		//Conexión BBDD si es correcta abre el programa
				if (ConnectionDB.connect("JavaLaberinto", "Java12345", "localhost", "3306", "Laberinto") != null) {
					//Lanzar Primera Pantalla de View
					SeleccionLaberinto sl = new SeleccionLaberinto();
					sl.setVisible(true);
				}
				
				
	}

}
