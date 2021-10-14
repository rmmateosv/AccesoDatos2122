package EjerciciosFicherosAleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;

public class AccesoDatosEmpleado {
	
	//TAMAÑO DE REGISTRO: 215B	
	private String nombreFichero;
	
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public AccesoDatosEmpleado(String nombreFichero) {
	
		this.nombreFichero = nombreFichero;
	}

	public int obtenerCodigo() {
		// TODO Auto-generated method stub
		int resultado = 0; //si error, devuelve 0.
		
		RandomAccessFile fichero = null;
		
		//Abrimos el fichero para leer
		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			resultado = 1;
		}
		return resultado;
	}
	
	
}
