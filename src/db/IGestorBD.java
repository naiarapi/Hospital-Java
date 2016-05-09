package db;

import java.sql.ResultSet;

public interface IGestorBD {
	
	//ejecutar sentencias 
	public ResultSet executeSql(String sql);
	
	//actualizar la sql
	public void updateSql(String sql);

}
