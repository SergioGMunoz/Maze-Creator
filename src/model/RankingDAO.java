package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankingDAO {
	
	 private Connection conn;
	    
    public RankingDAO() throws SQLException {
        this.conn = ConnectionDB.getConnection();
    }
    
	// Metodo que inserta una entrada de ranking devuelve si ha sido correcto
	 public boolean insertRanking(Object[] rankingData) {
	     String sql = "INSERT INTO Ranking (ID_Maze, ID_Disposition, User, Win, Hp) VALUES (?, ?, ?, ?, ?)";
	
	     try (PreparedStatement st = conn.prepareStatement(sql)) {
	    	 st.setInt(1, (int) rankingData[0]);                     
	         st.setInt(2, (int) rankingData[1]);                     
	         st.setString(3, ((String) rankingData[2])); 
	         st.setBoolean(4, (boolean) rankingData[3]);             
	         st.setInt(5, (int) rankingData[4]);                    
	
	         int rows = st.executeUpdate();
	         return rows >= 1;
	
	     } catch (SQLException e) {
	         System.err.println("❌ Error insert SQL");
	         System.err.println("❌ Error SQL al insertar ranking: " + e.getMessage());
	         return false;
	     }
	 }
	 
	// Metodo que devuelve el listado de ranking de un laberinto
	 public ArrayList<Object[]> getRankingsByMazeId(int idMaze) {
		 
		    ArrayList<Object[]> data = new ArrayList<>();
		    
		    String sql = "SELECT User, ID_Disposition, Hp, Win\r\n"
		    		+ "		        FROM Ranking\r\n"
		    		+ "		        WHERE ID_Maze = ?\r\n"
		    		+ "		        ORDER BY Win DESC, Hp DESC";

		    try  {
		    	PreparedStatement st = conn.prepareStatement(sql);
		        st.setInt(1, idMaze);

		        try (ResultSet rs = st.executeQuery()) {
		            while (rs.next()) {
		                Object[] fila = new Object[4];
		                fila[0] = rs.getString("User");           
		                fila[1] = rs.getInt("ID_Disposition");   
		                fila[2] = rs.getInt("Hp");                
		                fila[3] = rs.getBoolean("Win");           
		                data.add(fila);
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("❌ Error SQL al obtener ranking resumen: " + e.getMessage());
		    }

		    return data;
		}

}
