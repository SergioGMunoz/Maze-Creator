package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import model.Disposition;
import model.GameDAO;
import model.Maze;

public class SeleccionLaberinto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboLaberintos;
    private JComboBox<String> comboDisposiciones;
    private HashMap<String, Integer> mapaNombreALaberinto = new HashMap<>();

    private int selectedMazeId = 1;
    private int selectedDispositionId = 1;

    // Crea una nueva disposición respetando los muros y la configuración del Maze seleccionado
    private int crearNuevaDisposicion() {
        int nuevaDisposicionId = -1;
        try {
            GameDAO dao = new GameDAO();

            // 1. Calcula el siguiente ID_Disposition para este Maze
            int mazeId = selectedMazeId;
            int nextDispositionId = dao.obtenerUltimoIdDisposicion(mazeId) + 1;

            // 2. Obtén datos del Maze correcto y su disposición base (la 1 del Maze seleccionado)
            Maze maze = dao.getMazeById(mazeId);
            if (maze == null) {
            	throw new SQLException("No se ha encontrado el Maze seleccionado en la BBDD.");
            }
            Disposition dispBase = dao.getDispositionById(maze, 1); // Solo para copiar muros

            int filas = maze.getNumRow();
            int cols = maze.getNumCol();

            // 3. Copia matriz
            int[][] nuevaMatriz = new int[filas][cols];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    nuevaMatriz[i][j] = dispBase.getCells()[i][j];
                }
            }

            // 4. LIMPIA todos los cocodrilos y medkits de la copia, deja solo Free y Block
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (nuevaMatriz[i][j] == 2 || nuevaMatriz[i][j] == 3) {
                        nuevaMatriz[i][j] = 0; // Free
                    }
                }
            }

            // 5. Crea la nueva Disposition y añade cocodrilos/medkits SOLO con valores de este Maze
            Disposition nuevaDispo = new Disposition(maze, nuevaMatriz);
            nuevaDispo.añadirCocodrilosYMedkits(maze.getNumCrocodiles(), maze.getNumMedKit());

            // 6. Inserta la nueva disposición en la BBDD usando el método de GameDAO
            dao.insertarDisposicion(nuevaDispo, nextDispositionId);
            nuevaDisposicionId = nextDispositionId;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al crear nueva disposición: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return nuevaDisposicionId;
    }

    private void pressBtnJugar() {
        try {
            String mazeNameSelected = (String) comboLaberintos.getSelectedItem();
            selectedMazeId = mapaNombreALaberinto.getOrDefault(mazeNameSelected, 1);

            String dispoIdSelectedStr = (String) comboDisposiciones.getSelectedItem();

            if ("Nueva Disposicion".equals(dispoIdSelectedStr)) {
                selectedDispositionId = crearNuevaDisposicion();
                cargarDisposicionesPorMaze(selectedMazeId);
            } else if (dispoIdSelectedStr != null && dispoIdSelectedStr.startsWith("Disposicion: ")) {
                try {
                    selectedDispositionId = Integer.parseInt(dispoIdSelectedStr.substring("Disposicion: ".length()));
                } catch (NumberFormatException e) {
                    selectedDispositionId = 1;
                }
            } else {
                selectedDispositionId = 1;
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

        if (comboLaberintos.getItemCount() > 0) {
            String primerLaberinto = (String) comboLaberintos.getItemAt(0);
            int primerId = mapaNombreALaberinto.get(primerLaberinto);
            cargarDisposicionesPorMaze(primerId);
        }

        comboLaberintos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mazeNameSelected = (String) comboLaberintos.getSelectedItem();
                if (mazeNameSelected != null) {
                    int mazeId = mapaNombreALaberinto.get(mazeNameSelected);
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

    private void cargarLaberintos() {
        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID_Maze, Name FROM Maze");

            while (rs.next()) {
                int id = rs.getInt("ID_Maze");
                String nombre = rs.getString("Name");
                comboLaberintos.addItem(nombre);
                mapaNombreALaberinto.put(nombre, id);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los laberintos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDisposicionesPorMaze(int mazeId) {
        comboDisposiciones.removeAllItems();
        comboDisposiciones.addItem("Nueva Disposicion");
        try {
            Connection conn = ConnectionDB.getConnection();
            String sql = "SELECT DISTINCT ID_Disposition FROM Disposition WHERE ID_Maze = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
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
