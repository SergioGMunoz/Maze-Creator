package controller;

import java.sql.SQLException;

import javax.swing.JFrame;

import model.Disposition;
import model.GameDAO;
import model.Maze;
import view.GameView;

public class GameController {
	
	// Clase que gestiona las partidas al completo, instanciar con un ID_Maze y ID_Disposition 
	// Gestiona tambien la funcionalidad del quiz
	
	GameDAO gameDAO = new GameDAO();
	GameView gameView = new GameView(this);
	
	
	Maze maze;
	Disposition disposition;
	int health = 100;
	int col = 0;
	int row = 0;
	
	
	public GameController(int mazeId, int dispositionId) throws SQLException {
		
		// Recibe ID e instancia el laberinto y disposición 
		this.maze = gameDAO.getMazeById(mazeId);
		this.disposition = gameDAO.getDispositionById(maze, dispositionId);
		
		// Instanciar todas las preguntas
		gameDAO.importQuestions();
		
		//Mostrar ventana
		showGameView ();
	}
	
	
	
	public int getHealth() {
		return health;
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
	private void showGameView () {
		gameView.updateLbHealth(health);
		gameView.updateLbCrocodiles(maze.getNumCrocodiles());
		gameView.updateLbMedkits(maze.getNumMedKit());
		gameView.updateBtnMoves(getDirections());
		gameView.setVisible(true);
		
	}
	
	// Oculta game y muestra quiz con una pregunta
	private void showQuiz(boolean failed) {
		gameView.setVisible(false);
		QuizController quizController = new QuizController(this, false, maze.getDmgQuestions());
	}
	
	// Lógica de moverse 
	public void move(int direction) {
	    // Direcciones: 1=Arriba, 2=Abajo, 3=Izquierda, 4=Derecha
	    switch (direction) {
	        case 1:
	        	row--;
	        	break;
	        case 2:
	        	row++; 
	        	break;
	        case 3:
	        	col--;   
	        	break;
	        case 4: 
	        	col++;   
	        	break;
	        default:
	        	System.out.println("Dirección no válida");
	        	break;
	    }
	    //Muestra el tipo de casilla
	    showTypeCell();
	    
	    //Chechear victoria o derrota
	    if(!isEnd()) {
	    	showQuiz(false);
	    	//Actualizar btns para la vuelta
		    gameView.updateBtnMoves(getDirections());
	    }
	}
	
	// Metodo que muestra lo que hay en la casilla (nada, cocodirlo o kit medico)
	public void showTypeCell(){
		int type = disposition.cells[row][col];
		
		// Asignar nuevos puntos de vida
		if(type == 2) {
			//Cocodrilo
			health -= maze.getDmgCrocodiles();
			if (health < 0) health = 0;
			maze.setNumCrocodiles(maze.getNumCrocodiles() -1);
			gameView.updateLbCrocodiles(maze.getNumCrocodiles());
		}else if (type == 3) {
			//Medical Kit
			health += maze.getHpMedKit();
			if (health > 100) health = 100;
			maze.setNumMedKit(maze.getNumMedKit() -1);
			gameView.updateLbMedkits(maze.getNumMedKit());
		}
		//Mostrar tipo en view 
		gameView.updateCellType(type);
		//Eliminar typo de casilla
		disposition.cells[row][col] = 1;
	}

	public void quizFail() {
		health -= maze.getDmgQuestions();
		if (!isEnd()) {
			QuizController quizController = new QuizController(this, true,maze.getDmgQuestions());
		}
	}

	public void quizWin(JFrame quizFrame) {
		quizFrame.dispose(); 
		gameView.setVisible(true);
	}
	
	private boolean isEnd() {
		// Metodo que chequea la derrota
		boolean fail = health <= 0;
		boolean win = (col == maze.getNumCol()-1) && (row == maze.getNumRow()) && !fail;
		
		if (fail || win) {
			goRanking(win);
			return true;
		}
		return false;
	}
	
	private void goRanking(boolean win) {
		System.out.println("Se acabo la partida, ganaste -> " + win);
		gameView.dispose();
		//Logica que instancia una pantalla Ranking
	}
	
}
