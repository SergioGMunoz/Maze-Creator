package test;

import model.ConnectionDB;
import view.AñadirPreguntas;

public class AddQuestionTest {

	public static void main(String[] args) {
		ConnectionDB.connect("JavaLaberinto", "Java12345", "localhost", "3306", "Laberinto");
		AñadirPreguntas cl = new AñadirPreguntas();
		cl.setVisible(true);
	}

}
