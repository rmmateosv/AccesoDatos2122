package bibliotecaDB4O;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;



public class AccesoDatos {
	
	private ObjectContainer conexion = null;
	
	
	
	public AccesoDatos() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();	
		config.common().objectClass(Socio.class).cascadeOnDelete(true);
		
		conexion = Db4oEmbedded.openFile(config,"biblioteca.obj");
	}



	public void cerrar() {
		try {
			conexion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	public ObjectContainer getConexion() {
		return conexion;
	}



	public void setConexion(ObjectContainer conexion) {
		this.conexion = conexion;
	}



	public int obtenerId() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int resultado = 1;
		
		//Recuperar el valor del id a crear
		ObjectSet<Ids> ids = conexion.queryByExample(new Ids());
		Ids objeto;
		if(ids.hasNext()) {
			objeto = ids.next();			
			//Sumar 1 al id 
			objeto.setContador(objeto.getContador()+1);
			
				
		}
		//Si no hay objeto se crea el primero
		else {
			
			//Creamos el objeto id y lo guardamos en la bd
			objeto = new Ids();
			objeto.setContador(1);
			
		}
		//Actualizar el id en la BD		
		conexion.store(objeto);
		resultado = objeto.getContador();
		
		return resultado;
	}



	public boolean crearLibro(Libro lNuevo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.store(lNuevo);
			resultado =  true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.store(s);
			resultado =  true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public Socio obtenerSocio(String dni) {
		// TODO Auto-generated method stub
		Socio resultado  = null;
		try {
			Socio s = new Socio();
			s.setDni(dni);
			
			ObjectSet<Socio> socios = conexion.queryByExample(s);
			if(socios.hasNext()) {
				resultado = socios.next();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}



	public ArrayList<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado  = new ArrayList<Socio>();
		try {
			ObjectSet<Socio> socios = conexion.queryByExample(new Socio());
			while(socios.hasNext()) {
				resultado.add(socios.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultado;
	}



	public ArrayList<Libro> obtenerLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> resultado = new ArrayList<Libro>();
		try {
			ObjectSet<Libro> libros = conexion.queryByExample(new Libro());
			while(libros.hasNext()) {
				resultado.add(libros.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public Libro obtenerLibro(int idLibro) {
		// TODO Auto-generated method stub
		Libro resultado = null;
		try {
			Libro l = new Libro();
			l.setId(idLibro);
			ObjectSet<Libro> libros = conexion.queryByExample(l);
			
			if(libros.hasNext()) {
				resultado=libros.next();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public boolean crearPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Prestamo p = new Prestamo();
			p.setClave(new PrestamoClave(s, l, new Date()));
			p.setDevuelto(false);
			p.setFechaD(new Date(new Date().getTime()+10*24*60*60*1000));
			conexion.store(p);
			//Modificar el nº de ejemplares
			l.setNumEjemplares(l.getNumEjemplares()-1);
			conexion.store(l);
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public ArrayList<Prestamo> obtenerPrestamos(Socio s) {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> resultado = new ArrayList<Prestamo>();
		try {
			Prestamo p = new Prestamo();
			p.setClave(new PrestamoClave(s, null, null));
			ObjectSet<Prestamo> prestamos = conexion.queryByExample(p);
			while(prestamos.hasNext()) {
				resultado.add(prestamos.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public ArrayList<Prestamo> obtenerPrestamos(Socio s, Libro l) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<Prestamo> resultado = new ArrayList<Prestamo>();
				try {
					Prestamo p = new Prestamo();
					p.setClave(new PrestamoClave(s, l, null));
					ObjectSet<Prestamo> prestamos = conexion.queryByExample(p);
					while(prestamos.hasNext()) {
						resultado.add(prestamos.next());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return resultado;
	}

	public boolean devolverPrestamo(Prestamo p) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			p.setDevuelto(true);
			//Sancionar al socio si devuelve después ......
			//Aumentamos nº de ejemplares del libro
			p.getClave().getLibro().setNumEjemplares(
					p.getClave().getLibro().getNumEjemplares()+1);
			//conexion.store(p);
			conexion.delete(p);
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}



	public boolean borrarSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.delete(s);
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

}
