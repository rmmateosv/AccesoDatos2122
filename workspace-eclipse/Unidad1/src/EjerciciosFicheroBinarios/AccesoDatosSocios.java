package EjerciciosFicheroBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AccesoDatosSocios {
	private String nombreFichero;

	public AccesoDatosSocios(String nombreFichero) {
		
		this.nombreFichero = nombreFichero;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		//Declaramos el fichero para escribir
		DataOutputStream fichero = null;		
		
		try {
			//Abrimos el fichero. Si no existe se crea y si existe
			//a�adiremos informaci�n
			fichero = new DataOutputStream(new FileOutputStream(nombreFichero,true));
			
			//Escribimos los datos del socio en el fichero
			//Hacemos que el dni sea de un tama�o fijo de 9
			StringBuffer dniCon9 = new StringBuffer(s.getDni());
			dniCon9.setLength(9);
			fichero.writeChars(dniCon9.toString());
			
			fichero.writeChars(s.getNombre()+"\n");
			fichero.writeLong(s.getFechaN().getTime());
			fichero.writeBoolean(s.isActivo());
			
			resultado = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, no existe fichero de socios");
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

	public ArrayList<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado = new ArrayList<>();
		
		DataInputStream fichero = null;
		
		try {
			fichero = new DataInputStream(new FileInputStream(nombreFichero));
			
			//Recorremos el fichero leyendo de forma secuencial hasta que
			//lleguemos al final de fichero. Cuando se llega al final del
			//fichero, se produce la excepci�n EOFException, que hay que capturar
			//pero no hay que informar.
			while(true) {
				
				Socio s = new Socio();
				
				//Leemos el dni. Al ser un String tenemos que leer cada uno
				//de sus caracteres.
				String dni="";
				for(int i=0;i<9;i++) {
					dni += fichero.readChar();
				}
				s.setDni(dni);
				
				//Leemos el nombre. Al ser un String de tama�o variable,
				//hay que leer todos los caracteres hasta encontrar el \n
				String nombre="";
				char letra;
				while((letra=fichero.readChar())!='\n') {
					nombre+=letra;
				}
				s.setNombre(nombre);
				
				//Leemos el long de la fecha
				s.setFechaN(new Date(fichero.readLong()));
				
				//Leemos activo
				s.setActivo(fichero.readBoolean());
				
				//A�adimos el socio al resultado
				resultado.add(s);
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("A�n no existen socios");
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

	public Socio obtenerSocio(String pDni) {
		// TODO Auto-generated method stub
		Socio resultado = null;
		
		DataInputStream fichero = null;
		
		try {
			fichero = new DataInputStream(new FileInputStream(nombreFichero));
			
			//Recorremos el fichero leyendo de forma secuencial hasta que
			//lleguemos al final de fichero. Cuando se llega al final del
			//fichero, se produce la excepci�n EOFException, que hay que capturar
			//pero no hay que informar.
			while(true) {				
				//Leemos el dni. Al ser un String tenemos que leer cada uno
				//de sus caracteres.
				String dni="";
				for(int i=0;i<9;i++) {
					dni += fichero.readChar();
				}				
				
				if(dni.equalsIgnoreCase(pDni)) {
					resultado = new Socio();
					resultado.setDni(dni);
					//Leemos el nombre. Al ser un String de tama�o variable,
					//hay que leer todos los caracteres hasta encontrar el \n
					String nombre="";
					char letra;
					while((letra=fichero.readChar())!='\n') {
						nombre+=letra;
					}
					resultado.setNombre(nombre);
					
					//Leemos el long de la fecha
					resultado.setFechaN(new Date(fichero.readLong()));
					
					//Leemos activo
					resultado.setActivo(fichero.readBoolean());
					
					return resultado;
				}
				else {
					char letra;
					String nombre="";
					while((letra=fichero.readChar())!='\n') {
						nombre+=letra;
					}					
					fichero.readLong();					
					//Leemos activo
					fichero.readBoolean();
				}
				
				
				
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("A�n no existen socios");
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

	public boolean bajaSocio(Socio s) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
