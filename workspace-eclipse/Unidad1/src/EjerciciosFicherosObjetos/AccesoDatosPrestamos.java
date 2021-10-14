package EjerciciosFicherosObjetos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import EjerciciosFicheroBinarios.Socio;
import EjerciciosFicheroTexto.Libro;

public class AccesoDatosPrestamos {
	private String nombreFichero;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public AccesoDatosPrestamos(String nombreFichero) {
		
		this.nombreFichero = nombreFichero;
	}

	public boolean crearPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectOutputStream fichero = null;
		
		try {
			
			File f = new File(nombreFichero);
			if(f.exists()) {
				//Abrimos fichero de objeto. Si no existe se crea. Se deben añadir datos
				fichero = new MiObjectOutputStream(new FileOutputStream(nombreFichero,true));
			}
			else {
				//Abrimos fichero de objeto. Si no existe se crea. Se deben añadir datos
				fichero = new ObjectOutputStream(new FileOutputStream(nombreFichero,true));
				
			}	
			
			
			//Creamos el préstamo
			Prestamo p = new Prestamo();
			p.setSocio(s);
			p.setLibro(l);
			p.setFechaP(new Date());
			//Calculamos la fecha de devolución
			Calendar c = Calendar.getInstance();
			c.setTime(p.getFechaP());
			c.add(Calendar.DATE, 15);			
			p.setFechaD(c.getTime());
			
			p.setDevuelto(false);
			
			//Escribimos el préstamo en el fichero
			fichero.writeObject(p);
			
			resultado = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public ArrayList<Prestamo> obtenerPrestamos() {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> resultado = new ArrayList<Prestamo>();
		
		ObjectInputStream fichero = null;
		
		try {
			fichero = new ObjectInputStream(new FileInputStream(nombreFichero));
			
			//Recorremos el fichero
			while(true) {
				//Leemos un objeto préstamo
				Prestamo p = (Prestamo) fichero.readObject();
				
				//Añadimos el préstamo al resultado
				resultado.add(p);
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return resultado;
	}
	
	public boolean devolverPrestamo(Prestamo pP) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		ObjectInputStream ficheroO = null;
		ObjectOutputStream ficheroN = null;

		try {
			ficheroO = new ObjectInputStream(new FileInputStream(nombreFichero));

			ficheroN = new ObjectOutputStream(new FileOutputStream("prestamos.tmp", false));

			while (true) {
				Prestamo p = (Prestamo) ficheroO.readObject();

				// Comprobamos fecha de préstamo, dni e isbn y que no esté devuelto
				if (formato.format(p.getFechaP().getTime()).equals(formato.format(pP.getFechaP()))
						&& (p.getSocio().getDni().equals(pP.getSocio().getDni()))
						&& (p.getLibro().getIsbn().equals(pP.getLibro().getIsbn())) && !p.isDevuelto()) {
					p.setDevuelto(true);
					resultado = true;

				}
				ficheroN.writeObject(p);
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (ficheroO != null) {
					ficheroO.close();
					if (ficheroN != null) {
						ficheroN.close();
					} else {
						System.out.println("Error al cerrar el fichero prestamos.tmp");
					}
				} else {
					System.out.println("Error al cerrar el archivo prestamos.obj");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// cambiar nombre archivos

		File fO = new File(nombreFichero);
		if (fO.delete()) {
			File fN = new File("prestamos.tmp");
			if (fN.renameTo(fO)) {

			} else {
				System.out.println("Error al renombrar el archivo prestamos.tmp");
			}
		} else {
			System.out.println("Error al borrar el archivo prestamos.obj");
		}

		return resultado;
	}

	public boolean borrarPrestamo(Prestamo pP) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		ObjectInputStream ficheroO = null;
		ObjectOutputStream ficheroN = null;

		try {
			ficheroO = new ObjectInputStream(new FileInputStream(nombreFichero));

			ficheroN = new ObjectOutputStream(new FileOutputStream("prestamos.tmp", false));

			while (true) {
				Prestamo p = (Prestamo) ficheroO.readObject();

				if (!formato.format(p.getFechaP().getTime()).equals(formato.format(pP.getFechaP()))
						|| !(p.getSocio().getDni().equals(pP.getSocio().getDni()))
						|| !(p.getLibro().getIsbn().equals(pP.getLibro().getIsbn()))) {
					ficheroN.writeObject(p);
				}
			}
		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ficheroO != null) {
					ficheroO.close();
					if (ficheroN != null) {
						ficheroN.close();
					} else {
						System.out.println("Error al cerrar el fichero prestamos.tmp");
					}
				} else {
					System.out.println("Error al cerrar el archivo prestamos.obj");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File fO = new File(nombreFichero);
		if (fO.delete()) {
			File fN = new File("prestamos.tmp");
			if (fN.renameTo(fO)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar el archivo prestamos.tmp");
			}
		} else {
			System.out.println("Error al borrar el archivo prestamos.obj");
		}

		return resultado;
	}

	public ArrayList<Prestamo> obtenerPrestamosSocio(Socio s) {
		ArrayList<Prestamo> resultado = new ArrayList<Prestamo>();

		ObjectInputStream fichero = null;

		try {
			fichero = new ObjectInputStream(new FileInputStream(nombreFichero));

			while (true) {
				// añadir solo los préstamos que sean del socio
				Prestamo p = (Prestamo) fichero.readObject();
				if (p.getSocio().getDni().equals(s.getDni())) {
					resultado.add(p);
				} else {

				}
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}
	
	
}
