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

	
}
