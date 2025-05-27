package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GameController;
import model.ConnectionDB;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;


public class SeleccionLaberinto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboLaberintos;
	private JComboBox<String> comboDisposiciones;
	private HashMap<String, Integer> mazeNameToIdMap = new HashMap<>();

	// Por defecto sale el laberinto 1 disposición 1
	private int selectedMazeId = 1;
	private int selectedDispositionId = 1;
	
	private int carlosCrearDisposicion() {
	    int nuevaDisposicionId = -1;

	    String sqlInsert = "INSERT INTO Disposition DEFAULT VALUES";

	    try (Connection conn = ConnectionDB.getConnection();
	         Statement stmt = conn.createStatement()) {

	        int filasAfectadas = stmt.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

	        if (filasAfectadas == 0) {
	            throw new SQLException("No se pudo crear la nueva disposición.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                nuevaDisposicionId = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("No se pudo obtener el ID de la nueva disposición.");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al crear nueva disposición", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return nuevaDisposicionId;
	}

	
	// Método que lanza la ventana de partida aportando la id de laberinto y disposicion
	private void pressBtnJugar() {
	    try {
	        // Obtener nombre del laberinto y buscar su ID
	        String mazeNameSelected = (String) comboLaberintos.getSelectedItem();
	        selectedMazeId = mazeNameToIdMap.getOrDefault(mazeNameSelected, 1); // Default = 1

	        // Obtener ID disposición seleccionada
	        String dispoIdSelectedStr = (String) comboDisposiciones.getSelectedItem();

	        if ("Nueva Disposicion".equals(dispoIdSelectedStr)) {
	            // Aquí llamas al método para crear la nueva disposición y obtener su ID
	            selectedDispositionId = carlosCrearDisposicion();
	        } 
	        else 
	        	if (dispoIdSelectedStr != null && dispoIdSelectedStr.startsWith("Disposicion: ")) {
	            try {
	                selectedDispositionId = Integer.parseInt(dispoIdSelectedStr.substring("Disposicion: ".length()));
	            } catch (NumberFormatException e) {
	                selectedDispositionId = 1; // Valor por defecto
	            }
	        } 
	        	else {
	            selectedDispositionId = 1; // Valor por defecto
	        }

	        GameView gameView = new GameView(selectedMazeId, selectedDispositionId);
	        gameView.setVisible(true);
	        dispose();

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "No se puede iniciar el juego correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        e.printStackTrace();
	    }
	}



	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	        	SeleccionLaberinto frame = new SeleccionLaberinto();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public SeleccionLaberinto() {
	    setTitle("Selección Juego");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 700, 500);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitulo = new JLabel("SELECCIÓN DE JUEGO");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 600, 40);
	    contentPane.add(lblTitulo);

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(60, 70, 90));
	    panel.setBounds(150, 100, 400, 260);
	    panel.setLayout(null);
	    panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(panel);

	    JLabel lblLaberinto = new JLabel("Selecciona un laberinto:");
	    lblLaberinto.setForeground(Color.WHITE);
	    lblLaberinto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblLaberinto.setBounds(30, 30, 300, 20);
	    panel.add(lblLaberinto);

	    comboLaberintos = new JComboBox<>();
	    comboLaberintos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboLaberintos.setBounds(30, 55, 340, 25);
	    try {
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT ID_Maze, Name FROM Maze");

	        while (rs.next()) {
	            int id = rs.getInt("ID_Maze");
	            String nombre = rs.getString("Name");
	            comboLaberintos.addItem(nombre);
	            mazeNameToIdMap.put(nombre, id);
	        }

	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al cargar los laberintos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    panel.add(comboLaberintos);

	    JLabel lblDispo = new JLabel("Selecciona una disposición (opcional):");
	    lblDispo.setForeground(Color.WHITE);
	    lblDispo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblDispo.setBounds(30, 100, 300, 20);
	    panel.add(lblDispo);

	    comboDisposiciones = new JComboBox<>();
	    comboDisposiciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboDisposiciones.setBounds(30, 125, 340, 25);
	    try {
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        comboDisposiciones.addItem("Nueva Disposicion");

	     ResultSet rs = stmt.executeQuery("SELECT ID_Disposition FROM Disposition");
	     while (rs.next()) {
	         String dispoId = rs.getString("ID_Disposition");
	         comboDisposiciones.addItem("Disposicion: " + dispoId);
	     }

	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al cargar las disposiciones desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    panel.add(comboDisposiciones);

	    JButton btnJugar = new JButton("Jugar");
	    btnJugar.setBackground(new Color(30, 200, 150));
	    btnJugar.setForeground(Color.BLACK);
	    btnJugar.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnJugar.setBounds(115, 175, 160, 40);
	    panel.add(btnJugar);
	    
	    	    JButton btnVolver = new JButton("Volver");
	    	    btnVolver.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    		PantallaInicio inicio = new PantallaInicio();
	                    inicio.setVisible(true);
	                    dispose();	                
	                    
	    	    	}
	    	    });
	    	    btnVolver.setBounds(530, 401, 120, 30);
	    	    contentPane.add(btnVolver);
	    	    btnVolver.setBackground(Color.GRAY);
	    	    btnVolver.setForeground(Color.WHITE);
	    	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    	    
	    	    btnJugar.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	            pressBtnJugar();
	    	        }
	    	    });

	}
}