package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBDSQlite implements IGestorBD {
	
	private static final String RUTA_BD=".\\pacientes.sqlite";
	
	private	Connection cn=null;
	private	Statement st=null;
	
	//constructora
	public GestorBDSQlite(){
		//cargar driver y conectar bd
		try
		{
			//cargar el driver jdbc-sqlite
		    Class.forName("org.sqlite.JDBC");
				//comprobar si existe la bd
				File f = new File(RUTA_BD);
				if(f.exists()){
					//conectarnos la la bd
					cn =DriverManager.getConnection("jdbc:sqlite:"+RUTA_BD);
					st = cn.createStatement();
					
				}else{
					//conectarnos la la bd
					cn =DriverManager.getConnection("jdbc:sqlite:"+RUTA_BD);
					st = cn.createStatement();
	
				}
				
				
			}
			catch (ClassNotFoundException ex){
				System.out.println("sqlite driver not found");
			}
			catch(SQLException ex1){
				System.out.println("Error abriendo bd");
			}
			
		}
		
	
	

	@Override
	public ResultSet executeSql(String sql) {			
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}

	
	@Override
	public void updateSql(String sql) {
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}

}


