package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaRanking;
	private JComboBox<String> comboLaberintos;
	private JComboBox<String> comboDisposiciones;
	private JButton btnVolverAJugar;

	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            Ranking frame = new Ranking();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public Ranking() {
	    setTitle("Ranking");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 750, 550);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitulo = new JLabel("RANKING DE JUGADORES");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 650, 40);
	    contentPane.add(lblTitulo);

	    // Selector de laberintos
	    comboLaberintos = new JComboBox<>();
	    comboLaberintos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboLaberintos.setBounds(60, 80, 250, 30);
	    comboLaberintos.addItem("Laberinto 1");
	    comboLaberintos.addItem("Laberinto 2");
	    contentPane.add(comboLaberintos);

	    // Selector de disposición
	    comboDisposiciones = new JComboBox<>();
	    comboDisposiciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboDisposiciones.setBounds(430, 80, 250, 30);
	    comboDisposiciones.addItem("Disposición A");
	    comboDisposiciones.addItem("Disposición B");
	    comboDisposiciones.addItem("Ninguna");
	    contentPane.add(comboDisposiciones);

	    // Tabla de ranking
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(60, 130, 620, 300);
	    contentPane.add(scrollPane);

	    tablaRanking = new JTable();
	    tablaRanking.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    tablaRanking.setRowHeight(25);
	    tablaRanking.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
	    tablaRanking.setModel(new DefaultTableModel(
	        new Object[][] {
	            {"1", "JugadorA", "300 pts"},
	            {"2", "JugadorB", "280 pts"},
	            {"3", "JugadorC", "260 pts"},
	            {"4", "JugadorD", "250 pts"},
	            {"5", "JugadorE", "240 pts"},
	            {"6", "JugadorF", "230 pts"},
	            {"7", "JugadorG", "220 pts"},
	            {"8", "JugadorH", "210 pts"},
	            {"9", "JugadorI", "200 pts"},
	            {"10", "JugadorJ", "190 pts"},
	        },
	        new String[] {"Posición", "Jugador", "Puntos"}
	    ));
	    scrollPane.setViewportView(tablaRanking);

	    // Botón volver
	    JButton btnVolver = new JButton("Ir al inicio");
	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnVolver.setBackground(new Color(30, 200, 150));
	    btnVolver.setBounds(430, 450, 150, 40);
	    contentPane.add(btnVolver);
	    
	    btnVolverAJugar = new JButton("Volver a jugar");
	    btnVolverAJugar.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnVolverAJugar.setBackground(new Color(30, 200, 150));
	    btnVolverAJugar.setBounds(136, 450, 150, 40);
	    contentPane.add(btnVolverAJugar);
	}

}