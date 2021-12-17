package biblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import EjerciciosFicheroTexto.AccesoDatosLibro;
import EjerciciosFicheroTexto.Libro;

public class Pricipal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Crear BD Biblioteca");
			System.out.println("2-Importar Libros");
			System.out.println("3-Mostrar Libros");
			System.out.println("4-Mostrar Libro por isbn");
			System.out.println("5-Crear Socio");
			System.out.println("6-Mostrar Socios");
			System.out.println("7-Prestar Libro");
			System.out.println("8-Mostrar Préstamos");
			System.out.println("9-Mostrar Préstamos pendientes de un socio");
			System.out.println("10-Devolver Préstamos");
			System.out.println("11- Borrar Socio");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				crearBiblioteca();
				break;
			}
			case 2: {
				importarLibros();
				break;
			}
			case 3: {
				mostrarLibros();
				break;
			}
			case 4: {
				mostrarLibro();
				break;
			}
			case 5: {
				crearSocio();
				break;
			}
			case 6: {
				mostrarSocios();
				break;
			}
			case 7: {
				crearPrestamo();
				break;
			}
			case 8: {
				mostrarPrestamos();
				break;
			}
			case 9: {
				mostrarPrestamosSocio();
				break;
			}
			case 10: {
				devolverPrestamo();
				break;
			}
			case 11:{
				borrarSocio();
			}

			}

		} while (opcion != 0);
		ad.cerrar();
	}

	private static void mostrarPrestamosSocio() {
		// TODO Auto-generated method stub
		ad.mostrarSocios();
		
		System.out.println("Nif");
		String nif = t.nextLine();
		ad.mostrarPrestamos(nif);
	}

	private static void mostrarPrestamos() {
		// TODO Auto-generated method stub
		ad.mostrarPrestamos();
	}

	private static void borrarSocio() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Nif:");
		String nif = t.nextLine();
		if(ad.existeSocio(nif)) {
			ad.borrarSocio(nif);
		}
	}

	private static void devolverPrestamo() {
		// TODO Auto-generated method stub
		if(ad.getColeccion()!=null) {
			mostrarSocios();
			System.out.println("Introduce nif");
			String nif = t.nextLine();
			if(ad.existeSocio(nif)) {
				ad.mostrarPrestamoSocios(nif);
				System.out.println("Id del préstamo a devolver:");
				int id = t.nextInt(); t.nextLine();
				if(ad.existePrestamoSocio(id,nif)) {
					if(!ad.devolverPrestamo(id,nif)) {
						System.out.println("Error al devolver el préstamo");
					} 
				}
				else {
					System.out.println("Error, préstamo no existe o no es del socio");
				}
				
			}
			else {
				System.out.println("Error, socio no existe");
			}
		}
		else {
			System.out.println("Error, no está creada la biblioteca");
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			ad.mostrarSocios();
			System.out.println("NIf:");
			String nif = t.nextLine();
			if (ad.existeSocio(nif)) {
				// Ver si está sancionado
				if (!ad.SocioSancionado(nif)) {
					if (ad.obtenerNumPrestamos(nif) < 2) {
						ad.mostrarLibros();
						System.out.println("Isbn");
						String isbn = t.nextLine();
						if (ad.existeLibro(isbn)) {
							int numEjem = ad.obtenerEjemplaresLibro(isbn);
							if ( numEjem> 0) {
								int id = ad.crearPrestamo(nif, isbn,numEjem);
								if (id == 0) {
									System.out.println("Error al crear el préstamo");
								} else {
									System.out.println("Se ha creado el préstamo con id " + id);
								}

							} else {
								System.out.println("Error, no hay ejemplares suficientes");
							}
						} else {
							System.out.println("Error, el libro no existe");
						}
					}

					else {
						System.out.println("Error, el socio ya tiene 2 préstamos");
					}
				} else {
					System.out.println("Error, el socio está sancionado");
				}
			} else {
				System.out.println("Error, socio no existe");
			}

		} else {
			System.out.println("Error, no existe la biblioteca");
		}
	}

	private static void mostrarSocios() {
		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			ad.mostrarSocios();
		} else {
			System.out.println("Error, no existe la colección");
		}
	}

	private static void crearSocio() {

		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			System.out.println("Nif:");
			String nif = t.nextLine();
			if (!ad.existeSocio(nif)) {
				System.out.println("Nombre:");
				String nombre = t.nextLine();

				if (!ad.crearSocio(nif, nombre)) {
					System.out.println("Error al crear el socio");
				}
			} else {
				System.out.println("Error, ya existe un socio con ese dni");
			}

		} else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void mostrarLibro() {
		// TODO Auto-generated method stub
		mostrarLibros();
		if (ad.getColeccion() != null) {
			System.out.println("Introduce ISBN:");
			String isbn = t.nextLine();
			ad.mostrarLibro(isbn);
		} else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			ad.mostrarLibros();
		} else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void importarLibros() {
		// TODO Auto-generated method stub
		if (ad.getColeccion() != null) {
			ArrayList<Libro> libros = new AccesoDatosLibro("libros.txt").obtenerLibros();
			for (Libro l : libros) {
				if (!ad.crearLibro(l)) {
					System.out.println("Error al crear el libro" + l.getIsbn());
				}
			}
		} else {
			System.out.println("Error, no existe la colección biblioteca");
		}
	}

	private static void crearBiblioteca() {
		// TODO Auto-generated method stub
		// Chequear que no existe antes de crear
		if (ad.getColeccion() == null) {
			if (!ad.crearBiblioteca()) {
				System.out.println("Error, no se puede crear la colección");
			}
		} else {
			System.out.println("Error, ya existe la colección");
		}
	}

}
