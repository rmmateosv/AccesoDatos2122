package Tareas;



import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

public class AccesoDatos {
	String url="xmldb:exist://localhost:8080/exist/xmlrpc/db/Tareas", 
			usuario="admin", clave="admin";
	
	private Collection coleccion=null;

	public Collection getColeccion() {
		return coleccion;
	}

	public void setColeccion(Collection coleccion) {
		this.coleccion = coleccion;
	}

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
					coleccion = servicio.createCollection("Tareas");
					
					//Subir Ficheros XML
					File fichero = new File("departamentos.xml");
					Resource recurso = coleccion.createResource(
							fichero.getName(), "XMLResource");
					recurso.setContent(fichero);
					coleccion.storeResource(recurso);
					
					fichero = new File("tareas.xml");
					recurso = coleccion.createResource(
							fichero.getName(), "XMLResource");
					recurso.setContent(fichero);
					coleccion.storeResource(recurso);
					
					fichero = new File("empleados.xml");
				    recurso = coleccion.createResource(
							fichero.getName(), "XMLResource");
					recurso.setContent(fichero);
					coleccion.storeResource(recurso);
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
		
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		try {
			coleccion.close();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarDepartamentos() {
		// TODO Auto-generated method stub
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("/departamentos/departamento");
			ResourceIterator i = r.getIterator();
			while(i.hasMoreResources()) {
				System.out.println(i.nextResource().getContent());
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existeDpto(String dpto) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = 
			servicio.query("/departamentos[departamento='"+dpto+"']");
			ResourceIterator i = r.getIterator();
			if(i.hasMoreResources()) {
				resultado = true;
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean altaEmpleado(String dpto, String nombre, Float salario, String fecha) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		int id = obtenerIdEmpleado();
		
		//Consulta de insert
		try {
			XPathQueryService servicio =
					(XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update insert "
					+ "<empleado id='"+id+"' departamento = '"+dpto+"'>"
					+ "<nombre>"+nombre+"</nombre>"
					+ "<fecha_contratacion>"+fecha+"</fecha_contratacion>"
					+ "<salario>"+salario+"</salario>"
					+ "</empleado>"
					+ "into /empleados");
			resultado = true;
			
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	private int obtenerIdEmpleado() {
		// TODO Auto-generated method stub
		int id = 1;
		//Hacemos una consulta para obtener el id
		try {
			XPathQueryService servicio =
					(XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("data(/empleados/empleado/@id)[last()]");
			ResourceIterator i = r.getIterator();
			if(i.hasMoreResources()) {
				id = Integer.parseInt(i.nextResource().getContent().toString()) + 1;
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
}
