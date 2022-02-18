package biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	private static AccesoDatos ad = new AccesoDatos();
	private static Scanner t = new java.util.Scanner(System.in);
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Mostrar Socios");
				System.out.println("3-Crear Libro");
				System.out.println("4-Mostrar Libros");				
				System.out.println("5-Prestar Libro");
				System.out.println("6-Mostrar Préstamos");
				System.out.println("8-Devolver Préstamos");


				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1: {
					crearsocio();
					break;
				}
				case 2: {
					mostrarsocio();
					break;
				}
				}
			} while (opcion != 0);
			ad.cerrar();
		}
	}

	private static void mostrarsocio() {
		// TODO Auto-generated method stub
		ArrayList<Socio> socios = ad.obtenerSocios();
		for(Socio s:socios) {
			s.mostrar();
		}
	}

	private static void crearsocio() {
		// TODO Auto-generated method stub
		try {
			Socio s =new Socio();
			
			System.out.println("Nombre:");
			s.setNombre(t.nextLine());
			System.out.println("Calle:");
			s.getDireccion().setCalle(t.nextLine());
			System.out.println("Nº:");
			s.getDireccion().setNumero(t.nextInt());t.nextLine();
			System.out.println("CP:");
			s.getDireccion().setCp(t.nextInt());t.nextLine();
			System.out.println("Fecha Nacicimiento (dd/MM/yy:");
	
			s.setFechaNac(formato.parse(t.nextLine()));
			
			if(!ad.crearSocio(s)) {
				System.out.println("Error al crear el socio");
			}			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Fecha incorrecta");
		}
	}

}
