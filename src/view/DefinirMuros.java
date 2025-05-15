package view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class DefinirMuros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gridPanel;
	private JButton[][] celdas;
	private int filas;
	private int columnas;
	private boolean[][] esMuro;

	public DefinirMuros(int ancho, int alto) {
	    this.filas = alto;
	    this.columnas = ancho;
	    this.esMuro = new boolean[filas][columnas];

	    setTitle("Definir muros");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 800, 700);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitulo = new JLabel("DEFINIR MUROS DEL LABERINTO");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 700, 40);
	    contentPane.add(lblTitulo);

	    // Panel para la cuadrícula del laberinto
	    gridPanel = new JPanel();
	    gridPanel.setBounds(50, 80, 700, 500);
	    gridPanel.setLayout(new GridLayout(filas, columnas, 2, 2));
	    contentPane.add(gridPanel);

	    // Crear botones de celda
	    celdas = new JButton[filas][columnas];
	    for (int i = 0; i < filas; i++) {
	        for (int j = 0; j < columnas; j++) {
	            JButton btnCelda = new JButton();
	            btnCelda.setBackground(Color.WHITE);
	            btnCelda.setOpaque(true);
	            btnCelda.setBorder(new LineBorder(Color.GRAY));
	            final int row = i;
	            final int col = j;
	            btnCelda.addActionListener(e -> toggleCelda(row, col));
	            celdas[i][j] = btnCelda;
	            gridPanel.add(btnCelda);
	        }
	    }

	    // Botón Confirmar
	    JButton btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnConfirmar.setBackground(new Color(30, 200, 150));
	    btnConfirmar.setBounds(250, 600, 140, 40);
	    contentPane.add(btnConfirmar);

	    // Botón Volver
	    JButton btnVolver = new JButton("Volver");
	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnVolver.setBackground(new Color(30, 200, 150));
	    btnVolver.setBounds(420, 600, 140, 40);
	    contentPane.add(btnVolver);

	    // Acción Confirmar
	    btnConfirmar.addActionListener(e -> {
	        // Aquí podrías guardar la matriz esMuro al modelo del laberinto
	        JOptionPane.showMessageDialog(this, "¡Muros definidos correctamente!");
	    });

	    btnVolver.addActionListener(e -> {
	        dispose(); // o volver a la ventana anterior
	    });
	}

	private void toggleCelda(int row, int col) {
	    esMuro[row][col] = !esMuro[row][col];
	    celdas[row][col].setBackground(esMuro[row][col] ? Color.DARK_GRAY : Color.WHITE);
	}

	// Puedes añadir un método getMuros() si necesitas recuperar la matriz luego
	public boolean[][] getMuros() {
	    return esMuro;
	}

	// Método main de prueba
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        DefinirMuros frame = new DefinirMuros(10, 8); // ejemplo 10x8
	        frame.setVisible(true);
	    });
	}
}