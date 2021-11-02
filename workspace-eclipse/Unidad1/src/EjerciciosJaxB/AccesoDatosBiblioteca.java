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
				JAXBContext contexto = JAXBContext.newInstance(Biblioteca.class);
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
				contexto = JAXBContext.newInstance(Biblioteca.class);
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
				pXML.setLibro(p.getLibro().getTitulo());				
				pXML.setId(contador);
				contador++;
				biblioteca.getPrestamos().add(pXML);				
			}
			resultado = true;
		}
		else {
			System.out.println("Error, ya existe el fichero, no se puede volver a importar");
		}
		
		return resultado;
	}

	public Biblioteca obtenerBiblioteca() {
		// TODO Auto-generated method stub
		return biblioteca;
	}

	public int obtenerIdPrestamo() {
		// TODO Auto-generated method stub
		int resultado = 0;
		if(biblioteca!=null) {
			//El nuevo id, será uno más del último id de préstamo
			//Obtenemos el array list de prestamos
			ArrayList<PrestamoXML> lista = biblioteca.getPrestamos();
			//Obtenemos el último préstamo
			PrestamoXML p = lista.get(lista.size()-1);
			//Sumamos 1 al id del último préstamo
			resultado = p.getId() +1;
		}
		return resultado;
	}

	public boolean crearPrestamo(PrestamoXML p) {
		// TODO Auto-generated method stub		
		biblioteca.getPrestamos().add(p);
		return true;
	}

	public boolean modificarSocio(int id, String nombre) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		if(biblioteca!=null) {
			for(PrestamoXML p:biblioteca.getPrestamos()) {
				//Comprobamos si es el préstamo buscado
				if(p.getId()==id) {
					p.setSocio(nombre);
					return true;
				}
			}
		}
		return resultado;
	}

	public boolean borrarPrestamo(int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		if(biblioteca!=null) {
			for(PrestamoXML p:biblioteca.getPrestamos()) {
				//Comprobamos si es el préstamo buscado
				if(p.getId()==id) {
					biblioteca.getPrestamos().remove(p);
					return true;
				}
			}
		}
		return resultado;
	}

	
	
}
