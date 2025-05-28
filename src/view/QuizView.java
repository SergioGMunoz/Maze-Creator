package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import controller.QuizController;

public class QuizView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbHealth, lbDamage, lbError;
    private JRadioButton rdOption1, rdOption2, rdOption3, rdOption4;
    private ButtonGroup grupoOpciones = new ButtonGroup();
    private ArrayList <JRadioButton> radioButtons = new ArrayList <>();
    private JLabel lbPregunta;
    private QuizController quizController;
    private JButton btnEnviar;
    private JLabel lblTime;
    private int seconds;
    private Timer timer;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	QuizView frame = new QuizView(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    
    
    public void setSeconds(int seconds) {
		this.seconds = seconds;
	}



	public void updateQuestion(String question, ArrayList <String> answers) {
    	lbPregunta.setText(question);
    	rdOption1.setText(answers.get(0));
    	rdOption2.setText(answers.get(1));
    	rdOption3.setText(answers.get(2));
    	rdOption4.setText(answers.get(3));
    }

    public void setLbErrorVisibility(boolean visible) {
    	lbError.setVisible(visible);
	}
    
    public void setDmgAnswer(int dmg) {
    	lbDamage.setText("Daño por fallo: " + dmg);
    }
    
    public void setHealth(int hp) {
    	lbHealth.setText("Vida: " + hp);
    }
    
    private void updateBtnSent(boolean enable) {
    	btnEnviar.setEnabled(enable);
	}
    
    // Enlace: https://old.chuidiang.org/java/timer/timer.php
    public void timerStart() {
    	lblTime.setText(seconds + "s");
    	
    	if (timer != null && timer.isRunning()) {
    	    timer.stop();
    	}
    	
    	timer = new Timer (1000, new ActionListener ()
    	{
    	    public void actionPerformed(ActionEvent e){
    	        seconds--;
    	        //System.out.println("Un segundo..."+seconds);
    	        if (seconds <= 0) {
    	        	seconds = 0;
    	        	quizController.checkAnswer("no-time-response");
    	        	System.out.println("Se acabó el tiempo");
    	        	if (timer != null && timer.isRunning()) {
            		    timer.stop();
            		}
    	        }
    	        lblTime.setText(seconds +"s");
    	     }
    	});
    	timer.start();
    }
    
    
    
	public QuizView(QuizController quizController) {
		this.quizController = quizController;
        setTitle("Pregunta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 45, 60));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lbHealth = new JLabel("Vida: 100");
        lbHealth.setForeground(Color.WHITE);
        lbHealth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lbHealth.setBounds(100, 20, 150, 25);
        contentPane.add(lbHealth);

        lbDamage = new JLabel("Daño por fallo: 20");
        lbDamage.setForeground(Color.WHITE);
        lbDamage.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lbDamage.setBounds(499, 20, 180, 25);
        contentPane.add(lbDamage);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 70, 90));
        panel.setBounds(100, 70, 530, 370);
        panel.setLayout(null);
        contentPane.add(panel);

        lbPregunta = new JLabel("¿Cuál es la capital de Francia?");
        lbPregunta.setForeground(Color.WHITE);
        lbPregunta.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbPregunta.setBounds(20, 20, 490, 30);
        panel.add(lbPregunta);

        rdOption1 = new JRadioButton("Opcion 1");
        grupoOpciones.add(rdOption1);
        rdOption1.setBounds(30, 69, 460, 30);
        panel.add(rdOption1);
        
        
        rdOption2 = new JRadioButton("Opcion 2");
        grupoOpciones.add(rdOption2);
        rdOption2.setBounds(30, 120, 460, 30);
        panel.add(rdOption2);

        rdOption3 = new JRadioButton("Opcion 3");
        grupoOpciones.add(rdOption3);
        rdOption3.setBounds(30, 170, 460, 30);
        panel.add(rdOption3);

        rdOption4 = new JRadioButton("Opcion 4");
        grupoOpciones.add(rdOption4);
        rdOption4.setBounds(30, 220, 460, 30);
        panel.add(rdOption4);
        
        radioButtons.add(rdOption1);
		radioButtons.add(rdOption2);
		radioButtons.add(rdOption3);
		radioButtons.add(rdOption4);

        lbError = new JLabel("Respuesta incorrecta");
        lbError.setForeground(Color.RED);
        lbError.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbError.setBounds(30, 275, 300, 25);
        lbError.setVisible(false);
        panel.add(lbError);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setEnabled(false);
        btnEnviar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEnviar.setBackground(new Color(30, 200, 150));
        btnEnviar.setBounds(180, 310, 160, 40);
        panel.add(btnEnviar);
        
        lblTime = new JLabel("s");
        lblTime.setBounds(350, 318, 50, 25);
        panel.add(lblTime);
        lblTime.setForeground(Color.WHITE);
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        for (JRadioButton rdBtn: radioButtons) {
        	rdBtn.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		updateBtnSent(true);
            	}
            });
		}
        
        btnEnviar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (timer != null && timer.isRunning()) {
        		    timer.stop();
        		}

        		String answer="";
        		for (JRadioButton rdBtn: radioButtons) {
        			if (rdBtn.isSelected()) {
        				answer = rdBtn.getText();
        				break;
        			}
        		}
        		quizController.checkAnswer(answer);
        	}
        });
    }
}