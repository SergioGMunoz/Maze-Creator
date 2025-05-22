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
		showGameView();
	}
	
	
	
	public int getHealth() {
		return health;
	}

	private boolean [] getDirections() {

	    int maxCol = maze.getNumCol();
	    int maxRow = maze.getNumRow();
	    
	    int[][] cells = disposition.getCells();
	    
	    // Arriba -> -1 0
		// Derecha -> 0 1
		// Abajo -> 1 0
		// Izquierda -> 0 -1
	    
	    boolean[] moves = {
	    	    row > 0 && cells[row - 1][col] != 1,               // Arriba
	    	    row < maxRow - 1 && cells[row + 1][col] != 1,      // Abajo
	    	    col > 0 && cells[row][col - 1] != 1,               // Izquierda
	    	    col < maxCol - 1 && cells[row][col + 1] != 1       // Derecha
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
		QuizController quizController = new QuizController(this, false, maze.getDmgQuestions(),maze.getTimeQuestions());
	}
	
	// Lógica de moverse 
	public void move(int direction) {
	    // Direcciones: 0=Arriba, 1=Abajo, 2=Izquierda, 3=Derecha
		System.out.println("Direción: " + direction);
		System.out.println("Col " + col + " Row " + row);
	    switch (direction) {
	        case 0:
	        	row--;
	        	break;
	        case 1:
	        	row++; 
	        	break;
	        case 2:
	        	col--;   
	        	break;
	        case 3: 
	        	col++;   
	        	break;
	        default:
	        	System.out.println("Dirección no válida");
	        	break;
	    }
	    System.out.println("MOVIMIENTO:");
	    System.out.println("Salud->"+health);
		System.out.println("Col " + col + " Row " + row);
		System.out.println("Tipo: " + disposition.cells[row][col]);
		
	    
	    
	    
	    //Chechear victoria o derrota
	    if(!isEnd()) {
	    	showQuiz(false);
	    	//Actualizar btns para la vuelta
		    gameView.updateBtnMoves(getDirections());
	    }
	    
	    //Muestra el tipo de casilla
	    showTypeCell();
	    gameView.updateLbHealth(health);
	}
	
	// Metodo que muestra lo que hay en la casilla (nada, cocodirlo o kit medico)
	public void showTypeCell(){
		int type = disposition.cells[row][col];
		
		// Asignar nuevos puntos de vida
		if(type == 2) {
			System.out.println("Daño de cocodrilo -> " + maze.getDmgCrocodiles());
			//Cocodrilo
			health -= maze.getDmgCrocodiles();
			if (health < 0) health = 0;
			maze.setNumCrocodiles(maze.getNumCrocodiles() -1);
			gameView.updateLbCrocodiles(maze.getNumCrocodiles());
		}else if (type == 3) {
			//Medical Kit
			System.out.println("Cura MedKit -> " + maze.getHpMedKit());
			health += maze.getHpMedKit();
			if (health > 100) health = 100;
			maze.setNumMedKit(maze.getNumMedKit() -1);
			gameView.updateLbMedkits(maze.getNumMedKit());
		}
		//Mostrar tipo en view 
		gameView.updateCellType(type);
		//Eliminar typo de casilla
		disposition.cells[row][col] = 0;
		System.out.println("Salud actual -> " + health);
	}

	public void quizFail(JFrame quizFrame) {
		quizFrame.dispose(); 
		health -= maze.getDmgQuestions();
		System.out.println("Salud actual -> " + health);
		gameView.updateLbHealth(health);
		if (!isEnd()) {
			QuizController quizController = new QuizController(this, true,maze.getDmgQuestions(),maze.getTimeQuestions());
		}
	}

	public void quizWin(JFrame quizFrame) {
		quizFrame.dispose(); 
		gameView.setVisible(true);
	}
	
	private boolean isEnd() {
		// Metodo que chequea la derrota
		boolean fail = health <= 0;
		boolean win = (col == maze.getNumCol()-1) && (row == maze.getNumRow()-1) && !fail;
		
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
	
	public static void crearDisposicion(int idLaberinto, boolean[][] muros, int numCocodrilos, int numMedkits) {
	    try {
	        GameDAO dao = new GameDAO();
	        Maze maze = dao.getMazeById(idLaberinto);

	        int nuevoId = dao.obtenerUltimoIdDisposicion(idLaberinto) + 1;
	        int[][] cells = new int[maze.getNumRow()][maze.getNumCol()];

	        for (int row = 0; row < maze.getNumRow(); row++) {
	            for (int col = 0; col < maze.getNumCol(); col++) {
	                cells[row][col] = muros[row][col] ? 1 : 0;
	            }
	        }

	        // Proteger entrada y salida
	        cells[0][0] = 0;
	        cells[maze.getNumRow() - 1][maze.getNumCol() - 1] = 0;

	        Disposition disp = new Disposition(maze, cells);
	        disp.añadirCocodrilosYMedkits(numCocodrilos, numMedkits);

	        dao.insertarDisposicion(disp, nuevoId);

	        System.out.println("✅ Disposición creada con ID: " + nuevoId);
	    } catch (SQLException e) {
	        System.err.println("❌ Error al crear disposición: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}