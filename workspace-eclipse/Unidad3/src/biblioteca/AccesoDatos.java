package biblioteca;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

import EjerciciosFicheroTexto.Libro;

public class AccesoDatos {
	//Colección
	private Collection coleccion = null;
	private String ruta =
			"xmldb:exist://localhost:8080/exist/xmlrpc/db/biblioteca",
	usuario="admin",
	clave="admin";
	
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

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
						
		try {
			//Nos conectamos a la colección padre /db;
			Collection padre = 
					DatabaseManager.getCollection(
							"xmldb:exist://localhost:8080/exist/xmlrpc/db",
							usuario, clave);
			if(padre!=null) {
				//Creamos un servicio
				CollectionManagementService servicio = 
						(CollectionManagementService)
						padre.getService("CollectionManagementService", "1.0");
				coleccion = servicio.createCollection("biblioteca");
				if(coleccion!=null) {
					resultado = true;
				}
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
				
		return resultado;
	}

	public boolean crearLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		//Chequeamos si existe el documento libros.xml
		try {
			Resource recurso = coleccion.getResource("libros.xml");
			if(recurso == null) {
				//Crear el documento libros.xml en la coleccion biblioteca
				File fichero = new File("libros.xml");
				recurso = coleccion.createResource("libros.xml", 
						"XMLResource");
				recurso.setContent(fichero);
				coleccion.storeResource(recurso);
			}
			//Insertar el libro - Consulta de inserción XQUERY
			//Creamos servicio de consulta
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update insert "
					+ "<libro isbn ='"+l.getIsbn()+
					"' fechaL='"+formato.format(l.getFechaLanzamiento())+"'>"
					+ "<titulo>"+l.getTitulo()+"</titulo>"
					+ "<autor>"+l.getAutor()+"</autor>"
					+ "<numEjem>"+l.getNumEjemplares()+"</numEjem>"
					+ "</libro> into /libros");
			resultado = true;
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarLibros() {
		// TODO Auto-generated method stub
		try {
			XPathQueryService consulta = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			//Ejecutamos consulta XPATH
			ResourceSet r = consulta.query("/libros/libro");
			//Obtenemos los nodos devueltos en la consulta
			ResourceIterator libros = r.getIterator();
			//Recorremos cada nodo para pintarlo
			while(libros.hasMoreResources()) {
				//Pintamos el nodo				
				System.out.println(libros.nextResource().getContent());
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void mostrarLibro(String isbn) {
		// TODO Auto-generated method stub
		try {
			XPathQueryService consulta = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			//Ejecutamos consulta XPATH
			ResourceSet r = 
			consulta.query("/libros/libro[@isbn='"+isbn+"']");
			//Obtenemos los nodos devueltos en la consulta
			ResourceIterator libros = r.getIterator();
			//Recorremos cada nodo para pintarlo
			if(libros.hasMoreResources()) {
				//Pintamos el nodo				
				System.out.println(libros.nextResource().getContent());
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean crearSocio(String nif, String nombre) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			//Chequear si existe socios.xml
			Resource r = coleccion.getResource("socios.xml");
			if(r==null) {
				//Crear socios.xml a partir del fichero que 
				//hay en el proyecto y que tiene sólo el nodo raíz
				File f = new File("socios.xml");
				r = coleccion.createResource("socios.xml", "XMLResource");
				//Asignamos el contenido del objeto File al recuros
				r.setContent(f);
				//Alamacenamos el recurso en la colección
				coleccion.storeResource(r);				
			}
			
			//Insertar el nuevo socio
			XPathQueryService consulta = 
					(XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
}
