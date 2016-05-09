package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.GestorCitas;
import logic.IGestorCitas;
import logic.Paciente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Font;

public class PacientesUI extends JFrame {
	
	private GestorCitas gCitas = new GestorCitas();
	private NewPacienteUI newPacienteUI = new NewPacienteUI();

	private JPanel contentPane;
	private JList listPacientes;
	private JTextField txtNumVisitas;
	private JTextField txtfconsulta;
	private JLabel lblUltimaConsulta;
	private JTextField txtUltima;
	private JButton btnEliminar;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacientesUI frame = new PacientesUI();
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
	public PacientesUI() {
		setResizable(false);
		setTitle("Gesti\u00F3n de consultas - CS Zubiri");
		setForeground(new Color(255, 250, 240));
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\\\zor012982b\\DW31\\PROGRAMACION\\ne\\doctor.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNVisitas = new JLabel("N\u00BA Visitas:");
		lblNVisitas.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNVisitas.setBounds(197, 124, 96, 14);
		contentPane.add(lblNVisitas);
		
		txtNumVisitas = new JTextField();
		txtNumVisitas.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtNumVisitas.setBounds(197, 140, 86, 20);
		txtNumVisitas.setEditable(false);
		txtNumVisitas.setColumns(10);
		contentPane.add(txtNumVisitas);
		
		JLabel lblFechaConsulta = new JLabel("Fecha Consulta");
		lblFechaConsulta.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblFechaConsulta.setBounds(195, 12, 112, 14);
		contentPane.add(lblFechaConsulta);
		
		txtfconsulta = new JTextField();
		txtfconsulta.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtfconsulta.setBounds(197, 37, 86, 20);
		contentPane.add(txtfconsulta);
		txtfconsulta.setColumns(10);
		
		JButton btnAnadir = new JButton("A\u00F1adir consulta");
		btnAnadir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAnadir.setBackground(new Color(248, 248, 255));
		btnAnadir.setBounds(293, 29, 133, 36);
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddConsulta();
			}
		});
		contentPane.add(btnAnadir);
		
		lblUltimaConsulta = new JLabel("Ultima Consulta");
		lblUltimaConsulta.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblUltimaConsulta.setBounds(197, 68, 112, 14);
		contentPane.add(lblUltimaConsulta);
		
		txtUltima = new JTextField();
		txtUltima.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtUltima.setBounds(197, 93, 86, 20);
		txtUltima.setColumns(10);
		contentPane.add(txtUltima);
		
		JButton btnNew = new JButton("Nuevo paciente");
		btnNew.setBackground(new Color(248, 248, 255));
		btnNew.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNew.setBounds(242, 170, 133, 36);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddPaciente();
			}
		});
		contentPane.add(btnNew);
		
		JButton btnModificar = new JButton("Modificar fecha");
		btnModificar.setBackground(new Color(248, 248, 255));
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnModificar.setBounds(293, 85, 133, 36);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModificarClick();
			}
		});
		contentPane.add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 12, 167, 239);
		contentPane.add(scrollPane);
		
		listPacientes = new JList();
		listPacientes.setFont(new Font("Verdana", Font.PLAIN, 11));
		listPacientes.setBackground(new Color(255, 250, 240));
		scrollPane.setViewportView(listPacientes);
		listPacientes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mostrarDatos();
			}
		});
		listPacientes.setModel(gCitas.getPaciente());
		listPacientes.setLayoutOrientation(JList.VERTICAL);
		
		btnEliminar = new JButton();
		btnEliminar.setBackground(new Color(248, 248, 255));
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarClick();
			}
		});
		btnEliminar.setBounds(197, 217, 227, 36);
		contentPane.add(btnEliminar);
	}
	
	// Muestra los datos del paciente cuando se selecciona
	private void mostrarDatos(){
		if(listPacientes.getSelectedValue() == null){
			// establece el paciente seleccionado por defecto
			listPacientes.setSelectedIndex(0);
		} else {
			// obtiene el paciente seleccionado
			Paciente p = (Paciente) listPacientes.getSelectedValue();
			btnEliminar.setText("Eliminar " + p.getNombre());
			// asignamos los datos a cada campo
			txtUltima.setText(p.getFultimaconsulta());
			txtNumVisitas.setText(Integer.toString(p.getNumVisitas()));
		}
	}
	
	// Inserta una nueva consulta en el paciente seleccionado
	private void btnAddConsulta(){
		// obtiene el paciente seleccionado
		Paciente p = (Paciente) listPacientes.getSelectedValue();
		
		try {
			// Consulta para actualizar la BD
			String sql = "UPDATE pacientes SET numvisitas = numvisitas+1, fultimaconsulta = '" + txtfconsulta.getText() 
						+ "' WHERE nombre = '"+ p.getNombre() + "'";			
			if(txtfconsulta.getText().length() == 0){
				JOptionPane.showMessageDialog(this,"Introduce una fecha");	
			}else{
				gCitas.actualizarBD(sql);
				JOptionPane.showMessageDialog(this,"Fecha Introducida. Gracias!");
				txtUltima.setText(p.getFultimaconsulta());
				// recarga los datos
				recargaDatos();
			}
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(this,"Seleccione un paciente");
		}
	}
	
	private void btnAddPaciente(){
		// abre la ventana
		newPacienteUI.setVisible(true);
		this.setVisible(false);
	}
	
	private void btnModificarClick(){
		Paciente p = (Paciente) listPacientes.getSelectedValue();
		
		try {
			// Consulta para actualizar la BD
			String sql = "UPDATE pacientes SET fultimaconsulta = '" + txtUltima.getText() 
						+ "' WHERE nombre = '"+ p.getNombre() + "'";			
			if(txtUltima.getText().equals(p.getFultimaconsulta())){
				JOptionPane.showMessageDialog(this,"Introduce una nueva fecha");	
			}else{
				gCitas.actualizarBD(sql);
				JOptionPane.showMessageDialog(this,"Fecha Modificada. Gracias!");
				// recarga los datos
				recargaDatos();
			}
			txtUltima.setText(p.getFultimaconsulta());
			
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(this,"Seleccione un paciente");
		}
	}
	
	public void recargaDatos(){
		// vacía el campo de texto de la fecha introducida
		txtfconsulta.setText("");
		// actualiza la lista de pacientes
		listPacientes.setModel(gCitas.getPaciente());
		// establece el paciente seleccionado por defecto
		listPacientes.setSelectedIndex(0);
	}
	
	public void btnEliminarClick(){
		Paciente p = (Paciente) listPacientes.getSelectedValue();
		
		try {
			String sql = "DELETE FROM pacientes WHERE nombre = '" + p.getNombre() + "'";
			
			int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar a " + p.getNombre() + "?", "Eliminar Paciente", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				gCitas.actualizarBD(sql);
				recargaDatos();
			}			
		} catch(NullPointerException e){
			JOptionPane.showMessageDialog(this,"Seleccione un paciente");
		}
	}
}
