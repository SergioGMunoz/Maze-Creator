package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PantallaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblInput;

	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            PantallaAdmin frame = new PantallaAdmin();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public PantallaAdmin() {
	    setTitle("Laberinto - Inicio");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 700, 500);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel lblTitle = new JLabel("PANTALLA CONFIGURACIÓN");
	    lblTitle.setForeground(new Color(255, 255, 255));
	    lblTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitle.setBounds(50, 20, 600, 50);
	    contentPane.add(lblTitle);

	    JPanel panelLogin = new JPanel();
	    panelLogin.setBackground(new Color(60, 70, 90));
	    panelLogin.setBounds(150, 100, 400, 280);
	    panelLogin.setLayout(null);
	    panelLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(panelLogin);

	    lblInput = new JLabel("");
	    lblInput.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	    lblInput.setForeground(Color.WHITE);
	    lblInput.setBounds(40, 80, 300, 25);
	    panelLogin.add(lblInput);
	    
	    JButton btnEntrar_1_1 = new JButton("Crear laberinto");
	    btnEntrar_1_1.setForeground(Color.BLACK);
	    btnEntrar_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnEntrar_1_1.setBackground(new Color(30, 200, 150));
	    btnEntrar_1_1.setBounds(109, 50, 177, 40);
	    panelLogin.add(btnEntrar_1_1);
	    
	    JButton btnEntrar_1_1_1 = new JButton("Añadir pregunta");
	    btnEntrar_1_1_1.setForeground(Color.BLACK);
	    btnEntrar_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnEntrar_1_1_1.setBackground(new Color(30, 200, 150));
	    btnEntrar_1_1_1.setBounds(109, 115, 177, 40);
	    panelLogin.add(btnEntrar_1_1_1);
	    
	    JButton btnEntrar_1_1_1_1 = new JButton("Volver al inicio");
	    btnEntrar_1_1_1_1.setForeground(Color.BLACK);
	    btnEntrar_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnEntrar_1_1_1_1.setBackground(new Color(30, 200, 150));
	    btnEntrar_1_1_1_1.setBounds(109, 186, 177, 40);
	    panelLogin.add(btnEntrar_1_1_1_1);
	}
}