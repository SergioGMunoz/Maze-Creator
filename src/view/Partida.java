package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Partida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblVida, lblBotiquines, lblCocodrilos;
	private JButton btnArriba, btnAbajo, btnIzquierda, btnDerecha, btnRendirse;

	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            Partida frame = new Partida();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public Partida() {
	    setTitle("Partida");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 700, 500);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitulo = new JLabel("PARTIDA");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 600, 40);
	    contentPane.add(lblTitulo);

	    JPanel panelEstado = new JPanel();
	    panelEstado.setLayout(null);
	    panelEstado.setBounds(50, 80, 600, 60);
	    panelEstado.setBackground(new Color(60, 70, 90));
	    panelEstado.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(panelEstado);

	    lblVida = new JLabel("Vida: 100");
	    lblVida.setForeground(Color.WHITE);
	    lblVida.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblVida.setBounds(60, 20, 107, 25);
	    panelEstado.add(lblVida);

	    lblBotiquines = new JLabel("Botiquines restantes: 3");
	    lblBotiquines.setForeground(Color.WHITE);
	    lblBotiquines.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblBotiquines.setBounds(194, 20, 181, 25);
	    panelEstado.add(lblBotiquines);

	    lblCocodrilos = new JLabel("Cocodrilos restantes: 5");
	    lblCocodrilos.setForeground(Color.WHITE);
	    lblCocodrilos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblCocodrilos.setBounds(400, 20, 200, 25);
	    panelEstado.add(lblCocodrilos);

	    JPanel panelControles = new JPanel();
	    panelControles.setLayout(null);
	    panelControles.setBounds(200, 160, 300, 200);
	    panelControles.setBackground(new Color(60, 70, 90));
	    panelControles.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(panelControles);

	    btnArriba = new JButton("↑");
	    btnArriba.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnArriba.setBounds(110, 39, 80, 50);
	    panelControles.add(btnArriba);

	    btnIzquierda = new JButton("←");
	    btnIzquierda.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnIzquierda.setBounds(30, 90, 80, 50);
	    panelControles.add(btnIzquierda);

	    btnDerecha = new JButton("→");
	    btnDerecha.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnDerecha.setBounds(190, 90, 80, 50);
	    panelControles.add(btnDerecha);

	    btnAbajo = new JButton("↓");
	    btnAbajo.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnAbajo.setBounds(110, 90, 80, 50);
	    panelControles.add(btnAbajo);

	    btnRendirse = new JButton("Rendirse");
	    btnRendirse.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnRendirse.setBackground(Color.RED);
	    btnRendirse.setForeground(Color.WHITE);
	    btnRendirse.setBounds(270, 390, 150, 40);
	    contentPane.add(btnRendirse);
	}

	public void actualizarVida(int vida) {
	    lblVida.setText("Vida: " + vida);
	}

	public void actualizarBotiquines(int b) {
	    lblBotiquines.setText("Botiquines: " + b);
	}

	public void actualizarCocodrilos(int c) {
	    lblCocodrilos.setText("Cocodrilos: " + c);
	}
}