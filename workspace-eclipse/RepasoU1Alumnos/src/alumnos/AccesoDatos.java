package alumnos;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class AccesoDatos {
	
	private String nombreFXML = "alumnos.xml";

	public String crearFAleatorio() {
		// TODO Auto-generated method stub
		
		String resultado = null;
		Instituto insti = unmarshal();
		
		//Crear fichero aleatorio
		RandomAccessFile fa = null;
		
		try {
			fa = new RandomAccessFile(insti.getNombre()+".bin", "rw");
			for(Alumno a: insti.getAlumnos()) {
				for(Nota n: a.getNotas()) {
					fa.writeInt(a.getExp());
					
					StringBuffer texto = new StringBuffer(a.getNombre());
					texto.setLength(100);
					fa.writeChars(texto.toString());
					
					texto = new StringBuffer(n.getAsig());
					texto.setLength(50);
					fa.writeChars(texto.toString());
					
					fa.writeInt(n.getNota());	
					
				}
			}
			resultado = insti.getNombre()+".bin";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fa!=null) {
				try {
					fa.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	private Instituto unmarshal() {
		// TODO Auto-generated method stub
		Instituto resultado = null;
		File fichero = new File(nombreFXML);
		if(fichero.exists()) {
			JAXBContext contexto;
			try {
				contexto = JAXBContext.newInstance(Instituto.class);
				Unmarshaller unmarshal = contexto.createUnmarshaller();
				resultado = (Instituto) unmarshal.unmarshal(fichero);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return resultado;
	}

	public ArrayList<NotasAle> obtenerNotas(String nombreFichero) {
		// TODO Auto-generated method stub
		ArrayList<NotasAle> resultado = new ArrayList();
		
		RandomAccessFile fa = null;
		
		try {
			fa = new RandomAccessFile(nombreFichero, "r");
			while(true) {
				
				NotasAle n = new NotasAle();
				n.setExp(fa.readInt());
				
				n.setNombre("");
				for(int i=0;i<100;i++) {
					n.setNombre(n.getNombre()+fa.readChar());
				}
				n.setNombre(n.getNombre().trim());
				
				n.setAsig("");
				for(int i=0;i<50;i++) {
					n.setAsig(n.getAsig()+fa.readChar());
				}
				n.setAsig(n.getAsig().trim());
				
				n.setNota(fa.readInt());
				
				resultado.add(n);
				
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
		}
		finally {
			if(fa!=null) {
				try {
					fa.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}

	public NotasAle obtenerNotaAle(String nombreF, int exp, String asig) {
		// TODO Auto-generated method stub
		NotasAle resultado = null;
		
		RandomAccessFile fichero = null;
		try {
			fichero = new RandomAccessFile(nombreF, "r");
			while(true) {
				//Leemos exp
				int expF = fichero.readInt();
				if(expF==exp) {
					//Saltamos nombre
					fichero.seek(fichero.getFilePointer() + 200);
					//Leemos asig
					String asigF = "";
					for(int i=0;i<50;i++) {
						asigF+=fichero.readChar();
					}
					asigF=asigF.trim();
					if(asigF.equals(asig)) {
						resultado = new NotasAle();
						resultado.setExp(expF);
						//Nombre
						fichero.seek(fichero.getFilePointer()-300);
						resultado.setNombre("");
						for(int i=0;i<100;i++) {
							resultado.setNombre(resultado.getNombre()+
									fichero.readChar());
						}
						resultado.setNombre(resultado.getNombre().trim());
						
						resultado.setAsig(asigF);
						
						//Nota
						fichero.seek(fichero.getFilePointer()+100);
						resultado.setNota(fichero.readInt());
					}
					else {
						fichero.seek(fichero.getFilePointer()+4);
					}
				}
				else {
					fichero.seek(fichero.getFilePointer() + 304);
				}
			}
		} 
		catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
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

	public boolean modificarNota(String nombreF, NotasAle n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile fichero = null;
		try {
			fichero = new RandomAccessFile(nombreF, "rw");
			while(true) {
				//Leemos exp
				int expF = fichero.readInt();
				if(expF==n.getExp()) {
					//Saltamos nombre
					fichero.seek(fichero.getFilePointer() + 200);
					//Leemos asig
					String asigF = "";
					for(int i=0;i<50;i++) {
						asigF+=fichero.readChar();
					}
					asigF = asigF.trim();
					if(asigF.equals(n.getAsig())) {
						fichero.writeInt(n.getNota());
						return true;
					}
					else {
						fichero.seek(fichero.getFilePointer()+4);
					}
				}
				else {
					fichero.seek(fichero.getFilePointer() + 304);
				}
			}
		} 
		catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
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

}
