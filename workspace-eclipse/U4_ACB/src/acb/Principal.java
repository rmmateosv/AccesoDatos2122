package acb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
	
	static Partido partidoSeleccionado = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Seleccionar Partido");
				System.out.println("2-Registra Acción");
				System.out.println("3-Anular Acción");
				System.out.println("4-Borrar Partido");
				System.out.println("5-Estadística Partido");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
					case 1: {
						seleccionarPartido();
						break;
					}
					case 2: {
						
						break;
					}
					case 3: {
						
						break;
					}
					case 4: {
						
						break;
					}
					case 5: {
						
						break;
					}
				}
			} while (opcion != 0);
			ad.cerrar();
		}
	}

	private static void seleccionarPartido() {
		// TODO Auto-generated method stub
		mostrarPartidos();
	}

	private static void mostrarPartidos() {
		// TODO Auto-generated method stub
		List<Partido> partidos = ad.obtenerPartidos();
		for(Partido p:partidos) {
			p.mostrar(false);
		}
	}
}
