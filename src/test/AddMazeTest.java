package test;

import model.ConnectionDB;
import view.CrearLaberinto;

public class AddMazeTest {

public static void main(String[] args) {
	ConnectionDB.connect("JavaLaberinto", "Java12345", "localhost", "3306", "Laberinto");
	CrearLaberinto cl = new CrearLaberinto();
	cl.setVisible(true);
    }

}
