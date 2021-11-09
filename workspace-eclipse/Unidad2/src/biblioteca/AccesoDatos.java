package biblioteca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import EjerciciosFicheroTexto.Libro;

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
		// Establecemos conexión al servidor
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/?allowMultiQueries=true", usuario,
					clave);

			String texto = cargarScript("biblioteca.sql");
			// Declaramos una sentencia para que se pueda ejecutar
			Statement sentencia = conexion.createStatement();
			// Ejecutamos sentencia
			sentencia.executeUpdate(texto);

			// Devolvemos la conexión a biblioteca
			conexion = DriverManager.getConnection(url, usuario, clave);
			if (conexion == null) {
				System.out.println("Error, no se ha establacido conexión con la BD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String cargarScript(String nombreFichero) {
		// TODO Auto-generated method stub
		String resultado = "";

		// Abrir fichero de texto
		BufferedReader fichero = null;

		try {
			fichero = new BufferedReader(new FileReader(nombreFichero));
			String linea;
			while ((linea = fichero.readLine()) != null) {
				resultado += linea;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public void mostrarMetadatos() {
		// TODO Auto-generated method stub
		if (conexion != null) {
			try {
				DatabaseMetaData datos = conexion.getMetaData();
				System.out.println("SGBD:" + datos.getDatabaseProductName());
				System.out.println("Versión:" + datos.getDatabaseProductVersion());

				ResultSet bds = datos.getCatalogs();
				System.out.println("Bases de datos");
				while (bds.next()) {
					System.out.println("->" + bds.getString(1));

				}

				System.out.println("Tablas de la bd biblioteca");
				ResultSet tablas = datos.getTables("biblioteca", null, null, null);
				while (tablas.next()) {
					System.out.println("->" + tablas.getString(3));
				}

				System.out.println("Campos de la tabla libro");
				Statement sentencia = conexion.createStatement();
				ResultSet r = sentencia.executeQuery("select * from libro");
				ResultSetMetaData datosLibro = r.getMetaData();
				System.out.println("Nº de columnas:" + datosLibro.getColumnCount());
				for (int i = 1; i <= datosLibro.getColumnCount(); i++) {
					System.out.println("->" + datosLibro.getColumnName(i) + ":" + datosLibro.getColumnTypeName(i));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean crearLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		if(conexion!=null) {
			try {				
				PreparedStatement sentencia = conexion.prepareStatement(
						"insert into libro values (?,?,?,?,?)");
				//Rellenamos los parámetros -> ?
				sentencia.setString(1, l.getIsbn());
				sentencia.setString(2, l.getTitulo());
				sentencia.setString(3, l.getAutor());
				sentencia.setDate(4, new Date(l.getFechaLanzamiento().getTime()));
				sentencia.setInt(5, l.getNumEjemplares());
				
				//Ejecutamos
				int numFilas = sentencia.executeUpdate();
				if(numFilas==1) {
					resultado=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public Libro obtenerLibro(String isbn) {
		// TODO Auto-generated method stub
		Libro resultado = null;
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from libro where isbn = ?");
			sentencia.setString(1, isbn);
			
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Libro(r.getString(1),
						r.getString(2),
						r.getString(3),
						r.getDate(4),
						r.getInt(5));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

}
