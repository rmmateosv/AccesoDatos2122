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
				System.out.println("Introduce una opc?on:");
				System.out.println("0-Salir");
				System.out.println("1-Opci?n 1");
				System.out.println("2-Opci?n 2");
				System.out.println("3-Opci?n 3");
				System.out.println("4-Opci?n 4");
				System.out.println("5-Opci?n 5");
				System.out.println("6-Opci?n 6");

				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1: {
					altaEmpleado();
					break;
				}
				case 2: {
					altaTarea();
					break;
				}
				case 3: {
					finalizarTarea();
					break;
				}
				case 4: {
					mostrarTareasEmpleado();
					break;
				}
				case 5: {
					borrarTarea();
					break;
				}
				case 6: {

					break;
				}
				}
			} while (opcion != 0);
			datos.cerrar();
		} else {
			System.out.println("Error, no existe colecci?n");
		}
	}

	private static void borrarTarea() {
		// TODO Auto-generated method stub
		datos.mostrarTareas();
		System.out.println("Id tarea:");
		int id = t.nextInt();t.nextLine();
		if(datos.existeTareaAbierta(id)) {
			if(!datos.borrarTarea(id)) {
				System.out.println("Error al finalizar la tarea");
			}
		}
		else {
			System.out.println("Error, la tarea no existe o est? finalizada");
		}
	}

	private static void mostrarTareasEmpleado() {
		// TODO Auto-generated method stub
		datos.mostrarEmpleados();
		System.out.println("Empleado");
		int emp = t.nextInt(); t.nextLine();
		datos.mostrarTareas(emp);
	}

	private static void finalizarTarea() {
		// TODO Auto-generated method stub
		datos.mostrarTareas();
		System.out.println("Id tarea:");
		int id = t.nextInt();t.nextLine();
		if(datos.existeTareaAbierta(id)) {
			if(!datos.finalizarTarea(id)) {
				System.out.println("Error al finalizar la tarea");
			}
		}
		else {
			System.out.println("Error, la tarea no existe o est? finalizada");
		}
	}

	private static void altaTarea() {
		// TODO Auto-generated method stub
		datos.mostrarEmpleados();
		System.out.println("Introduce id de empleado");
		int id = t.nextInt(); t.nextLine();
		if(datos.existeEmpleado(id)) {
			System.out.println("Descripcion tarea");
			String desc = t.nextLine();
			if(!datos.altaTarea(id,desc,formato.format(new Date()))) {
				System.out.println("Error al dar de alta la tarea");
			}
		}
		else {
			System.out.println("Error, no existe empleado");
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
