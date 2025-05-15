package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CrearLaberinto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            CrearLaberinto frame = new CrearLaberinto();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
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

	    JLabel lblTitulo = new JLabel("CREAR NUEVO LABERINTO");
	    lblTitulo.setForeground(Color.WHITE);
	    lblTitulo.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setBounds(50, 20, 650, 40);
	    contentPane.add(lblTitulo);

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(60, 70, 90));
	    panel.setBounds(100, 80, 530, 420);
	    panel.setLayout(null);
	    panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(panel);

	    Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);
	    Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
	    Color textColor = Color.WHITE;

	    String[] labels = {
	        "Nombre del laberinto:", "Ancho del tablero:", "Alto del tablero:",
	        "Número de cocodrilos:", "Número de botiquines:",
	        "Daño por cocodrilo (por defecto 25):", "Vida por botiquín (por defecto 10):",
	        "Tiempo máximo para responder:", "Daño por respuesta errónea:",
	        "¿Permitir ayudas externas?"
	    };

	    int y = 15;
	    JTextField[] campos = new JTextField[labels.length - 1];

	    for (int i = 0; i < labels.length - 1; i++) {
	        JLabel lbl = new JLabel(labels[i]);
	        lbl.setForeground(textColor);
	        lbl.setFont(labelFont);
	        lbl.setBounds(20, y, 300, 20);
	        panel.add(lbl);

	        JTextField field = new JTextField();
	        field.setFont(fieldFont);
	        field.setBounds(300, y, 200, 25);
	        panel.add(field);
	        campos[i] = field;

	        if (labels[i].contains("Daño por cocodrilo")) field.setText("25");
	        if (labels[i].contains("Vida por botiquín")) field.setText("10");

	        y += 40;
	    }

	    JCheckBox checkAyudas = new JCheckBox(labels[labels.length - 1]);
	    checkAyudas.setFont(labelFont);
	    checkAyudas.setBackground(new Color(60, 70, 90));
	    checkAyudas.setForeground(textColor);
	    checkAyudas.setBounds(17, 378, 243, 25);
	    panel.add(checkAyudas);
	    
	    	    JButton btnCrear = new JButton("Ir a definir muros");
	    	    btnCrear.setBounds(301, 370, 199, 40);
	    	    panel.add(btnCrear);
	    	    btnCrear.setBackground(new Color(30, 200, 150));
	    	    btnCrear.setForeground(Color.BLACK);
	    	    btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    	    
	    	    JButton btnVolver = new JButton("Volver");
	    	    btnVolver.setForeground(Color.WHITE);
	    	    btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    	    btnVolver.setBackground(Color.GRAY);
	    	    btnVolver.setBounds(580, 510, 120, 30);
	    	    contentPane.add(btnVolver);
	}

}
