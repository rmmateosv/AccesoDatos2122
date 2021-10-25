package EjerciciosJaxB;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import EjerciciosFicherosObjetos.Prestamo;

public class AccesoDatosBiblioteca {
	private String nombreFichero;
	private Biblioteca biblioteca;

	public AccesoDatosBiblioteca(String nombreFichero) {
		super();
		this.nombreFichero = nombreFichero;
		biblioteca = unmarshal();
	}
	
	public void marshal() {
		try {
			if(biblioteca!=null) {
				JAXBContext contexto = JAXBContext.newInstance("Biblioteca.class");
				Marshaller marshal = contexto.createMarshaller();
				marshal.marshal(biblioteca, new File(nombreFichero));
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public Biblioteca unmarshal() {
		Biblioteca resultado = null;
		File fichero = new File(nombreFichero);
		if(fichero.exists()) {
			JAXBContext contexto;
			try {
				contexto = JAXBContext.newInstance("Biblioteca.class");
				Unmarshaller unmarshal = contexto.createUnmarshaller();
				resultado = (Biblioteca) unmarshal.unmarshal(fichero);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return resultado;
	}

	public boolean importar(ArrayList<Prestamo> prestamos, String nombre, Date fecha) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		if(biblioteca==null) {
			biblioteca = new Biblioteca();
			biblioteca.setNombre(nombre);
			biblioteca.setFecha(fecha);
			int contador = 1;
			for(Prestamo p : prestamos) {
				PrestamoXML pXML = new PrestamoXML();
				pXML.setSocio(p.getSocio().getNombre());
				pXML.setFecha(p.getFechaP());
				pXML.setTitulo(p.getLibro().getTitulo());				
				pXML.setId(contador);
				contador++;
				biblioteca.getPrestamos().add(pXML);
			}
		}
		else {
			System.out.println("Error, ya existe el fichero, no se puede volver a importar");
		}
		
		return resultado;
	}
	
}
