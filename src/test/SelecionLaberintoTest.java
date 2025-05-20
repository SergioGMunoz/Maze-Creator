package test;

import model.ConnectionDB;
import view.SeleccionLaberinto;

public class SelecionLaberintoTest {

	public static void main(String[] args) {
		ConnectionDB.connect("JavaLaberinto", "Java12345", "localhost", "3306", "Laberinto");
		SeleccionLaberinto cl = new SeleccionLaberinto();
		cl.setVisible(true);
	}

}
