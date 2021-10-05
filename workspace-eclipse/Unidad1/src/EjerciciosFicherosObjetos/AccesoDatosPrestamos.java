package EjerciciosFicherosObjetos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import EjerciciosFicheroBinarios.Socio;
import EjerciciosFicheroTexto.Libro;

public class AccesoDatosPrestamos {
	private String nombreFichero;

	public AccesoDatosPrestamos(String nombreFichero) {
		
		this.nombreFichero = nombreFichero;
	}

	public boolean crearPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectOutputStream fichero = null;
		
		try {
			//Abrimos fichero de objeto. Si no existe se crea. Se deben añadir datos
			fichero = new ObjectOutputStream(new FileOutputStream(nombreFichero,true));
			
			//Creamos el préstamo
			Prestamo p = new Prestamo();
			p.setSocio(s);
			p.setLibro(l);
			p.setFechaP(new Date());
			//Calculamos la fecha de devolución
			Calendar c = Calendar.getInstance();
			c.setTime(p.getFechaP());
			c.add(Calendar.DATE, 15);			
			p.setFechaD(c.getTime());
			
			p.setDevuelto(false);
			
			//Escribimos el préstamo en el fichero
			fichero.writeObject(p);
			
			resultado = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public ArrayList<Prestamo> obtenerPrestamos() {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> resultado = new ArrayList<Prestamo>();
		
		ObjectInputStream fichero = null;
		
		try {
			fichero = new ObjectInputStream(new FileInputStream(nombreFichero));
			
			//Recorremos el fichero
			while(true) {
				//Leemos un objeto préstamo
				Prestamo p = (Prestamo) fichero.readObject();
				
				//Añadimos el préstamo al resultado
				resultado.add(p);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return resultado;
	}
	
	
	
	
}
