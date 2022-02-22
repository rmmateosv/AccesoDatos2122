package biblioteca;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection conexion = null;

	private String url = "jdbc:postgresql://localhost:5432/biblioteca", us = "postgres", pw = "admin";

	public AccesoDatos() {
		// Cargamos Driver
		try {
			Class.forName("org.postgresql.Driver");
			// Establecemos conexión
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
			PreparedStatement consulta = conexion.prepareStatement("insert into socio values (default,?,(?,?,?),?)");
			consulta.setString(1, s.getNombre());
			consulta.setString(2, s.getDireccion().getCalle());
			consulta.setInt(3, s.getDireccion().getNumero());
			consulta.setInt(4, s.getDireccion().getCp());
			consulta.setDate(5, new Date(s.getFechaNac().getTime()));
			int r = consulta.executeUpdate();
			if (r == 1) {
				resultado = true;
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
			ResultSet r = consulta.executeQuery("select id, nombre, (direccion).calle, (direccion).numero, "
					+ "(direccion).cp, fechaNac " + "from socio");
			while (r.next()) {
				Socio s = new Socio(r.getInt(1), r.getString(2),
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
			PreparedStatement consulta = conexion.prepareStatement("select * from libro " + "where isbn = ?");
			consulta.setString(1, isbn);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearRevista(Revista r) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		try {
			PreparedStatement consulta = conexion.prepareStatement("insert into revista " + "values (?,?,?,null,?,?)");
			consulta.setString(1, r.getIsbn());
			consulta.setString(2, r.getTitulo());
			consulta.setInt(3, r.getNumEjem());
			consulta.setString(4, r.getGenero());
			consulta.setDate(5, new Date(r.getFechaP().getTime()));
			int filas = consulta.executeUpdate();
			if (filas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Libro> obtenerLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> resultado = new ArrayList<>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from libro");
			while (r.next()) {
				Libro l = new Libro();
				l.setIsbn(r.getString(1));
				l.setTitulo(r.getString(2));
				l.setNumEjem(r.getInt(3));

				Array datosPrestamos = r.getArray(4);
				// Comprobar si hay préstamos
				if (datosPrestamos != null) {
					String[][] filas = ((String[][]) datosPrestamos.getArray());
					// Pasamos filas a los préstamos del libro
					for (String[] fila : filas) {
						l.getPrestamos().add(fila);
					}
				}

				resultado.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean existeSocio(int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("select * from socio where id = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean registrarPrestamo(String isbn, int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			// Recuperamos el libro para saber si hay o no préstamos
			PreparedStatement consulta = conexion.prepareStatement("select * from libro " + "where isbn = ?");
			consulta.setString(1, isbn);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				// Obtenemos préstamos
				Array p = r.getArray(4);
				if (p == null) {
					// Creamos un nuevo array en el campo préstamos de libro
					consulta = conexion.prepareStatement("update libro " + "set prestamos = array[array[?,?,?]],"
							+ "numEjemplares = numEjemplares -1 " + "where isbn = ?");

				} else {
					// Concatenamos una nueva línea al array en el campo préstamos de libro
					consulta = conexion.prepareStatement(
							"update libro " + "set prestamos = array_cat(prestamos,array[?,?,?]::text[][]),"
									+ " numEjemplares = numEjemplares -1 " + " where isbn = ?");

				}
				java.util.Date fechaP = new java.util.Date();
				// Sumamos a la fecha de préstamos 7 días
				java.util.Date fechaD = new java.util.Date(fechaP.getTime() + 7 * 24 * 60 * 60 * 1000);
				consulta.setString(1, formato.format(fechaP));
				consulta.setString(2, formato.format(fechaD));
				consulta.setString(3, String.valueOf(id));
				consulta.setString(4, isbn);
				int filas = consulta.executeUpdate();
				if (filas == 1) {
					resultado = true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPrestamo(String isbn, int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		// Recuperamos el libro para saber los préstamos
		// Recuperamos el libro para saber si hay o no préstamos
		PreparedStatement consulta;
		try {

			consulta = conexion.prepareStatement("select * from libro " + "where isbn = ?");
			consulta.setString(1, isbn);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				// Iniciamos transacción
				conexion.setAutoCommit(false);
				// Obtenemos préstamos
				Array p = r.getArray(4);
				if (p != null) {
					String[][] filas = (String[][]) p.getArray();
					ArrayList<String[]> pValidos = new ArrayList();
					// Recorremos filas y descartamos el préstamo a borrar
					for (String[] datos : filas) {
						if (!datos[2].equalsIgnoreCase(String.valueOf(id))) {
							pValidos.add(datos);
						}
					}

					if (!pValidos.isEmpty()) {
						// Actualizamos la tabla libros y ponemos como prestamos los
						// préstamos de pValidos
						for (int i = 0; i < pValidos.size(); i++) {
							// Para el primer préstamos creamo un nuevo
							// array con el préstamo
							if (i == 0) {
								consulta = conexion.prepareStatement(
										"update libro " + "set prestamos = array[array[?,?,?]] " + "where isbn = ?");
							}
							// Para el resto, concatenamos cada préstamo al primero
							else {
								consulta = conexion.prepareStatement("update libro " + "set prestamos = "
										+ "array_cat(prestamos,array[?,?,?]::text[][]) " + "where isbn = ?");
							}
							consulta.setString(1, pValidos.get(i)[0]);
							consulta.setString(2, pValidos.get(i)[1]);
							consulta.setString(3, pValidos.get(i)[2]);
							consulta.setString(4, isbn);
							int ok = consulta.executeUpdate();
							if (ok < 1) {
								conexion.rollback();
								return false;
							}

						}
					} else {
						consulta = conexion
								.prepareStatement("update libro " + "set prestamos = null " + "where isbn = ?");
						consulta.setString(1, isbn);
						int ok = consulta.executeUpdate();
						if (ok < 1) {
							conexion.rollback();
							return false;
						}
					}

					conexion.commit();
					resultado = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	public boolean borrarPrestamo2(String isbn, int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean resultado = false;

		// Recuperamos el libro para saber los préstamos
		// Recuperamos el libro para saber si hay o no préstamos
		PreparedStatement consulta;
		try {

			consulta = conexion.prepareStatement("select * from libro " + "where isbn = ?");
			consulta.setString(1, isbn);
			ResultSet r = consulta.executeQuery();
			if (r.next()) {
				
				// Obtenemos préstamos
				Array p = r.getArray(4);
				if (p != null) {
					String[][] filas = (String[][]) p.getArray();
					ArrayList<String[]> pValidos = new ArrayList();
					// Recorremos filas y descartamos el préstamo a borrar
					for (String[] datos : filas) {
						if (!datos[2].equalsIgnoreCase(String.valueOf(id))) {
							pValidos.add(datos);
						}
					}

					if (!pValidos.isEmpty()) {
						// Actualizamos la tabla libros y ponemos como prestamos los
						// préstamos de pValidos
						String textoArray = "";
						for(int i=0;i<pValidos.size();i++) {
							if(i==0) {
								textoArray += "array[";
							}
							textoArray+="array['"+pValidos.get(i)[0]+"',"
											+"'"+pValidos.get(i)[1]+"',"
											+"'"+pValidos.get(i)[2]+"']";
							if(i!=pValidos.size()-1) {
								textoArray+=",";
							}
							else {
								textoArray+="]";
							}
						}
						
						consulta = conexion.prepareStatement(
								"update libro "
								+ "set prestamos = " + textoArray + ""
								+ " where isbn = ?");
						consulta.setString(1, isbn);
						int ok = consulta.executeUpdate();
						if (ok == 1) {
							resultado = true;
						}
						
					} else {
						consulta = conexion
								.prepareStatement("update libro " + "set prestamos = null " + "where isbn = ?");
						consulta.setString(1, isbn);
						int ok = consulta.executeUpdate();
						if (ok == 1) {
							resultado = true;
						}
					}

					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 

		return resultado;
	}
}
