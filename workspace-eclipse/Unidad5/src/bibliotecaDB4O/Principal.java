package bibliotecaDB4O;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;






public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");				
				System.out.println("1-Importar LibroS");
		

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				
				case 1: {
					importarLibros();
					break;
				}
				case 2: {
				
					break;
				}
						

				}

			} while (opcion != 0);
			ad.cerrar();
		} else {
			System.out.println("Error, no se ha podido conectar con la BD");
		}
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		biblioteca.AccesoDatos adP = new biblioteca.AccesoDatos();
		ArrayList<biblioteca.Libro> libros = adP.obtenerLibros();
		for(biblioteca.Libro l:libros) {
			Libro lNuevo = 
			new Libro(l.getIsbn(), l.getTitulo(), l.getNumEjem());
			//Rellenamos el id simulando un autonumérico
			lNuevo.setId(ad.obtenerId());
			lNuevo.setIsbn(l.getIsbn());
			lNuevo.setTitulo(l.getTitulo());
			lNuevo.setNumEjemplares(l.getNumEjem());
			if(!ad.crearLibro(lNuevo)) {
				System.out.println("Error al importar el libro " + 
						lNuevo.getIsbn());
			}
		}
	}



	



}
