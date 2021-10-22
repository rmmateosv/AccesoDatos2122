package EjerciciosDOM;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import EjerciciosFicherosObjetos.Prestamo;

public class AccesoDatosBiblioteca {
	private String nombreFichero;
	private Document arbol=null;
	
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
	public AccesoDatosBiblioteca(String nombre) {
		//Si existe el fichero, se carga el árbol con el contenido
		nombreFichero = nombre;
		File fichero = new File(nombreFichero);
		if(fichero.exists()) {
			
			try {
				//Cargamos árbol
				DocumentBuilder db = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				arbol = db.parse(fichero);
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean importar(ArrayList<Prestamo> lPrestamos) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		if(arbol==null) {
			
			try {
				//Creamos árbol vacío
				DocumentBuilder db = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				arbol = db.newDocument();
				//Establecemos versión xml
				arbol.setXmlVersion("1.0");
				//Creamos el nodo raíz
				Element raiz = arbol.createElement("biblioteca");
				//Añadimos el elemento raíz al árbol
				arbol.appendChild(raiz);
				
				//Creamos elemento nombre
				Element nombre = arbol.createElement("nombre");
				//Añadimos nombre a elemento raíz
				raiz.appendChild(nombre);
				Text tNombre = arbol.createTextNode("IES Augustóbriga");
				//Añadimos el texto al nombre
				nombre.appendChild(tNombre);
				
				//Creamos el elemento fecha
				Element fecha = arbol.createElement("fecha");
				raiz.appendChild(fecha);
				Text tFecha = arbol.createTextNode(formato.format(new Date()));
				fecha.appendChild(tFecha);
				
				//Nodo prestamos
				Element prestamos = arbol.createElement("prestamos");
				raiz.appendChild(prestamos);
				
				int id = 1;
				for(Prestamo p: lPrestamos) {
					//Creamos nodos prestamo
					Element prestamo = arbol.createElement("prestamo");
					prestamos.appendChild(prestamo);
					
					//Para simplificar, hacemos un contador
					prestamo.setAttribute("id", String.valueOf(id));
					id++;
					
					//Rellenamos atributo fecha
					prestamo.setAttribute("fecha", formato.format(p.getFechaP()));
					
					
					
					Element socio = arbol.createElement("socio");
					prestamo.appendChild(socio);
					Text tSocio = arbol.createTextNode(p.getSocio().getNombre());
					socio.appendChild(tSocio);
					
					Element libro = arbol.createElement("libro");
					prestamo.appendChild(libro);
					Text tLibro = arbol.createTextNode(p.getLibro().getTitulo());
					libro.appendChild(tLibro);
				}
				
				resultado = true;
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Fichero existe, no puede volver a importar");
		}
		return resultado;
	}

	public void guardarArbol() {
		// TODO Auto-generated method stub
		if(arbol!=null) {			
			try {
				Source fuente = new DOMSource(arbol);
				Result destino = new StreamResult(new File(nombreFichero));
				Transformer t = TransformerFactory.newInstance().newTransformer();
				t.transform(fuente, destino);
			
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else {
			System.out.println("Error, el árbol está vacío, no se puede generar fichero");
		}
	}

	public Biblioteca obtenerBiblioteca() {
		// TODO Auto-generated method stub
		Biblioteca resultado = null;
		if(arbol!=null) {		
			try {
				resultado = new Biblioteca();
				//Nombre de la biblioteca
				NodeList n = arbol.getElementsByTagName("nombre");
				resultado.setNombre(n.item(0).getTextContent());
				//Fecha
				n = arbol.getElementsByTagName("fecha");
				resultado.setFecha(formato.parse(n.item(0).getTextContent()));
				
				//Rellenamos los préstamos
				
				/*
				OPCIÓN 1
				n = arbol.getElementsByTagName("prestamos");
				NodeList prestamos = n.item(0).getChildNodes();*/
				
				//OPCIÓN 2
				NodeList prestamos = arbol.getElementsByTagName("prestamo");
				
				
				for(int i=0;i<prestamos.getLength();i++) {
					PrestamoXML p = new PrestamoXML();
					Element prestamo = (Element) prestamos.item(i); 
					p.setId(Integer.parseInt(prestamo.getAttribute("id")));
					p.setFecha(formato.parse(prestamo.getAttribute("fecha")));
					//Nombre del socio
					p.setSocio(prestamo.getChildNodes().item(0).getChildNodes().item(0).getNodeValue());
					//Título del libro
					p.setTitulo(prestamo.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
					
					//Añadir p a la lista de préstamos de la biblioteca
					resultado.getPrestamos().add(p);
					
				}
				
				
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("No hay datos, debes importar el fichero");
		}
		return resultado;
	}
	
	
}
