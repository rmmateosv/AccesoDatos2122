package cursos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Principal {

	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (ad.getConexion() != null) {
			login();
			ad.cerrar();
		} else {
			System.out.println("Error, no se ha podido conectar con la BD");
		}
	}

	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Usuario");
		String usuario = t.nextLine();
		System.out.println("Contraseña:");
		String clave = t.nextLine();
		
		String perfil = ad.login(usuario,clave);
		if(perfil== null || perfil.equalsIgnoreCase("NE")){
			System.out.println("Error, usuario o contraseña incorrecto");
		}
		else {
			if(perfil.equalsIgnoreCase("Admin")) {
				menuAdmin();
			}
			else {
				menuUsuario();
			}
		}
	}

	private static void menuUsuario() {
		// TODO Auto-generated method stub
		System.out.println("Menú Alumno");
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Modificar contraseña");
			System.out.println("2-Mostrar Cursos (con patrón)");
			System.out.println("3-Matricularse en un curso");
			System.out.println("4-Anular matrícula");
			System.out.println("5-Ver resultados curso");
			System.out.println("6-Estadística");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					
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
	}

	private static void menuAdmin() {
		// TODO Auto-generated method stub
		System.out.println("Menú Administrador");
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Crear Alumno");
			System.out.println("2-Mostrar Alumnos (con patrón)");
			System.out.println("3-Borrar Alumno");
			System.out.println("4-Crear Curso");
			System.out.println("5-Mostrar Cursos");
			System.out.println("6-Modificar Curso");
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					crearAlumno();
					break;
				}
				case 2: {
					mostrarAlumnos();
					break;
				}
				case 3: {
					borrarAlumno();
					break;
				}
				case 4: {
					crearCurso();
					break;
				}
				case 5: {
					mostrarCursos();
					break;
				}
				case 6: {
					modificarCurso();
					break;
				}
				
			}
			
		} while (opcion != 0);
	}

	private static void modificarCurso() {
		// TODO Auto-generated method stub
		mostrarCursos();
		System.out.println("Introduce el id del curso a modificar");
		int id = t.nextInt();t.nextLine();
		Curso c = ad.obtenerCurso(id);
		if(c!=null) {
			System.out.println("Nuevo Nombre");
			c.setNombre(t.nextLine());
			System.out.println("Nuevas Horas:");
			c.setHoras(t.nextInt()); t.nextLine();
			System.out.println("Nuevo Nivel: 1-Principiante, 2-Medio, 3-Avanzado");
			int nivel = t.nextInt();t.nextLine();
			switch (nivel) {
				case 1:{
					c.setNivel("Principiante");break;}
				case 2:{
					c.setNivel("Medio");break;}
				case 3:{
					c.setNivel("Avanzado");break;}
				
			}
			if(!ad.modificarCurso(c)) {
				System.out.println("Error al modificar el curso");
			}
		}
		else {
			System.out.println("Error, el curso no existe");
		}
	}

	private static void mostrarCursos() {
		// TODO Auto-generated method stub
		ArrayList<Curso> cursos = ad.obtenerCursos();
		for(Curso c: cursos) {
			c.mostrar();
		}
	}

	private static void crearCurso() {
		// TODO Auto-generated method stub
		Curso c = new Curso();
		System.out.println("Nombre");
		c.setNombre(t.nextLine());
		System.out.println("Horas:");
		c.setHoras(t.nextInt()); t.nextLine();
		System.out.println("Nivel: 1-Principiante, 2-Medio, 3-Avanzado");
		int nivel = t.nextInt();t.nextLine();
		switch (nivel) {
			case 1:{
				c.setNivel("Principiante");break;}
			case 2:{
				c.setNivel("Medio");break;}
			case 3:{
				c.setNivel("Avanzado");break;}
			
		}
		if(!ad.crearCurso(c)) {
			System.out.println("Error al crear el curso");
		}
		else {
			System.out.println("Se ha creado el curso:");
			c.mostrar();
		}
		
	
		
	}

	private static void borrarAlumno() {
		// TODO Auto-generated method stub
		ArrayList<String> alumnos = ad.obtenerAlumnos("%");
		for(String nombre:alumnos) {
			System.  out.println("Alumno:" + nombre);
		}
		System.out.println("Introduce el alumno a borrar");
		String alumno = t.nextLine();
		if(ad.existeAlumno(alumno)) {
			if(!ad.borrarAlumno(alumno)) {
				System.out.println("Error, al borrar alumno");
			}
		}
		
		
	}

	private static void mostrarAlumnos() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre o parte del nombre");
		ArrayList<String> alumnos = ad.obtenerAlumnos(t.nextLine());
		for(String nombre:alumnos) {
			System.  out.println("Alumno:" + nombre);
		}
	}

	private static void crearAlumno() {
		// TODO Auto-generated method stub
		System.out.println("Nombre Usuario a crear:");
		String usuario = t.nextLine();
		//Chequear si hay otro usuario con el mismo nombre
		if(!ad.existeUsuario(usuario)) {
			if(!ad.crearAlumno(usuario)) {
				System.out.println("Error al dar de alta el alumno");
			}
		}
		else {
			System.out.println("Error, ya hay un alumno con ese nombre");
		}
	}

}
