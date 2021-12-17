package cesta;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class AccesoDatos {
	private String nombreFichero;

	public AccesoDatos(String nombreFichero) {
	
		this.nombreFichero = nombreFichero;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public Producto obtenerProducto(int codigoR) {
		// TODO Auto-generated method stub
		Producto resultado = null;
		
		RandomAccessFile fichero = null;
		try {
			 fichero = 
					new RandomAccessFile(nombreFichero, "r");
			while(true) {
				//Leemos codigoR
				int codigoRLeido = fichero.readInt();
				//Comprobar si es el buscado
				if(codigoRLeido==codigoR) {
					resultado =new Producto();
					resultado.setCodigoR(codigoRLeido);
					//Creamos el codigo del producto a vacío para poder concatenar
					//Si no, vale null y no se puede concatenar
					resultado.setCodigoP("");
					for(int i = 0;i<10;i++) {						
						resultado.setCodigoP(resultado.getCodigoP()+fichero.readChar());
					}
					resultado.setNombre("");
					for(int i = 0;i<100;i++) {						
						resultado.setNombre(resultado.getNombre()+fichero.readChar());
					}
					resultado.setCantidad(fichero.readInt());
					resultado.setImporte(fichero.readFloat());
					return resultado;
					
				}
				else {
					//Desplazamos al siguiente codigoR. Saltamos 228 B
					fichero.seek(fichero.getFilePointer()+228);
				}
			}
			
		} 
		catch (EOFException e) {
			// TODO: handle exception
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay productos");
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
	
	
}
