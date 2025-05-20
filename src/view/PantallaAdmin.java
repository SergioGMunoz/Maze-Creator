package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PantallaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaAdmin() {
        setTitle("Pantalla Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        crearInterfaz();
    }

    private void crearInterfaz() {
        // Título
        JLabel lblTitulo = new JLabel("ADMINISTRACIÓN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 600, 50);
        contentPane.add(lblTitulo);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(60, 70, 90));
        panelPrincipal.setBounds(150, 100, 400, 280);
        panelPrincipal.setLayout(null);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        contentPane.add(panelPrincipal);

        // Botón Crear Laberinto
        JButton btnCrearLaberinto = new JButton("Crear Laberinto");
        btnCrearLaberinto.setBounds(110, 50, 180, 40);
        btnCrearLaberinto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCrearLaberinto.setBackground(new Color(30, 200, 150));
        btnCrearLaberinto.setForeground(Color.BLACK);
        panelPrincipal.add(btnCrearLaberinto);

        // Botón Añadir Preguntas
        JButton btnAñadirPreguntas = new JButton("Añadir Preguntas");
        btnAñadirPreguntas.setBounds(110, 115, 180, 40);
        btnAñadirPreguntas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAñadirPreguntas.setBackground(new Color(30, 200, 150));
        btnAñadirPreguntas.setForeground(Color.BLACK);
        panelPrincipal.add(btnAñadirPreguntas);

        // Botón Volver al Inicio
        JButton btnVolverInicio = new JButton("Volver al Inicio");
        btnVolverInicio.setBounds(110, 180, 180, 40);
        btnVolverInicio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolverInicio.setBackground(new Color(30, 200, 150));
        btnVolverInicio.setForeground(Color.BLACK);
        panelPrincipal.add(btnVolverInicio);

        // Configurar acciones de los botones
        configurarAcciones(btnCrearLaberinto, btnAñadirPreguntas, btnVolverInicio);
    }

    private void configurarAcciones(JButton btnCrearLaberinto, JButton btnAñadirPreguntas, JButton btnVolverInicio) {
        // Acción para el botón Crear Laberinto
        btnCrearLaberinto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearLaberinto crearLaberinto = new CrearLaberinto();
                crearLaberinto.setVisible(true);
                dispose();
            }
        });

        // Acción para el botón Añadir Preguntas
        btnAñadirPreguntas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AñadirPreguntas añadirPreguntas = new AñadirPreguntas();
                añadirPreguntas.setVisible(true);
                dispose();
            }
        });

        // Acción para el botón Volver al Inicio
        btnVolverInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaInicio inicio = new PantallaInicio();
                inicio.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaAdmin frame = new PantallaAdmin();
                frame.setVisible(true);
            }
        });
    }
}