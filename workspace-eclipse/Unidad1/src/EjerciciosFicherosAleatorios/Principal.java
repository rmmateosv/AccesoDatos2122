package EjerciciosFicherosAleatorios;


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
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					
					break;
				}
				case 2: {
					crearEmpleado();
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
		if(e.getCodigo()==0) {
			System.out.println("Error, no se puede obtener un código "
					+ "para el empleado");
		}
		else {
			
		}
	}

	
}

