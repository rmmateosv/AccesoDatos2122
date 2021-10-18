package EjerciciosFicherosAleatorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatosEmpleado datosE = new AccesoDatosEmpleado("empleados.rand");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Empleados");
			System.out.println("2-Crear Empleado");
			System.out.println("3-Modificar turno");
			System.out.println("4-Borrar Empleado");
			System.out.println("5-Baja Empleado");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1: {
				mostrarEmpleados();
				break;
			}
			case 2: {
				crearEmpleado();
				break;
			}
			case 3: {
				modificarTurno();
				break;
			}
			case 4: {
				borrarEmpleado();
				break;
			}
			case 5: {
				bajaEmpleado();
				break;
			}
			case 6: {

				break;
			}

			}

		} while (opcion != 0);

	}

	private static void bajaEmpleado() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce codigo de empleado");
		int codigo = t.nextInt();
		t.nextLine();

		Empleado e = datosE.obtenerEmpleado(codigo);
		if (e != null) {
			if (e.isBaja()) {
				System.out.println("Ya está dado de baja");
			} else 
				if (!datosE.bajaEmpleado(e)) {
					System.out.println("Error al dar de baja el empleado");
				}
		} else {
			System.out.println("Error, el empleado no existe");
		}
	}

	private static void borrarEmpleado() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce codigo de empleado");
		int codigo = t.nextInt();
		t.nextLine();

		Empleado e = datosE.obtenerEmpleado(codigo);
		if (e != null) {
			if (!datosE.borrarEmpleado(e)) {
				System.out.println("Error al borrar el empleado");
			}
		} else {
			System.out.println("Error, el empleado no existe");
		}
	}

	private static void modificarTurno() {
		// TODO Auto-generated method stub
		mostrarEmpleados();
		System.out.println("Introduce codigo de empleado");
		int codigo = t.nextInt();
		t.nextLine();

		Empleado e = datosE.obtenerEmpleado(codigo);
		if (e != null) {
			System.out.println("Introduce el nuevo turno (M-Mañana/T-Tarde)");
			e.setTurno(t.nextLine().toUpperCase());
			if (!datosE.modificarTurno(e)) {
				System.out.println("Error al modificar el turno");
			}
		} else {
			System.out.println("Error, no existe el empleado");
		}
	}

	private static void mostrarEmpleados() {
		// TODO Auto-generated method stub
		ArrayList<Empleado> empleados = datosE.obtenerEmpleados();
		for (Empleado e : empleados) {
			e.mostrar();
		}
	}

	private static void crearEmpleado() {
		// TODO Auto-generated method stub
		Empleado e = new Empleado();

		System.out.println("Nombre del empleado");
		e.setNombre(t.nextLine());

		System.out.println("Introduce turno (M-Mañana/T-Tarde)");
		e.setTurno(t.nextLine().toUpperCase());

		e.setFechaC(new Date());
		e.setBaja(false);
		e.setCodigo(datosE.obtenerCodigo());
		if (e.getCodigo() == 0) {
			System.out.println("Error, no se puede obtener un código " + "para el empleado");
		} else {
			if (!datosE.crearEmpleado(e)) {
				System.out.println("Error al crear el empleado");
			}
		}
	}

}
