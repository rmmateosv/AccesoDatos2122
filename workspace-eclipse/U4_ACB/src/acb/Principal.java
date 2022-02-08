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
						registrarAcccion();
						break;
					}
					case 3: {
						anularAccion();
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

	private static void anularAccion() {
		// TODO Auto-generated method stub
		if(partidoSeleccionado!=null) {
			partidoSeleccionado = ad.obtenerPartido(partidoSeleccionado.getCodigo());
			partidoSeleccionado.mostrar(true);
			System.out.println("Introduce código de acción a anular");
			int codigo = t.nextInt(); t.nextLine();
			if(!ad.anularAccion(partidoSeleccionado, codigo)) {
				System.out.println("Error, al anular la acción");
			}
			else {
				partidoSeleccionado = ad.obtenerPartido(partidoSeleccionado.getCodigo());
				partidoSeleccionado.mostrar(true);
			}
						
		}
		else {
			System.out.println("Error, selecciona partido primero");
		}
	}

	private static void registrarAcccion() {
		// TODO Auto-generated method stub
		if(partidoSeleccionado!=null) {
			mostrarTipoAcciones();
			System.out.println("Introduce tipo acción");
			char tipo = t.next().charAt(0);
			TipoAccion tipoAccion = ad.obtenerTipoAccion(tipo);
			if(tipoAccion!=null) {
				System.out.println("Jugadores Locales");
				for(Jugador j:partidoSeleccionado.
						      getLocal().getJugadores()) {
					j.mostrar(false);
				}
				System.out.println("Jugadores Visitantes");
				for(Jugador j:partidoSeleccionado.
						      getVisitante().getJugadores()) {
					j.mostrar(false);
				}
				System.out.println("Introduce código de jugador");
				int codigoJ =t.nextInt();t.nextLine();
				Jugador j = ad.obtenerJugador(
						codigoJ,
						partidoSeleccionado.getLocal(),
						partidoSeleccionado.getVisitante());
				if(j!=null) {
					if(!ad.registrarAccion(partidoSeleccionado,j,tipoAccion)) {
						System.out.println("Error al registrar la acción");
					}
					else {
						partidoSeleccionado = ad.obtenerPartido(partidoSeleccionado.getCodigo());
						partidoSeleccionado.mostrar(true);
					}
				}
				else {
					System.out.println(
							"Error, jugador no existe o no juega el partdio");
				}
			}
			else {
				System.out.println("Error, no existe tipo acción");
			}
		}
		else {
			System.out.println("Error, hay que seleccionar partido");
		}
	}

	private static void mostrarTipoAcciones() {
		// TODO Auto-generated method stub
		List<TipoAccion> tipos = ad.obtenerTiposAcc();
		for(TipoAccion t : tipos) {
			t.mostrar();
		}
	}

	private static void seleccionarPartido() {
		// TODO Auto-generated method stub
		mostrarPartidos();
		System.out.println("Introduce código de partido:");
		int codigo = t.nextInt(); t.nextLine();
		partidoSeleccionado = ad.obtenerPartido(codigo);
		if(partidoSeleccionado!=null) {
			//Mostrar partido
			System.out.println("Partido seleccionado:");
			partidoSeleccionado.mostrar(false);
		}
		else {
			System.out.println("Error, no existe el partido con código " + codigo);
		}
	}

	private static void mostrarPartidos() {
		// TODO Auto-generated method stub
		List<Partido> partidos = ad.obtenerPartidos();
		for(Partido p:partidos) {
			p.mostrar(false);
		}
	}
}
