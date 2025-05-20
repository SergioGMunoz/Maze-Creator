package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.GameDAO;

public class DefinirMuros extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel gridPanel;
    private JButton[][] celdas;
    private int filas;
    private int columnas;
    private boolean[][] esMuro;
    private int idLaberinto;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                DefinirMuros frame = new DefinirMuros(1, 10, 8); // Ejemplo con ID 1, 10x8
                frame.setVisible(true);
            }
        });
    }

    public DefinirMuros(int idLaberinto, int ancho, int alto) {
        this.idLaberinto = idLaberinto;
        this.filas = alto;
        this.columnas = ancho;
        this.esMuro = new boolean[filas][columnas];

        configurarVentana();
        crearInterfaz();
    }

    private void configurarVentana() {
        setTitle("Definir muros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    private void crearInterfaz() {
        // Título
        JLabel lblTitulo = new JLabel("DEFINIR MUROS DEL LABERINTO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 700, 40);
        contentPane.add(lblTitulo);

        // Panel de la cuadrícula
        gridPanel = new JPanel();
        gridPanel.setBounds(50, 80, 700, 500);
        gridPanel.setLayout(new GridLayout(filas, columnas, 2, 2));
        contentPane.add(gridPanel);

        // Crear celdas
        crearCeldas();

        // Botones
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirmar.setBackground(new Color(30, 200, 150));
        btnConfirmar.setBounds(250, 600, 140, 40);
        contentPane.add(btnConfirmar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.setBackground(new Color(30, 200, 150));
        btnVolver.setBounds(420, 600, 140, 40);
        contentPane.add(btnVolver);

        // Configurar listeners
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmarMuros();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
    }

    private void crearCeldas() {
        celdas = new JButton[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                final int fila = i;
                final int columna = j;
                
                JButton btnCelda = new JButton();
                btnCelda.setBackground(Color.WHITE);
                btnCelda.setOpaque(true);
                btnCelda.setBorder(new LineBorder(Color.GRAY));
                
                btnCelda.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        alternarCelda(fila, columna);
                    }
                });
                
                celdas[i][j] = btnCelda;
                gridPanel.add(btnCelda);
            }
        }
    }

    private void alternarCelda(int fila, int columna) {
        esMuro[fila][columna] = !esMuro[fila][columna];
        if (esMuro[fila][columna]) {
            celdas[fila][columna].setBackground(Color.DARK_GRAY);
            celdas[fila][columna].setText("Muro");
        } else {
            celdas[fila][columna].setBackground(Color.WHITE);
            celdas[fila][columna].setText("");
        }
    }

    private void confirmarMuros() {
        try {
            GameDAO dao = new GameDAO();
            dao.guardarMuros(idLaberinto, esMuro);
            
            JOptionPane.showMessageDialog(this, 
                "Muros guardados correctamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar los muros: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public boolean[][] getMuros() {
        return esMuro;
    }
}