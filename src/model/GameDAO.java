package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {
	
	// Clase que gestiona la BBDD para las partidas (game)
	Connection connection= ConnectionDB.getConnection();
	
	public Maze getMazeById(int id) throws SQLException {
		Maze maze = null;
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Maze WHERE id_maze=?;");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			maze = new Maze(
			        rs.getInt("ID_Maze"),
			        rs.getString("Name"),
			        rs.getInt("Num_Col"),
			        rs.getInt("Num_Row"),
			        rs.getInt("NumCrocodiles"),
			        rs.getInt("NumMedKit"),
			        rs.getInt("DmgCrocodiles"),
			        rs.getInt("HpMedKit"),
			        rs.getInt("TimeQuestions"),
			        rs.getInt("DmgQuestions"),
			        rs.getBoolean("EnableHelp")
			    );
		}
		return maze;
	}
	
	public Disposition getDispositionById (Maze maze, int dispositionId) throws SQLException {
	    if (maze == null) return null;

	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM Disposition "
	    		+ "WHERE ID_Maze = ? AND ID_Disposition = ?");
	    
	    ps.setInt(1, maze.id);
	    ps.setInt(2, dispositionId);
	    ResultSet rs = ps.executeQuery();
	    
	    int [][] cells = new int[maze.numRow][maze.numCol];
	    while (rs.next()) {
	        int col = rs.getInt("Col_Maze");
	        int row = rs.getInt("Row_Maze");
	        String cellType = rs.getString("Cell_Type");
	        
	        //Codificar nombres a numeros
	        switch (cellType) {
	            case "Free":
	            	cells[row][col] = 0;
	            	break;
	            case "Block":
	            	cells[row][col] = 1;
	            	break;
	            case "Crocodile":
	            	cells[row][col] = 2;
	            	break;
	            case "Medkit":
	            	cells[row][col] = 3;
	            	break;
	            default:
	            	cells[row][col] = -1;
	            	break;
	        }
	        
        }

	    Disposition disposition = new Disposition(maze, cells);
	    System.out.println("📦 Contenido de la disposición:");
	    for (int row = 0; row < cells[0].length; row++) {
	        for (int col = 0; col < cells.length; col++) {
	            System.out.print(cells[row][col] + " ");
	        }
	        System.out.println(); // Salto de línea al final de cada fila
	    }
	    return disposition;
	}

	public void importQuestions() {
	    try {	    	
	    	String sql = "SELECT * FROM Questions";
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	//Instanciar todas las preguntas para luego evitar repetidos
	    	while (rs.next()) {
	    		Question q = new Question(
	    				rs.getInt("ID_Questions"),
	    				rs.getString("Question"),
	    				rs.getString("AnswerCorrect"),
	    				rs.getString("Answer2"),
	    				rs.getString("Answer3"),
	    				rs.getString("Answer4")
	    				);
	    	}
	    	
	    }catch (SQLException e) {
	    	System.err.println("❌ Error de SQL al importar preguntas");
	    	e.printStackTrace();
	    }
	}

}

