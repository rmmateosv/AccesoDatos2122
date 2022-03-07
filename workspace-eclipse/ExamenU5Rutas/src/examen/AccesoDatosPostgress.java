package examen;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public Lugar obtenerLugar(String nombre) {
		// TODO Auto-generated method stub
		Lugar resultado = null;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("select codigo, nombre, "
							+ "(info).latitud, (info).longitud "
							+ "from lugar "
							+ "where nombre = ?");
			consulta.setString(1, nombre);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Lugar(r.getInt(1), r.getString(2),
						new Localizacion(r.getInt(3), r.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public Lugar obtenerLugar(int codigo) {
		// TODO Auto-generated method stub
		Lugar resultado = null;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("select codigo, nombre, "
							+ "(info).latitud, (info).longitud "
							+ "from lugar "
							+ "where codigo = ?");
			consulta.setInt(1, codigo);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Lugar(r.getInt(1), r.getString(2),
						new Localizacion(r.getInt(3), r.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearLugar(Lugar l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("insert into lugar "
							+ "values (default,?,(?,?))");
			consulta.setString(1, l.getNombre());
			consulta.setInt(2, l.getInfo().getLatitud());
			consulta.setInt(3, l.getInfo().getLongitud());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Lugar> obtenerLugares() {
		// TODO Auto-generated method stub
		ArrayList<Lugar>  resultado = new ArrayList();
		try {
			Statement consulta = 
					conexion.createStatement();			
			ResultSet r = consulta.executeQuery("select codigo, nombre, "
					+ "(info).latitud, (info).longitud "
					+ "from lugar ");
			while(r.next()) {
				resultado.add(new Lugar(r.getInt(1), r.getString(2),
						new Localizacion(r.getInt(3), r.getInt(4))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Ruta obtenerRuta(String nombre) {
		// TODO Auto-generated method stub
		Ruta resultado = null;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("select * from ruta "
							+ "where nombre = ?");
			consulta.setString(1, nombre);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Ruta();
				resultado.setNombre(r.getString(1));
				resultado.setTiempo(r.getInt(3));
				Array datos = r.getArray(2);
				if(datos!=null) {
					Integer[] lugares = (Integer[]) datos.getArray();
					for(int l:lugares) {
						resultado.getLugares().add(l);
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearRuta(Ruta r) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("insert into ruta "
							+ "values (?,array[]::int[],?)");
					
			consulta.setString(1,r.getNombre());
			consulta.setInt(2,r.getTiempo());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean anadirLugarRuta(Ruta r, Lugar l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("update ruta "
							+ "set lugares = array_cat(lugares,array[?]::int[]) "
							+ "where nombre = ?");			
			consulta.setInt(1,l.getCodigo());
			consulta.setString(2,r.getNombre());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
