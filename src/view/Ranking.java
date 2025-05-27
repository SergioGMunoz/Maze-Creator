package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.RankingDAO;

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaRanking;
	private JButton btnVolverAJugar;
	private int idMaze;
	private RankingDAO rankingDAO;
	private DefaultTableModel model;
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            Ranking frame = new Ranking(0);
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	public Ranking(int idMaze) {
		this.idMaze = idMaze; 
		
		 this.model = new DefaultTableModel(
			        new Object[][] {
			        },
			        new String[] {"Usuario", "Disposición", "Vida", "Victoria"}
			    );
		
		try {
			this.rankingDAO  = new RankingDAO();
		} catch (SQLException e) {
			System.err.println("Error al crear objeto RankingDAO");
			e.printStackTrace();
		}
		
		// Actualizar datos del ranking
		renderRanking(rankingDAO.getRankingsByMazeId(idMaze));
		init();
	}
	
	private void renderRanking(ArrayList <Object[]> data) {
		    model.setRowCount(0); // Limpiar modelo

		    for (Object[] fila : data) {
		    	
		    	String victoria = "";
		    	if((boolean) fila[3]) {
		    		victoria="Si";
		    	}else {
		    		victoria="No";
		    	}
		    
		        model.addRow(new Object[] {
		            fila[0],      
		            fila[1],     
		            fila[2],      
		            victoria     
		        });
		    }
	}

	public void init() {
	    setTitle("Ranking");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 750, 550);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitulo = new JLabel("RANKING DE JUGADORES");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 650, 40);
	    contentPane.add(lblTitulo);


	    // Tabla de ranking
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(60, 130, 620, 300);
	    contentPane.add(scrollPane);

	    tablaRanking = new JTable();
	    tablaRanking.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    tablaRanking.setRowHeight(25);
	    tablaRanking.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
	    tablaRanking.setModel(model);

	    // Botón volver
	    JButton btnVolver = new JButton("Ir al inicio");
	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnVolver.setBackground(new Color(30, 200, 150));
	    btnVolver.setBounds(430, 450, 150, 40);
	    contentPane.add(btnVolver);
	    
	    btnVolverAJugar = new JButton("Volver a jugar");
	    btnVolverAJugar.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnVolverAJugar.setBackground(new Color(30, 200, 150));
	    btnVolverAJugar.setBounds(136, 450, 150, 40);
	    contentPane.add(btnVolverAJugar);
	}

}