package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GameController;

public class GameView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbHealth, lbMedkits, lbCrocodiles;
	private JButton btnUp, btnDown, btnLeft, btnRight, btnSurrender;
	private JLabel lbCellType;
	private GameController gameController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView frame = new GameView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateLbHealth(int health) {
	    lbHealth.setText("Vida: " + health);
	}

	public void updateLbMedkits(int medkits) {
	    lbMedkits.setText("Botiquines: " + medkits);
	}

	public void updateLbCrocodiles(int crocodiles) {
	    lbCrocodiles.setText("Cocodrilos: " + crocodiles);
	}
	
	public void updateBtnMoves(boolean[] moves) {
		if (moves.length >= 4) {
			btnUp.setEnabled(moves[0]);
			btnDown.setEnabled(moves[1]);
			btnLeft.setEnabled(moves[2]);
			btnRight.setEnabled(moves[3]);
		}	
	}

	public void updateCellType(int type) {
		switch(type) {
		case 0:
			lbCellType.setText("Casilla libre");
			lbCellType.setForeground(Color.WHITE);
			break;
		case 1:
			lbCellType.setText("Bloque");
			lbCellType.setForeground(Color.YELLOW);
			break;
		case 2:
			lbCellType.setText("Cocodrilo");
			lbCellType.setForeground(Color.RED);
			break;
		case 3:
			lbCellType.setText("Botiquín");
			lbCellType.setForeground(Color.GREEN);
			break;
		default:
			lbCellType.setText("");
			break;
		}
	}
	
	/**
	 * Create the frame.
	 */
	public GameView(GameController gameController) {
		this.gameController = gameController;
		setTitle("Partida");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 700, 500);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(40, 45, 60));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JLabel lbTitle = new JLabel("PARTIDA");
	    lbTitle.setBounds(50, 20, 600, 40);
	    lbTitle.setForeground(Color.WHITE);
	    lbTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
	    lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPane.add(lbTitle);

	    JPanel statePanel = new JPanel();
	    statePanel.setBounds(50, 80, 600, 60);
	    statePanel.setLayout(null);
	    statePanel.setBackground(new Color(60, 70, 90));
	    statePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	    contentPane.add(statePanel);

	    lbHealth = new JLabel("Salud: 100", SwingConstants.CENTER);
	    lbHealth.setForeground(Color.WHITE);
	    lbHealth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lbHealth.setBounds(54, 20, 107, 25);
	    statePanel.add(lbHealth);

	    lbMedkits = new JLabel("Medkits left: 3", SwingConstants.CENTER);
	    lbMedkits.setForeground(Color.WHITE);
	    lbMedkits.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lbMedkits.setBounds(209, 20, 181, 25);
	    statePanel.add(lbMedkits);

	    lbCrocodiles = new JLabel("Crocodiles left: 5", SwingConstants.CENTER);
	    lbCrocodiles.setForeground(Color.WHITE);
	    lbCrocodiles.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lbCrocodiles.setBounds(400, 20, 190, 25);
	    statePanel.add(lbCrocodiles);

	    JPanel controlPanel = new JPanel();
	    controlPanel.setBounds(200, 160, 300, 200);
	    controlPanel.setLayout(null);
	    controlPanel.setBackground(new Color(60, 70, 90));
	    contentPane.add(controlPanel);

	    btnUp = new JButton("↑");
	    btnUp.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnUp.setBounds(110, 39, 80, 50);
	    controlPanel.add(btnUp);

	    btnLeft = new JButton("←");
	    btnLeft.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnLeft.setBounds(30, 90, 80, 50);
	    controlPanel.add(btnLeft);

	    btnRight = new JButton("→");
	    btnRight.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnRight.setBounds(190, 90, 80, 50);
	    controlPanel.add(btnRight);

	    btnDown = new JButton("↓");
	    btnDown.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    btnDown.setBounds(110, 90, 80, 50);
	    controlPanel.add(btnDown);
	    
	    lbCellType = new JLabel("Casilla Libre", SwingConstants.CENTER);
	    lbCellType.setForeground(Color.WHITE);
	    lbCellType.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lbCellType.setBounds(62, 165, 181, 25);
	    controlPanel.add(lbCellType);

	    btnSurrender = new JButton("Rendirse");
	    btnSurrender.setBounds(270, 390, 150, 40);
	    btnSurrender.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    btnSurrender.setBackground(new Color(255, 0, 0));
	    contentPane.add(btnSurrender);
	    
	    btnUp.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gameController.move(0);
	    	}
	    });
	    
	    btnDown.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gameController.move(1);
	    	}
	    });
	    btnLeft.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gameController.move(2);
	    	}
	    });
	    
	    btnRight.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		gameController.move(3);
	    	}
	    });
	}
}