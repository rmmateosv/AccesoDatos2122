package EjerciciosDOM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AccesoDatosBiblioteca {
	private String nombreFichero;
	private Document arbol=null;
	
	public AccesoDatosBiblioteca(String nombre) {
		//Si existe el fichero, se carga el árbol con el contenido
		nombreFichero = nombre;
		File fichero = new File(nombreFichero);
		if(fichero.exists()) {
			//Cargamos árbol
			try {
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
	
	
}
