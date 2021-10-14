package EjerciciosFicherosObjetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import EjerciciosFicheroBinarios.AccesoDatosSocios;
import EjerciciosFicheroBinarios.Socio;
import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;


public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosPrestamos datosP = new AccesoDatosPrestamos("prestamos.obj");
	static AccesoDatosLibro datosL = new AccesoDatosLibro("libros.txt");
	static AccesoDatosSocios datosS = new AccesoDatosSocios("socios.bin");
	
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Préstamo");
			System.out.println("2-Crear Préstamo !!");
			System.out.println("3-Devolver Préstamo !!");
			System.out.println("4-Borrar Préstamo");
			System.out.println("5-Mostrar Préstamos de un socio");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					mostrarPrestamos();
					break;
				}
				case 2: {
					crearPrestamo();
					break;
				}
				case 3: {
					devolverPrestamo();
					break;
				}
				case 4: {
					borrarPrestamo();
					break;
				}
				case 5: {
					mostrarPrestamosSocio();
					break;
				}
				case 6: {
					
					break;
				}
				
			}
			
		} while (opcion != 0);
		
	}
	private static void mostrarPrestamosSocio() {
		ArrayList<Socio> socios = datosS.obtenerSocios();
		for (Socio s: socios) {
			s.mostrar();
		}
		System.out.println("Introduce dni del socio");
		String dni = t.nextLine();
		Socio s=datosS.obtenerSocio(dni);
		if (s!=null) {
			
			ArrayList<Prestamo> prestamos= datosP.obtenerPrestamosSocio(s);
			
			if(!prestamos.isEmpty()) {
				for (Prestamo p: prestamos) {
					p.mostrar();
				}
			}
			else {
				System.out.println("Ese socio no tiene prestamos");
			}									
		}
		else {
			System.out.println("Ese socio no existe");
		}
		
		
	}
	private static void borrarPrestamo() {
		// TODO Auto-generated method stub
		mostrarPrestamos();
		try {
			System.out.println("Introduce dni del socio");
			String dni = t.nextLine();

			Socio s = datosS.obtenerSocio(dni);
			if (s != null) {
				System.out.println("Introduce isbn del libro");
				String isbn = t.nextLine();
				Libro l = datosL.obtenerLibro(isbn);
				if (l != null) {
					System.out.println("Introduce la fecha en la que se realizó el préstamo en formato dd/MM/yyyy");

					Prestamo p = new Prestamo();
					p.setLibro(l);
					p.setSocio(s);

					p.setFechaP(formato.parse(t.nextLine()));

					if (!datosP.borrarPrestamo(p)) {
						System.out.println("Error, no se ha encontrado el préstamo");
					} else {
						System.out.println("Préstamo eliminado correctamente");
					}
				} else {
					System.out.println("Ese socio no tiene un libro con ese isbn");
				}
			} else {
				System.out.println("Error, el socio no existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void devolverPrestamo() {
		// TODO Auto-generated method stub
		mostrarPrestamos();
		System.out.println("Introduce dni del socio que va a realizar la devolución");
		String dni = t.nextLine();
		try {
			Socio s = datosS.obtenerSocio(dni);
			if (s != null) {
				System.out.println("Introduce isbn del libro a devolver");
				String isbn = t.nextLine();
				Libro l = datosL.obtenerLibro(isbn);
				if (l != null) {

					Prestamo p = new Prestamo();
					p.setLibro(l);
					p.setSocio(s);
					System.out.println("Introduce la fecha en la que se realizó el préstamo en formato dd/MM/yyyy");
					
					p.setFechaP(formato.parse(t.nextLine()));

					if (!datosP.devolverPrestamo(p)) {
						System.out.println("Error, no se ha encontrado el préstamo");
					} else {
						// Aumentar los ejemplares
						l.setNumEjemplares(l.getNumEjemplares() + 1);
						if (datosL.modificarEjemplares(l)) {
							System.out.println("Préstamo devuelto correctamente");
						} else {
							System.out.println("Error al devolver el préstamo");
						}
					}
				} else {
					System.out.println("Ese socio no tiene un libro con ese isbn");
				}
			} else {
				System.out.println("Error, el socio no existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void mostrarPrestamos() {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> prestamos = datosP.obtenerPrestamos();
		for(Prestamo p:prestamos) {
			p.mostrar();
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		//Mostramos los socios
		ArrayList<Socio> socios = datosS.obtenerSocios();
		for(Socio s: socios) {
			s.mostrar();
		}
		System.out.println("Introduce el dni del socio");
		String dni = t.nextLine();
		
		//Chequeamos si existe el socio
		Socio s = datosS.obtenerSocio(dni);
		if(s!=null) {
			//Mostramos libros
			ArrayList<Libro> libros = datosL.obtenerLibros();
			for(Libro l:libros) {
				l.mostrar();
			}
			System.out.println("Introduce isbn:");
			String isbn = t.nextLine();
			
			//Chequeamos si existe el libro
			Libro l = datosL.obtenerLibro(isbn);
			if(l!=null) {
				//Comprobar si hay libros suficientes para sacar
				if(l.getNumEjemplares()>0) {
					if(!datosP.crearPrestamo(s,l)) {
						System.out.println("Error, al registrar el préstamo");
					}
					else {
						//Disminuir el número de ejemplares
						l.setNumEjemplares(l.getNumEjemplares()-1);
						if(!datosL.modificarEjemplares(l)) {
							System.out.println("Se ha producido un error al actualizar "
									+ "el número de ejemplares del libro");
						}
					}
				}
				else {
					System.out.println("Error, no hay ejemplares suficientes");
				}
			}
			else {
				System.out.println("Error, el libro no existe");
			}
		}
		else {
			System.out.println("Error, el socio no existe");
		}
				
				
				
				
				
				
				
	}

}

