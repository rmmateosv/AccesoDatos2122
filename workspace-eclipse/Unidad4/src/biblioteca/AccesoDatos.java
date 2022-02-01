package biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AccesoDatos {
	private EntityManager conexion = null;

	public AccesoDatos() {
		try {
			conexion = Persistence.createEntityManagerFactory("UPBiblioteca").createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void cerrar() {
		try {
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public EntityManager getConexion() {
		return conexion;
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}

	public Libro obtenerLibro(String isbn) {
		// TODO Auto-generated method stub
		Libro resultado = null;

		try {
			// Hacemos una consulta JPQL para encontrar el libro por ISBN
			// No podemos usar el método find, porque ISBN no es clave primaria
			Query consulta = conexion.createQuery("from Libro " + "where isbn = ?1");
			consulta.setParameter(1, isbn);
			List<Libro> registros = consulta.getResultList();
			if (registros.size() > 0) {
				resultado = registros.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return resultado;
	}

	public boolean crearLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			// Iniciar transacción
			conexion.getTransaction().begin();
			// Crear un registro libro
			conexion.persist(l);
			// Finalizo con commit
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return resultado;
	}

	public List<Libro> obtenerLibros() {
		// TODO Auto-generated method stub

		List<Libro> resultado = new ArrayList<Libro>();
		try {
			Query consulta = conexion.createQuery("from Libro");
			resultado = consulta.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Socio obtenerSocio(String dni) {
		// TODO Auto-generated method stub
		Socio resultado = null;
		try {
			// Para obtener un registro a partir de su clave primaria,
			// usamos el método find
			resultado = conexion.find(Socio.class, dni);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();
			conexion.persist(s);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;

		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		List<Socio> resultado = new ArrayList<Socio>();
		try {
			Query consulta = conexion.createQuery("from Socio");
			resultado = consulta.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public String registrarPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		String resultado = null;

		try {
			// Chequeamos que el socio esté activo
			if (s.isActivo()) {
				// Comprobamos si no está sancionado
				if (s.getFechaSancion()== null || s.getFechaSancion().getTime() < new Date().getTime()) {
					// Chequeamos que hay ejemplares
					if(l.getNumEjemplares()>0) {
					// Chequeamos que el socio
					// no tiene más de dos préstamos sin devolver
						int numPresPte = 0;
						for(Prestamo p:s.getPrestamos()) {
							if(!p.isDevuelto()) {
								numPresPte++;
							}
						}
						if(numPresPte<2) {
							Calendar c = Calendar.getInstance();
							c.setTime(new Date());
							c.add(Calendar.DAY_OF_YEAR,15);
							Prestamo p = 
							new Prestamo(new PrestamoClave(s,l, new Date()),
									c.getTime(), false);
							//Actualizamos el nº de libros
							l.setNumEjemplares(l.getNumEjemplares()-1);
							//Creamos el préstamo y actualizamos el nº de ejemplares
							conexion.getTransaction().begin();
							conexion.persist(p);
							conexion.getTransaction().commit();
							conexion.clear();	
							resultado = "Préstamo registrado";
						}
						else {
							resultado = "El socio tiene 2 o más préstamos sin devolver";
						}
					}
					else {
						resultado = "Error, no hay ejemplares para prestar";
					}
				} else {
					resultado = "Error, el socio está sancionado";
				}
			} else {
				resultado = "Error, socio no está activo";
			}

		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Prestamo> obtenerPrestamos() {
		// TODO Auto-generated method stub
		List<Prestamo> resultado = new ArrayList<Prestamo>();
		try {
			Query consulta = conexion.createQuery("from Prestamo");
			resultado = consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

}
