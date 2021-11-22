package cursos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection conexion = null;
	private String usuario = "root",
			clave="root",
			url="jdbc:mysql://localhost:3306/cursos";
	
	public AccesoDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, clave);
			
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

	public String login(String pUs, String pClave) {
		// TODO Auto-generated method stub
		String resultado = null;
		
		try {
			CallableStatement funcion = conexion.prepareCall(
					"{? = call login(?,?)}");
			funcion.setString(2, pUs);
			funcion.setString(3, pClave);
			//Registrar el tipo del parámetro de salida
			funcion.registerOutParameter(1, Types.VARCHAR);
			funcion.executeUpdate();
			//Recuperamos el valor devuelto por la función
			resultado = funcion.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean existeAlumno(String pUs) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * "
							+ "from usuario where usuario = ?");
			sentencia.setString(1, pUs);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlumno(String pUs) {
		// TODO Auto-generated method stub
		boolean resultado= false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("insert into usuario "
							+ "values(?,sha2(?,0),'Alumno')");
			sentencia.setString(1, pUs);
			sentencia.setString(2, pUs);
			
			int filas = sentencia.executeUpdate();
			if(filas == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<String> obtenerAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<String> resultado = new ArrayList<>();
		 
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery("select * from usuario "
					+ "where perfil = 'Alumno'");
			while(r.next()) {
				resultado.add(r.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
}
