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
	    
	    int [][] cells = new int[maze.numCol][maze.numRow];

	    while (rs.next()) {
	        int col = rs.getInt("Col_Maze");
	        int row = rs.getInt("Row_Maze");
	        String cellType = rs.getString("Cell_Type");
	        
	        //Codificar nombres a numeros
	        switch (cellType) {
	            case "Free" -> cells[col][row] = 0;
	            case "Block" -> cells[col][row] = 1;
	            case "Crocodile" -> cells[col][row] = 2;
	            case "Medkit" -> cells[col][row] = 3;
	            default -> cells[col][row] = -1;
	        }
        }

	    Disposition disposition = new Disposition(maze, cells);
	    return disposition;
	}

	
}

