package Tareas;

import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class AccesoDatos {
	String url="xmldb:exist://localhost:8080/exist/xmlrpc/db/Tareas", 
			usuario="admin", clave="admin";
	
	Collection coleccion=null;

	public AccesoDatos() {
		try {
			Class driver = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database db = (Database) 
					driver.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(db);
			
			//Nos conectamos a la colección Tareas
			coleccion = DatabaseManager.getCollection(url,usuario, clave);
			if(coleccion == null) {
				//Hay que crear la colección Tareas
				//Nos conectamos a la colección db
				coleccion = DatabaseManager.getCollection(
						"xmldb:exist://localhost:8080/exist/xmlrpc/db",
						usuario, clave);
				if(coleccion!=null) {
					//Creamos colección Tareas
					CollectionManagementService servicio =
							(CollectionManagementService) 
							coleccion.getService("CollectionManagementService", "1.0");
				}
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
	
	
}
