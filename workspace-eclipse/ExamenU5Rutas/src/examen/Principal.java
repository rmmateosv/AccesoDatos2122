package examen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;



public class Principal {

	private static Scanner t = new java.util.Scanner(System.in);
	private static AccesoDatosPostgress adP = new AccesoDatosPostgress();
	private static AccesoDatosDB4O adD = new AccesoDatosDB4O();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (adP.getConexion() != null && adD.getConexion()!=null) {
			int opcion = 0;
			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Alta Lugar");
				System.out.println("2-Crear Ruta");
				System.out.println("3-Borrar Lugar");
				System.out.println("4-Alta Lugar DB4O");
				System.out.println("5-alta Ruta DB4O");
				System.out.println("6-borrar Ruta DB4O con cascada!!!!");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {

				case 1:
					altalugar();
					break;

				case 2:
					crearRuta();
					break;

				case 3:

					break;

				case 4:

					break;
				case 5:

					break;

				}
			} while (opcion != 0);
			adP.cerrar();
			adD.cerrar();
		}
		else {
			System.out.println("Error al conectar con Postgress o DB4O");
		}
	}

	private static void crearRuta() {
		// TODO Auto-generated method stub
		System.out.println("Nombre Ruta:");
		String nombre = t.nextLine();
		Ruta r = adP.obtenerRuta(nombre);
		if(r==null) {
			r= new Ruta();
			r.setNombre(nombre);
			System.out.println("Tiempo:");
			r.setTiempo(t.nextInt()); t.nextLine();
			if(!adP.crearRuta(r)) {
				System.out.println("error al crear la ruta");
			}
			else {
				int mas = 0;
				do {
					mostrarLugares();
					System.out.println("Codigo de lugar a añadir");
					int codigo = t.nextInt();t.nextLine();
					Lugar l = adP.obtenerLugar(codigo);
					if(l!=null) {
						if(!adP.anadirLugarRuta(r,l)) {
							System.out.println("Error al añadir el lugar");
						}
					}
					else {
						System.out.println("Error, lugar no existe");
					}
					System.out.println("Más Lugares (1:Sí/0:No");
					mas = t.nextInt();t.nextLine();
				}while(mas==1);
				r= adP.obtenerRuta(r.getNombre());
				System.out.println("Ruta:"+r.getNombre() +
						"\tTiempo:"+ r.getTiempo());
				for(Integer i: r.getLugares()) {
					Lugar l = adP.obtenerLugar(i);
					l.mostrar();
				}
				
			}
		}
		else {
			System.out.println("Error, ruta existe");
		}
	}

	private static void altalugar() {
		// TODO Auto-generated method stub
		System.out.println("Nombre lugar:");
		String nombre = t.nextLine();
		Lugar l = adP.obtenerLugar(nombre);
		if(l==null) {
			l = new Lugar();
			l.setNombre(nombre);
			l.setInfo(new Localizacion());
			System.out.println("Latitud");
			l.getInfo().setLatitud(t.nextInt()); t.nextLine();
			System.out.println("Longitud");
			l.getInfo().setLongitud(t.nextInt()); t.nextLine();
			if(!adP.crearLugar(l)) {
				System.out.println("Error al crear el lugar");
			}
			else {
				mostrarLugares();
			}
		}
		else {
			System.out.println("Error, el lugar ya existe");
		}
		
	}

	private static void mostrarLugares() {
		// TODO Auto-generated method stub
		ArrayList<Lugar> lugares = adP.obtenerLugares();
		for(Lugar l:lugares) {
			l.mostrar();
		}
	}

}
