package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

import db.GestorBDSQlite;
import db.IGestorBD;



public class GestorCitas implements IGestorCitas {
	
	IGestorBD gbd = new GestorBDSQlite();

	
	
	@Override
	public DefaultListModel<Paciente> getPaciente() {
		
		String sql= "SELECT * FROM pacientes";
		//conjunto de registros (ResultSet)
		ResultSet rs=gbd.executeSql(sql);
		DefaultListModel<Paciente> list=new DefaultListModel<Paciente>();
		
		try {
			
			while(rs.next()){
				//crear un PACIENTE
				Paciente p = new Paciente();
				//darle los datos
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setFconsulta(rs.getString("fconsulta"));
				p.setFultimaconsulta(rs.getString("fultimaconsulta"));
				p.setNumVisitas(rs.getInt("numvisitas"));
				//añadirlo al DefaultListModel
				list.addElement(p);
							
			}
			
			//devolver el DefaultListModel
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public void actualizarBD(String sql) {
		// TODO Auto-generated method stub
		gbd.updateSql(sql);
	}

	
	
	
	

}
