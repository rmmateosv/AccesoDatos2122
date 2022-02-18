package biblioteca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into socio values (default,?,(?,?,?),?)");
			consulta.setString(1, s.getNombre());
			consulta.setString(2, s.getDireccion().getCalle());
			consulta.setInt(3, s.getDireccion().getNumero());
			consulta.setInt(4, s.getDireccion().getCp());
			consulta.setDate(5, new Date(s.getFechaNac().getTime()));
			int r = consulta.executeUpdate();
			if(r==1) {
				resultado =true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado = new ArrayList<>();
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery(
					"select id, nombre, (direccion).calle, (direccion).numero, "
					+ "(direccion).cp, fechaNac "
					+ "from socio");
			while(r.next()) {
				Socio s = new Socio(r.getInt(1),
						r.getString(2),
						new Direccion(r.getString(3), r.getInt(4), r.getInt(5)),
						new java.util.Date(r.getDate(6).getTime()));
				resultado.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean existePublicacion(String isbn) {
		// TODO Auto-generated method stub
		
		boolean resultado = false;
			try {
				PreparedStatement consulta = conexion.prepareStatement(
						"select * from libro "
						+ "where isbn = ?");
				consulta.setString(1, isbn);
				ResultSet r = consulta.executeQuery();
				if(r.next()) {
					resultado = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return resultado;
	}
}
