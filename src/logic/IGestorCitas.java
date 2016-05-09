package logic;



import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;


public interface IGestorCitas {
	
	public DefaultListModel getPaciente();
	public void actualizarBD(String sql);
	

}
