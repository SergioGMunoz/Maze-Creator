package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.GameDAO;
import model.Maze;

public class CrearLaberinto extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCrear;
    private JTextField[] campos;
    private JCheckBox checkAyudas;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrearLaberinto frame = new CrearLaberinto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CrearLaberinto() {
        setTitle("Crear Laberinto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 600);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        crearInterfaz();
    }

    private void crearInterfaz() {
        // Título
        JLabel lblTitulo = new JLabel("CREAR NUEVO LABERINTO");
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

        // Configuración de fuentes y colores
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color textColor = Color.WHITE;

        // Etiquetas y campos de texto
        String[] labels = {
            "Nombre del laberinto:", "Ancho del tablero:", "Alto del tablero:",
            "Número de cocodrilos:", "Número de botiquines:",
            "Daño por cocodrilo (por defecto 25):", "Vida por botiquín (por defecto 10):",
            "Tiempo máximo para responder:", "Daño por respuesta errónea:",
            "¿Permitir ayudas externas?"
        };

        campos = new JTextField[labels.length - 1];
        int yPos = 15;

        for (int i = 0; i < labels.length - 1; i++) {
            // Etiqueta
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(textColor);
            lbl.setFont(labelFont);
            lbl.setBounds(20, yPos, 300, 20);
            panel.add(lbl);

            // Campo de texto
            JTextField field = new JTextField();
            field.setFont(fieldFont);
            field.setBounds(300, yPos, 200, 25);
            panel.add(field);
            campos[i] = field;

            // Valores por defecto
            if (labels[i].contains("Daño por cocodrilo")) {
                field.setText("25");
            }
            if (labels[i].contains("Vida por botiquín")) {
                field.setText("10");
            }

            yPos += 40;
        }

        // Checkbox para ayudas externas
        checkAyudas = new JCheckBox(labels[labels.length - 1]);
        checkAyudas.setFont(labelFont);
        checkAyudas.setBackground(new Color(60, 70, 90));
        checkAyudas.setForeground(textColor);
        checkAyudas.setBounds(17, 378, 243, 25);
        panel.add(checkAyudas);

        // Botón para crear laberinto
        btnCrear = new JButton("Ir a definir muros");
        btnCrear.setBounds(301, 370, 199, 40);
        btnCrear.setBackground(new Color(30, 200, 150));
        btnCrear.setForeground(Color.BLACK);
        btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCrear.setEnabled(false);
        panel.add(btnCrear);

        // Botón para volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBackground(Color.GRAY);
        btnVolver.setBounds(580, 510, 120, 30);
        contentPane.add(btnVolver);

        // Configurar listeners
        configurarListeners(btnVolver);
    }

    private void configurarListeners(JButton btnVolver) {
        // Listener para verificar campos
        DocumentListener verificador = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                verificarCampos();
            }
            public void removeUpdate(DocumentEvent e) {
                verificarCampos();
            }
            public void insertUpdate(DocumentEvent e) {
                verificarCampos();
            }

            private void verificarCampos() {
                boolean todosLlenos = true;
                for (JTextField campo : campos) {
                    if (campo.getText().trim().isEmpty()) {
                        todosLlenos = false;
                        break;
                    }
                }
                btnCrear.setEnabled(todosLlenos);
            }
        };

        for (JTextField campo : campos) {
            campo.getDocument().addDocumentListener(verificador);
        }

        // Listener para el botón crear
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearLaberinto();
            }
        });

        // Listener para el botón volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAPantallaAnterior();
            }
        });
    }

    private void crearLaberinto() {
        if (validarFormulario()) {
            try {
                // Recoger datos del formulario
                String nombre = campos[0].getText();
                int ancho = Integer.parseInt(campos[1].getText());
                int alto = Integer.parseInt(campos[2].getText());
                int numCocodrilos = Integer.parseInt(campos[3].getText());
                int numBotiquines = Integer.parseInt(campos[4].getText());
                int danioCocodrilos = Integer.parseInt(campos[5].getText());
                int vidaBotiquines = Integer.parseInt(campos[6].getText());
                int tiempoRespuesta = Integer.parseInt(campos[7].getText());
                int danioRespuestaErronea = Integer.parseInt(campos[8].getText());
                boolean permitirAyudas = checkAyudas.isSelected();

                // Crear y guardar laberinto
                Maze laberinto = new Maze(danioRespuestaErronea, nombre, ancho, alto, 
                                        numCocodrilos, numBotiquines, danioCocodrilos, 
                                        vidaBotiquines, tiempoRespuesta, danioRespuestaErronea, permitirAyudas);

                GameDAO dao = new GameDAO();
                int idLaberinto = dao.guardarLaberinto(laberinto);

                // Abrir pantalla para definir muros
                abrirDefinirMuros(idLaberinto, ancho, alto);
                dispose();

            } catch (NumberFormatException ex) {
                mostrarError("Por favor, introduzca números válidos en los campos numéricos", "Error de formato");
            } catch (SQLException ex) {
                mostrarError("Error al conectar con la base de datos: " + ex.getMessage(), "Error de conexión");
            }
        }
    }

    private boolean validarFormulario() {
        // Validar campos vacíos
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].getText().trim().isEmpty()) {
                mostrarError("Todos los campos son obligatorios. Por favor complete el campo " + (i+1), "Campo vacío");
                campos[i].requestFocus();
                return false;
            }
        }

        try {
            // Validar números positivos
            for (int i = 1; i < campos.length; i++) {
                int valor = Integer.parseInt(campos[i].getText());
                if (valor <= 0) {
                    mostrarError("Los valores numéricos deben ser mayores que cero", "Valor inválido");
                    campos[i].requestFocus();
                    return false;
                }
            }

            // Validar dimensiones mínimas
            int ancho = Integer.parseInt(campos[1].getText());
            int alto = Integer.parseInt(campos[2].getText());
            if (ancho < 4 || alto < 4) {
                mostrarError("El tablero debe tener al menos un tamaño de 4x4", "Dimensiones inválidas");
                return false;
            }

            // Validar número de cocodrilos y botiquines
            int numCocodrilos = Integer.parseInt(campos[3].getText());
            int numBotiquines = Integer.parseInt(campos[4].getText());
            int casillasDisponibles = ancho * alto;

            if (numCocodrilos + numBotiquines >= casillasDisponibles) {
                mostrarError("La suma de cocodrilos y botiquines no puede ser igual o superior al número de casillas", 
                            "Configuración inválida");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            mostrarError("Por favor, introduzca números válidos en los campos numéricos", "Error de formato");
            return false;
        }
    }

    private void abrirDefinirMuros(int idLaberinto, int ancho, int alto) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DefinirMuros definirMuros = new DefinirMuros(idLaberinto, ancho, alto);
                    definirMuros.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    mostrarError("Error al abrir la ventana de definición de muros: " + ex.getMessage(), "Error");
                }
            }
        });
    }

    private void volverAPantallaAnterior() {
        dispose();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaAdmin frame = new PantallaAdmin();
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void mostrarError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}