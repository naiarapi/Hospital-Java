package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.GestorCitas;
import logic.Paciente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class NewPacienteUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private GestorCitas gCitas = new GestorCitas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPacienteUI frame = new NewPacienteUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewPacienteUI() {
		setResizable(false);
		setTitle("Nuevo paciente - CS Zubiri");
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\\\zor012982b\\DW31\\PROGRAMACION\\ne\\doctor.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 239, 152);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtNombre.setBounds(106, 18, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNombre.setBounds(28, 21, 89, 14);
		contentPane.add(lblNombre);
		
		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.setBackground(new Color(248, 248, 255));
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPaciente();
			}
		});
		btnAdd.setBounds(10, 67, 86, 36);
		contentPane.add(btnAdd);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(248, 248, 255));
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarClick();
			}
		});
		btnCancelar.setBounds(106, 67, 107, 36);
		contentPane.add(btnCancelar);
	}
	
	private void addPaciente(){
		Paciente p= new Paciente();
		String nombre= txtNombre.getText();
		p.setNombre(nombre);
		
		if(txtNombre.getText().length()==0){
			JOptionPane.showMessageDialog(this,"Añada un paciente");
			
		}else{
			String sql= "INSERT INTO pacientes (nombre) VALUES ('"+p.getNombre()+"')";
			gCitas.actualizarBD(sql);
			JOptionPane.showMessageDialog(this,"Paciente añadido,Gracias!");
			cierraVentana();
		}
		
	}
	
	private void btnCancelarClick(){
		cierraVentana();
	}
	
	private void cierraVentana(){
		this.dispose();
		PacientesUI pacientesUI = new PacientesUI();
		pacientesUI.setVisible(true);
		pacientesUI.recargaDatos();
	}
}
