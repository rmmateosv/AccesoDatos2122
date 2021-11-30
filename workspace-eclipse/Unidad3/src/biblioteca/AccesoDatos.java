package biblioteca;

import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class AccesoDatos {
	//Colección
	private Collection coleccion = null;
	private String ruta =
			"xmldb:exist://localhost:8080/exist/xmlrpc/db/biblioteca",
	usuario="admin",
	clave="admin";

	public AccesoDatos() {
		//Conectar con la colección Biblioteca
		try {
			Class driver = 
					Class.forName("org.exist.xmldb.DatabaseImpl");
			//Crear una instancia de la BD
			Database db = (Database) 
					driver.getDeclaredConstructor().newInstance();
			//Registrar la BD
			DatabaseManager.registerDatabase(db);
			
			//Conectar a la colección
			coleccion = DatabaseManager.getCollection(
					ruta,usuario, clave);
			if(coleccion == null) {
				System.err.println("Error, no existe la colección, "
						+ "debes crearla");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Collection getColeccion() {
		return coleccion;
	}

	public void setColeccion(Collection coleccion) {
		this.coleccion = coleccion;
	}
	
	public void cerrar() {
		try {
			coleccion.close();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean crearBiblioteca() {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		return resultado;
	}
	
	
}
