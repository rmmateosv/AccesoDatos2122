package biblioteca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoDatos {
	private String usuario = "root", clave = "root", url = "jdbc:mysql://localhost:3306/biblioteca";

	private Connection conexion = null;

	public AccesoDatos() {

		try {
			// Cargamos driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecer conexión
			conexion = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (e.getErrorCode() == 1049) {
				System.out.println("Aún no se ha creado la BD");
			} else {
				e.printStackTrace();
			}
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

	public void ejecutarScript() {
		// TODO Auto-generated method stub
		//Establecemos conexión al servidor
		try {
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/?allowMultiQueries=true", usuario, clave);
			
			String texto = cargarScript("biblioteca.sql");
			//Declaramos una sentencia para que se pueda ejecutar
			Statement sentencia = conexion.createStatement();
			//Ejecutamos sentencia
			sentencia.executeUpdate(texto);
			
			//Devolvemos la conexión a biblioteca
			conexion = DriverManager.getConnection(url, usuario, clave);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String cargarScript(String nombreFichero) {
		// TODO Auto-generated method stub
		String resultado = "";
		
		//Abrir fichero de texto
		BufferedReader fichero = null;
		
		try {
			fichero = new BufferedReader(new FileReader(nombreFichero));
			String linea;
			while((linea = fichero.readLine())!=null) {
				resultado += linea;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

}
