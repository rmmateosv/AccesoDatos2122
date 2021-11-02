package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {
	private String usuario = "root", 
			clave = "root", 
			url = "jdbc:mysql://localhost:3306/biblioteca";
	
	private Connection conexion = null;

	public AccesoDatos() {
		
		try {
			//Cargamos driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establecer conexión
			conexion = DriverManager.getConnection(url,usuario,clave);
			
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
	
	
}
