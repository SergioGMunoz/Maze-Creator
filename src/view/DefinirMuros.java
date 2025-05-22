package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.GameController;

public class DefinirMuros extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel gridPanel;
    private JButton[][] botones;
    private boolean[][] esMuro;

    private int idLaberinto;
    private int filas;
    private int columnas;
    private int numCocodrilos;
    private int numMedkits;
    private JFrame ventanaAnterior;

    public DefinirMuros(int idLaberinto, int columnas, int filas, int numCocodrilos, int numMedkits, JFrame ventanaAnterior) {
        this.idLaberinto = idLaberinto;
        this.columnas = columnas;
        this.filas = filas;
        this.numCocodrilos = numCocodrilos;
        this.numMedkits = numMedkits;
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Definir Muros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        crearInterfaz();
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
        botones = new JButton[filas][columnas];
        esMuro = new boolean[filas][columnas];
        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                final int finalRow = row;
                final int finalCol = col;

                JButton boton = new JButton();
                boton.setBackground(Color.WHITE);
                boton.setOpaque(true);
                boton.setBorder(new LineBorder(Color.GRAY));

                // Entrada (arriba izquierda)
                if (row == 0 && col == 0) {
                    boton.setBackground(Color.GREEN);
                    boton.setEnabled(false);
                    boton.setText("Entrada");
                    esMuro[row][col] = false;
                }
                // Salida (abajo derecha)
                else if (row == filas - 1 && col == columnas - 1) {
                    boton.setBackground(Color.RED);
                    boton.setEnabled(false);
                    boton.setText("Salida");
                    esMuro[row][col] = false;
                }

                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        toggleMuro(finalRow, finalCol);
                    }
                });

                botones[row][col] = boton;
                gridPanel.add(boton);
            }
        }

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirmar.setBackground(new Color(30, 200, 150));
        btnConfirmar.setBounds(250, 600, 140, 40);
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarDisposicion();
            }
        });
        contentPane.add(btnConfirmar);

        JButton btnCancelar = new JButton("Volver");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCancelar.setBackground(new Color(30, 200, 150));
        btnCancelar.setBounds(420, 600, 140, 40);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ventanaAnterior.setVisible(true);
            }
        });
        contentPane.add(btnCancelar);
    }


    // Alternar entre muro y no muro
    private void toggleMuro(int row, int col) {
        esMuro[row][col] = !esMuro[row][col];
        if (esMuro[row][col]) {
            botones[row][col].setBackground(Color.DARK_GRAY);
            botones[row][col].setText("Muro");
        } else {
            botones[row][col].setBackground(Color.WHITE);
            botones[row][col].setText("");
        }
    }

    // Guardar disposición
    private void guardarDisposicion() {
        GameController.crearDisposicion(idLaberinto, esMuro, numCocodrilos, numMedkits);

        JOptionPane.showMessageDialog(this, 
            "Disposición creada correctamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}