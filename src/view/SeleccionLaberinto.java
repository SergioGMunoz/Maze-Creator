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

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class SeleccionLaberinto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboLaberintos;
	private JComboBox<String> comboDisposiciones;
	// Por defecto sale el laberinto 1 disposición 1
	private int selectedMazeId = 1;
	private int selectedDispositionId = 1;
	
	// Método que lanza la ventana de partida aportando la id de laberinto y disposicion
	private void pressBtnJugar() {
		try {
			GameController gc = new GameController(selectedMazeId, selectedDispositionId);
			System.out.println("Jugando a ... " +selectedMazeId +selectedDispositionId);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "El laberinto no se puede cargar correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
			System.err.println("❌ Error SQL con al jugar laberinto con id -> "+ selectedMazeId +
					" y dispo id -> " + selectedDispositionId);
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
	    comboLaberintos.addItem("Laberinto 1");
	    comboLaberintos.addItem("Laberinto 2");
	    comboLaberintos.addItem("Laberinto 3");
	    panel.add(comboLaberintos);

	    JLabel lblDispo = new JLabel("Selecciona una disposición (opcional):");
	    lblDispo.setForeground(Color.WHITE);
	    lblDispo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblDispo.setBounds(30, 100, 300, 20);
	    panel.add(lblDispo);

	    comboDisposiciones = new JComboBox<>();
	    comboDisposiciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboDisposiciones.setBounds(30, 125, 340, 25);
	    comboDisposiciones.addItem("Ninguna");
	    comboDisposiciones.addItem("Disposición A");
	    comboDisposiciones.addItem("Disposición B");
	    comboDisposiciones.addItem("Disposición C");
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
	    	    	}
	    	    });
	    	    btnVolver.setBounds(530, 401, 120, 30);
	    	    contentPane.add(btnVolver);
	    	    btnVolver.setBackground(Color.GRAY);
	    	    btnVolver.setForeground(Color.WHITE);
	    	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    	    
	    btnJugar.addActionListener(e -> pressBtnJugar());
	}
}