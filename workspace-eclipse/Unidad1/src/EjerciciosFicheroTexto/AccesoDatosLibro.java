package EjerciciosFicheroTexto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccesoDatosLibro {
	private String nombreFichero;

	public AccesoDatosLibro(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public ArrayList<Libro> obtenerLibros() {
		// TODO Auto-generated method stub
		ArrayList<Libro> resultado = new ArrayList<>();
		
		//Declaramos el flujo que va a menejar el fichero		
		BufferedReader fichero = null;
		try {
			//Abrimos el fichero para leer de él
			fichero = new BufferedReader(new FileReader(nombreFichero));
			
			//Leer los datos del fichero
			String linea;
			while((linea=fichero.readLine())!=null) {
				//Dividimos la línea por ; para obtener los campos
				//separados
				String[] campos = linea.split(";");
				
				//Pasamos la fecha de String a objeto Date
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = formato.parse(campos[3]);
				
				//Crear libro con lo datos leídos
				Libro l = new Libro(campos[0], campos[1], campos[2], 
						fecha, Integer.parseInt(campos[4]));
				
				//Añadimos el libro al arraylist resultado
				resultado.add(l);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, no existe el fichero de libros");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			//Cerrar el fichero
			try {
				if(fichero !=null) {
					fichero.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	
}
