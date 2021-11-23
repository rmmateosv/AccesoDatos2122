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

	public boolean existeUsuario(String pUs) {
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
	public boolean existeAlumno(String pUs) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * "
							+ "from usuario where usuario = ? and "
							+ "perfil = 'Alumno'");
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

	public ArrayList<String> obtenerAlumnos(String patron) {
		// TODO Auto-generated method stub
		ArrayList<String> resultado = new ArrayList<>();
		 
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from usuario "
							+ "where usuario like ? and "
							+ "perfil = 'Alumno'");
			sentencia.setString(1, "%"+patron+"%");
			ResultSet r = sentencia.executeQuery();
			while(r.next()) {
				resultado.add(r.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public boolean borrarAlumno(String alumno) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			//Iniciamos transacción
			conexion.setAutoCommit(false);
			//Borramos matrículas sin comprobar si tiene
			PreparedStatement sentencia = 
					conexion.prepareStatement("delete from matricula "
							+ "where alumno = ?");
			sentencia.setString(1, alumno);
			int filas = sentencia.executeUpdate();
			if(filas >= 0) {
				//Borramos alumnos
				sentencia = 
						conexion.prepareStatement("delete from usuario "
								+ "where usuario = ?");
				sentencia.setString(1, alumno);
				filas = sentencia.executeUpdate();
				if(filas==1) {
					conexion.commit();
					conexion.setAutoCommit(true);
					resultado = true;
				}
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public boolean crearCurso(Curso c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("insert into curso "
							+ "values (null, ?, ?, ?)", 
							Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, c.getNombre());
			sentencia.setInt(2, c.getHoras());
			sentencia.setString(3, c.getNivel());
			
			int filas = sentencia.executeUpdate();
			if(filas == 1) {
				resultado=true;
				//Obtengo el autonumérico que se ha asignado al curso
				ResultSet autoNum = sentencia.getGeneratedKeys();
				if(autoNum.next()) {
					c.setId(autoNum.getInt(1));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Curso> obtenerCursos() {
		// TODO Auto-generated method stub
		ArrayList<Curso> resultado = new ArrayList<>();
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = 
					sentencia.executeQuery("select * from curso");
			while(r.next()) {
				Curso c = new Curso(r.getInt(1), 
						r.getString(2), 
						r.getInt(3),
						r.getString(4));
				resultado.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Curso obtenerCurso(int id) {
		// TODO Auto-generated method stub
		Curso resultado = null;
		try {
			PreparedStatement sentencia;			
				sentencia = conexion.prepareStatement(
						"select * from curso "
						+ "where id = ?");
			
			sentencia.setInt(1, id);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Curso(r.getInt(1), 
						r.getString(2), 
						r.getInt(3),
						r.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarCurso(Curso c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"update curso set "
					+ "nombre = ?, "
					+ "horas = ?, "
					+ "nivel = ? "
					+ "where id = ?");
			sentencia.setString(1, c.getNombre());
			sentencia.setInt(2, c.getHoras());
			sentencia.setString(3, c.getNivel());
			sentencia.setInt(4, c.getId());
			
			int filas = sentencia.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
