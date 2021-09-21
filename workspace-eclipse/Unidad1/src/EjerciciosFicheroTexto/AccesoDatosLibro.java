package EjerciciosFicheroTexto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
			
			while(fichero.readLine()!=null) {
				//Crear libro con lo datos leídos
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, no existe el fichero de libros");
		} catch (IOException e) {
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
