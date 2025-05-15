package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaIncio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JPasswordField passwordField;
    private JTextField aliasField;
    private JLabel lblInput;
    private String usuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaIncio frame = new PantallaIncio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaIncio() {
        setTitle("Laberinto - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("BIENVENIDO AL JUEGO LABERINTO");
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

        JRadioButton rdbtnAdmin = new JRadioButton("Administrador");
        rdbtnAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rdbtnAdmin.setForeground(Color.WHITE);
        rdbtnAdmin.setBackground(new Color(60, 70, 90));
        rdbtnAdmin.setBounds(40, 32, 150, 30);
        buttonGroup.add(rdbtnAdmin);
        panelLogin.add(rdbtnAdmin);

        JRadioButton rdbtnUsuario = new JRadioButton("Jugador");
        rdbtnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rdbtnUsuario.setForeground(Color.WHITE);
        rdbtnUsuario.setBackground(new Color(60, 70, 90));
        rdbtnUsuario.setBounds(231, 32, 150, 30);
        buttonGroup.add(rdbtnUsuario);
        panelLogin.add(rdbtnUsuario);

        lblInput = new JLabel("");
        lblInput.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblInput.setForeground(Color.WHITE);
        lblInput.setBounds(40, 80, 300, 25);
        panelLogin.add(lblInput);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 110, 320, 30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setVisible(false);
        panelLogin.add(passwordField);

        aliasField = new JTextField();
        aliasField.setBounds(40, 110, 320, 30);
        aliasField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        aliasField.setVisible(false);
        panelLogin.add(aliasField);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(30, 200, 150));
        btnEntrar.setForeground(Color.BLACK);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEntrar.setBounds(130, 180, 140, 40);
        panelLogin.add(btnEntrar);

        rdbtnAdmin.addActionListener(e -> {
            lblInput.setText("Introduce la contraseña:");
            passwordField.setVisible(true);
            aliasField.setVisible(false);
        });

        rdbtnUsuario.addActionListener(e -> {
            lblInput.setText("Introduce el alias del jugador:");
            aliasField.setVisible(true);
            passwordField.setVisible(false);
        });

        btnEntrar.addActionListener(e -> {
            if (rdbtnAdmin.isSelected()) {
                String pass = new String(passwordField.getPassword());
                if (pass.equals("1234")) {
                    JOptionPane.showMessageDialog(this, "Acceso concedido como ADMIN.");
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (rdbtnUsuario.isSelected()) {
                usuario = aliasField.getText().trim();
                if (!usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario);
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, introduce un alias.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un tipo de usuario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
