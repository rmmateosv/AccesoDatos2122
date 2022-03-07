package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatosPostgress {
	private Connection conexion=null;
	private String url = "jdbc:postgresql://localhost:5432/rutas", 
			us = "postgres", 
			pw = "admin";

	public AccesoDatosPostgress() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url, us, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
