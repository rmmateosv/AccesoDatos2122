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
				System.out.println("Introduce una opc�on:");
				System.out.println("0-Salir");
				System.out.println("1-Opci�n 1");
				System.out.println("2-Opci�n 2");
				System.out.println("3-Opci�n 3");
				System.out.println("4-Opci�n 4");
				System.out.println("5-Opci�n 5");
				System.out.println("6-Opci�n 6");

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
			System.out.println("Error, no existe colecci�n");
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
