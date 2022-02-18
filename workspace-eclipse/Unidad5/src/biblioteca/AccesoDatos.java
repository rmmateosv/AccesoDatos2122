package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {
	private Connection conexion = null;
	
	private String url="jdbc:postgresql://localhost:5432/biblioteca", 
			us="postgres", 
			pw="admin";

	public AccesoDatos() {
		//Cargamos Driver
		try {
			Class.forName("org.postgresql.Driver");
			//Establecemos conexión
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
