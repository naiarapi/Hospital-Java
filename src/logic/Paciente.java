package logic;

import java.util.Date;
import java.sql.*;

public class Paciente {

	private int id;
	private String nombre;
	private String fconsulta;
	private String fultimaconsulta;
	private int numvisitas;
	
	/*
	 * Getters y setters
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getFultimaconsulta() {
		return fultimaconsulta;
	}
	public void setFultimaconsulta(String fultimaconsulta) {
		this.fultimaconsulta = fultimaconsulta;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFconsulta() {
		return fconsulta;
	}
	public void setFconsulta(String fconsulta) {
		this.fconsulta = fconsulta;
	}
	public int getNumVisitas() {
		return numvisitas;
	}
	public void setNumVisitas(int numVisitas) {
		this.numvisitas = numVisitas;
	}
	
	public String toString(){
		return nombre;
	}
	
	
	
}
