package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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

public class SeleccionLaberinto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboLaberintos;
    private JComboBox<String> comboDisposiciones;
    private HashMap<String, Integer> mazeNameToIdMap = new HashMap<>();

    // Por defecto sale el laberinto 1 disposición 1
    private int selectedMazeId = 1;
    private int selectedDispositionId = 1;

    // NUEVO: Crea una nueva disposición real en la BBDD usando la lógica de Disposition
    private int crearNuevaDisposicion() {
        int nuevaDisposicionId = -1;
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            // 1. Obtén el siguiente ID_Disposition para este maze
            int mazeId = selectedMazeId;
            int nextDispositionId = 1;

            String sqlNextId = "SELECT MAX(ID_Disposition) AS maxId FROM Disposition WHERE ID_Maze = ?";
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(sqlNextId)) {
                stmt.setInt(1, mazeId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    nextDispositionId = rs.getInt("maxId") + 1;
                }
                rs.close();
            }

            // 2. Crea la matriz de celdas vacía (todo libre)
            //    Obtén el Maze para saber su tamaño y valores
            model.GameDAO dao = new model.GameDAO();
            model.Maze maze = dao.getMazeById(mazeId);
            int filas = maze.getNumRow();
            int cols = maze.getNumCol();
            int[][] cells = new int[filas][cols];
            // Protege entrada y salida
            cells[0][0] = 0;
            cells[filas-1][cols-1] = 0;

            // 3. Añade cocodrilos y medkits con el método de Disposition
            model.Disposition dispo = new model.Disposition(maze, cells);

            // Puedes pedir al usuario cuántos, o usar los valores por defecto del maze:
            dispo.añadirCocodrilosYMedkits(maze.getNumCrocodiles(), maze.getNumMedKit());

            // 4. Inserta TODAS las celdas en la tabla
            String sqlInsert = "INSERT INTO Disposition (ID_Maze, ID_Disposition, Col_Maze, Row_Maze, Cell_Type) VALUES (?, ?, ?, ?, ?)";
            try (java.sql.PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                for (int row = 0; row < filas; row++) {
                    for (int col = 0; col < cols; col++) {
                        ps.setInt(1, mazeId);
                        ps.setInt(2, nextDispositionId);
                        ps.setInt(3, col);
                        ps.setInt(4, row);

                        String tipo = switch (cells[row][col]) {
                            case 0 -> "Free";
                            case 1 -> "Block";
                            case 2 -> "Crocodile";
                            case 3 -> "Medkit";
                            default -> "Free";
                        };

                        ps.setString(5, tipo);
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }

            nuevaDisposicionId = nextDispositionId;

        } catch (Exception e) {
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
                selectedDispositionId = crearNuevaDisposicion();
                // Opcional: recarga las dispos tras crear una nueva
                cargarDisposicionesPorMaze(selectedMazeId);
            }
            else if (dispoIdSelectedStr != null && dispoIdSelectedStr.startsWith("Disposicion: ")) {
                try {
                    selectedDispositionId = Integer.parseInt(dispoIdSelectedStr.substring("Disposicion: ".length()));
                } catch (NumberFormatException e) {
                    selectedDispositionId = 1; // Valor por defecto
                }
            }
            else {
                selectedDispositionId = 1; // Valor por defecto
            }

            GameController gameController = new GameController(selectedMazeId, selectedDispositionId);
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
        cargarLaberintos();
        panel.add(comboLaberintos);

        JLabel lblDispo = new JLabel("Selecciona una disposición (opcional):");
        lblDispo.setForeground(Color.WHITE);
        lblDispo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDispo.setBounds(30, 100, 300, 20);
        panel.add(lblDispo);

        comboDisposiciones = new JComboBox<>();
        comboDisposiciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboDisposiciones.setBounds(30, 125, 340, 25);
        panel.add(comboDisposiciones);

        // Cargar disposiciones iniciales para el primer laberinto
        if (comboLaberintos.getItemCount() > 0) {
            String primerLaberinto = (String) comboLaberintos.getItemAt(0);
            int primerId = mazeNameToIdMap.get(primerLaberinto);
            cargarDisposicionesPorMaze(primerId);
        }

        // Listener para actualizar disposiciones al cambiar de laberinto
        comboLaberintos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mazeNameSelected = (String) comboLaberintos.getSelectedItem();
                if (mazeNameSelected != null) {
                    int mazeId = mazeNameToIdMap.get(mazeNameSelected);
                    cargarDisposicionesPorMaze(mazeId);
                }
            }
        });

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setBackground(new Color(30, 200, 150));
        btnJugar.setForeground(Color.BLACK);
        btnJugar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnJugar.setBounds(115, 175, 160, 40);
        panel.add(btnJugar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("VOLVIENDO");
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

    // Método para cargar los laberintos en el combo
    private void cargarLaberintos() {
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
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los laberintos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para cargar solo las disposiciones asociadas al laberinto seleccionado
    private void cargarDisposicionesPorMaze(int mazeId) {
        comboDisposiciones.removeAllItems();
        comboDisposiciones.addItem("Nueva Disposicion");
        try {
            Connection conn = ConnectionDB.getConnection();
            String sql = "SELECT DISTINCT ID_Disposition FROM Disposition WHERE ID_Maze = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mazeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dispoId = rs.getString("ID_Disposition");
                comboDisposiciones.addItem("Disposicion: " + dispoId);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las disposiciones desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}