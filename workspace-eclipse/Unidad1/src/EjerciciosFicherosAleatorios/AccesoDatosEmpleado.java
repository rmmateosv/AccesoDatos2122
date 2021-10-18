package EjerciciosFicherosAleatorios;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccesoDatosEmpleado {

	// TAMAÑO DE REGISTRO: 215B
	private String nombreFichero;

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public AccesoDatosEmpleado(String nombreFichero) {

		this.nombreFichero = nombreFichero;
	}

	public int obtenerCodigo() {
		// TODO Auto-generated method stub
		int resultado = 0; // si error, devuelve 0.

		RandomAccessFile fichero = null;

		// Abrimos el fichero para leer
		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			// Nos situamos delante del código del último registro del fichero
			fichero.seek(fichero.length() - 215);
			// Leemos el código
			int codigo = fichero.readInt();
			// Obtenemos el nuevo código
			resultado = codigo + 1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			resultado = 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public boolean crearEmpleado(Empleado e) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile fichero = null;

		try {
			// Abrimos el fichero para escritura
			fichero = new RandomAccessFile(nombreFichero, "rw");

			// Nos posicionamos al final del fichero para añadir un nuevo registro
			fichero.seek(fichero.length());
			// Escribimos el nuevo registro
			fichero.writeInt(e.getCodigo());
			// Escribimos el nombre con 100 caracteres
			StringBuffer nombre = new StringBuffer(e.getNombre());
			nombre.setLength(100);
			fichero.writeChars(nombre.toString());

			fichero.writeLong(e.getFechaC().getTime());

			StringBuffer turno = new StringBuffer(e.getTurno());
			turno.setLength(1);
			fichero.writeChars(turno.toString());

			fichero.writeBoolean(e.isBaja());

			resultado = true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		// TODO Auto-generated method stub
		ArrayList<Empleado> resultado = new ArrayList<Empleado>();
		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			// Leemos todos los registros del fichero
			while (true) {
				Empleado e = new Empleado();
				// Leemos codigo
				e.setCodigo(fichero.readInt());
				// Leemos el nombre. Leemos 100 caracteres
				e.setNombre("");
				for (int i = 0; i < 100; i++) {
					e.setNombre(e.getNombre() + fichero.readChar());
				}
				// Limpiamos los espacios en blanco de relleno
				e.setNombre(e.getNombre().trim());

				e.setFechaC(new Date(fichero.readLong()));

				// Solamente es un caracter. No hacemos for!!!
				e.setTurno("");
				e.setTurno(e.getTurno() + fichero.readChar());

				e.setBaja(fichero.readBoolean());

				// Añadir empleado a resultado
				resultado.add(e);

			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay empleados");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		finally {
			if (fichero != null) {
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

	public Empleado obtenerEmpleado(int codigo) {
		// TODO Auto-generated method stub
		Empleado resultado = null;

		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			while (true) {
				// Leemos código
				int codigoF = fichero.readInt();
				// Compruebo si es el buscado
				if (codigoF == codigo) {
					resultado = new Empleado();
					resultado.setCodigo(codigoF);

					resultado.setNombre("");
					for (int i = 0; i < 100; i++) {
						resultado.setNombre(resultado.getNombre() + fichero.readChar());
					}
					resultado.setNombre(resultado.getNombre().trim());

					resultado.setFechaC(new Date(fichero.readLong()));

					resultado.setTurno("");
					resultado.setTurno(resultado.getTurno() + fichero.readChar());

					resultado.setBaja(fichero.readBoolean());

					return resultado;
				} else {
					// Nos desplazamos al siguiente registro
					// Para ello sumamos 211 a la posición actual del fichero
					fichero.seek(fichero.getFilePointer() + 211);
				}

			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public boolean modificarTurno(Empleado e) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombreFichero, "rw");
			while (true) {
				// Leer coódigo
				int codigo = fichero.readInt();
				if (codigo == e.getCodigo()) {
					// Posicionar delante del turno
					fichero.seek(fichero.getFilePointer() + 208);
					// Sobreescribimos turno
					fichero.writeChar(e.getTurno().charAt(0));
					return true;
				} else {
					fichero.seek(fichero.getFilePointer() + 211);
				}
			}
		} catch (EOFException e1) {
			// TODO: handle exception
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean borrarEmpleado(Empleado e) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile ficheroO = null;
		RandomAccessFile ficheroTmp = null;

		try {
			ficheroO = new RandomAccessFile(nombreFichero, "r");
			ficheroTmp = new RandomAccessFile("empleados.tmp", "rw");

			while (true) {
				int codigo = ficheroO.readInt();
				if (codigo != e.getCodigo()) {
					// FORMA 1 -----
					// Nos posicionamos delante del codigo
					ficheroO.seek(ficheroO.getFilePointer() - 4);
					// Leemos 215 B
					byte[] leidos = new byte[215];
					ficheroO.read(leidos, 0, 215);
					// Escribimos los bytes leidos
					ficheroTmp.write(leidos);

					// FORMA 2 ------
					/*
					 * Escribimos código ficheroTmp.writeInt(codigo); //Leemos 211 B byte[]
					 * leidos=new byte[211]; ficheroO.read(leidos, (int) ficheroO.getFilePointer(),
					 * 211); //Escribimos los bytes leidos ficheroTmp.write(leidos);
					 */

					// FORMA 3 ------
					// Leer y escribir cada campo de forma individual

				} else {
					ficheroO.seek(ficheroO.getFilePointer() + 211);
				}
			}
		} catch (EOFException e1) {
			// TODO: handle exception
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (ficheroO != null)
				try {
					ficheroO.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (ficheroTmp != null)
				try {
					ficheroTmp.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}

		// Borrar y renombrar
		File fO = new File(nombreFichero);
		if (fO.delete()) {
			File fTmp = new File("empleados.tmp");
			if (fTmp.renameTo(fO)) {
				resultado = true;
			}
		}

		return resultado;
	}

	public boolean bajaEmpleado(Empleado e) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombreFichero, "rw");
			while (true) {
				// Leer coódigo
				int codigo = fichero.readInt();
				if (codigo == e.getCodigo()) {
					// Posicionar delante de baja
					fichero.seek(fichero.getFilePointer() + 210);
					// Ponemos baja a true
					fichero.writeBoolean(true);;
					return true;
				} else {
					fichero.seek(fichero.getFilePointer() + 211);
				}
			}
		} catch (EOFException e1) {
			// TODO: handle exception
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return resultado;

	}

}
