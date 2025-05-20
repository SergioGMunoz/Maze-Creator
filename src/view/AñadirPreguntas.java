package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.GameDAO;

public class AñadirPreguntas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField[] respuestasFields = new JTextField[4];
    private JRadioButton[] opcionesCorrectas = new JRadioButton[4];
    private ButtonGroup grupoRespuestas = new ButtonGroup();
    private JTextField enunciadoField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AñadirPreguntas frame = new AñadirPreguntas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AñadirPreguntas() {
        setTitle("Añadir Preguntas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Configuración del título
        JLabel lblTitulo = new JLabel("AÑADIR PREGUNTA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 650, 40);
        contentPane.add(lblTitulo);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 70, 90));
        panel.setBounds(100, 80, 530, 420);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        contentPane.add(panel);

        // Fuentes y colores
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color textColor = Color.WHITE;

        // Campo para el enunciado
        JLabel lblEnunciado = new JLabel("Enunciado de la pregunta:");
        lblEnunciado.setForeground(textColor);
        lblEnunciado.setFont(labelFont);
        lblEnunciado.setBounds(20, 15, 300, 20);
        panel.add(lblEnunciado);

        enunciadoField = new JTextField();
        enunciadoField.setFont(fieldFont);
        enunciadoField.setBounds(20, 40, 480, 25);
        panel.add(enunciadoField);

        // Sección de respuestas
        JLabel lblRespuestas = new JLabel("Respuestas posibles (selecciona la correcta):");
        lblRespuestas.setForeground(textColor);
        lblRespuestas.setFont(labelFont);
        lblRespuestas.setBounds(20, 80, 400, 20);
        panel.add(lblRespuestas);

        // Campos para las respuestas y radio buttons
        int y = 110;
        for (int i = 0; i < 4; i++) {
            respuestasFields[i] = new JTextField();
            respuestasFields[i].setFont(fieldFont);
            respuestasFields[i].setBounds(50, y, 380, 25);
            panel.add(respuestasFields[i]);

            opcionesCorrectas[i] = new JRadioButton();
            opcionesCorrectas[i].setBackground(new Color(60, 70, 90));
            opcionesCorrectas[i].setBounds(20, y, 20, 25);
            grupoRespuestas.add(opcionesCorrectas[i]);
            panel.add(opcionesCorrectas[i]);
            y += 40;
        }

        // Botón para añadir pregunta
        JButton btnAnadir = new JButton("Añadir Pregunta");
        btnAnadir.setBackground(new Color(30, 200, 150));
        btnAnadir.setForeground(Color.BLACK);
        btnAnadir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAnadir.setBounds(170, y + 10, 200, 40);
        panel.add(btnAnadir);

        // Acción del botón añadir
        btnAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPregunta();
            }
        });

        // Botón para volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(581, 511, 120, 30);
        contentPane.add(btnVolver);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBackground(Color.GRAY);

        // Acción del botón volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaAdmin admin = new PantallaAdmin();
                admin.setVisible(true);
                dispose();
            }
        });
    }

    private void guardarPregunta() {
        // Validar que el enunciado no esté vacío
        if (enunciadoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El enunciado de la pregunta no puede estar vacío", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que todas las respuestas tengan contenido
        for (int i = 0; i < respuestasFields.length; i++) {
            if (respuestasFields[i].getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Todas las respuestas deben tener contenido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Validar que se haya seleccionado una respuesta correcta
        int respuestaCorrecta = -1;
        for (int i = 0; i < opcionesCorrectas.length; i++) {
            if (opcionesCorrectas[i].isSelected()) {
                respuestaCorrecta = i;
                break;
            }
        }

        if (respuestaCorrecta == -1) {
            JOptionPane.showMessageDialog(this, 
                "Debes seleccionar la respuesta correcta", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar en la base de datos
        try {
            GameDAO dao = new GameDAO();
            dao.guardarPregunta(
                enunciadoField.getText(),
                respuestasFields[respuestaCorrecta].getText(),
                respuestasFields[0].getText(),
                respuestasFields[1].getText(),
                respuestasFields[2].getText(),
                respuestasFields[3].getText()
            );
            
            JOptionPane.showMessageDialog(this, 
                "Pregunta añadida correctamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar campos después de guardar
            limpiarCampos();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar la pregunta: " + ex.getMessage(), 
                "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        enunciadoField.setText("");
        for (JTextField campo : respuestasFields) {
            campo.setText("");
        }
        grupoRespuestas.clearSelection();
    }
}