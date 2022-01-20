package Tareas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos datos = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (datos.getColeccion() != null) {
			int opcion = 0;

			do {
				System.out.println("Introduce una opcíon:");
				System.out.println("0-Salir");
				System.out.println("1-Opción 1");
				System.out.println("2-Opción 2");
				System.out.println("3-Opción 3");
				System.out.println("4-Opción 4");
				System.out.println("5-Opción 5");
				System.out.println("6-Opción 6");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1: {
					altaEmpleado();
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
				case 6: {

					break;
				}
				}
			} while (opcion != 0);
			datos.cerrar();
		} else {
			System.out.println("Error, no existe colección");
		}
	}

	private static void altaEmpleado() {
		// TODO Auto-generated method stub
		datos.mostrarDepartamentos();
		System.out.println("Introduce dpto:");
		String dpto = t.nextLine();
		//Chequear que existe
		if(datos.existeDpto(dpto)) {
			System.out.println("Nombre:");
			String nombre = t.nextLine();
			System.out.println("Salario:");
			Float salario = t.nextFloat(); t.nextLine();			
			try {
				System.out.println("Introduce fecha (yyyy-MM-dd)");
				String fecha = t.nextLine();
				Date f = formato.parse(fecha);
				if(!datos.altaEmpleado(dpto,nombre,salario, fecha)) {
					System.out.println("Error al crear el empleado");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en la fecha");
			}
			
		}
		else{
			System.out.println("Error, departamento no existe");
		}
		
	}

}
