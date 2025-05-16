package controller;

import java.sql.SQLException;

import model.Disposition;
import model.GameDAO;
import model.Maze;
import view.GameView;

public class GameController {
	// Clase que gestiona las partidas al completo, instanciar con un ID_Maze y ID_Disposition 
	
	GameDAO gameDAO = new GameDAO();
	GameView gameView = new GameView();
	
	Maze maze;
	Disposition disposition;
	int health = 100;
	int col = 0;
	int row = 0;
	
	
	public GameController(int mazeId, int dispositionId) throws SQLException {
		// Recibe la ID del laberinto y disposición y la instancia
		this.maze = gameDAO.getMazeById(mazeId);
		this.disposition = gameDAO.getDispositionById(maze, dispositionId);
	}
	
	
	private boolean[] getDirections() {

	    int maxCol = maze.getNumCol();
	    int maxRow = maze.getNumRow();
	    
	    int[][] cells = disposition.getCells();
	    
	    // Arriba -> -1 0
		// Derecha -> 0 1
		// Abajo -> 1 0
		// Izquierda -> 0 -1
	    
	    boolean [] moves = {
	    		row > 0 && cells[col][row - 1] != 1, // Arriba fila -1 
	    		row < maxRow - 1 && cells[col][row + 1] != 1, // Abajo fila +1 
	    		col > 0 && cells[col - 1][row] != 1, // Izquierda columna -1
	    		col < maxCol - 1 && cells[col + 1][row] != 1 // Derecha col +1
	    	};
	    return moves;
	}

	//Set visible the game view to play
	void showGameView () {
		gameView.updateLbHealth(health);
		gameView.updateLbCrocodiles(maze.getNumCrocodiles());
		gameView.updateLbMedkits(maze.getNumMedKit());
		gameView.updateBtnMoves(getDirections());
		gameView.setVisible(true);
		
	}
	
	
}
