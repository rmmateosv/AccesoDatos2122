package bibliotecaDB4O;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Libro implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2077981661327561318L;
	
	private int id;	
	private String isbn;	
	private String titulo;
	private int numEjemplares;
	
	private List<Prestamo> prestamos = new ArrayList<>();
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}


	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}


	public Libro() {
		
	}

	
	public Libro(int id, String isbn, String titulo,  int numEjemplares) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
	
		this.numEjemplares = numEjemplares;
	}


	public Libro(String isbn, String titulo,  int numEjemplares) {
		
		this.isbn = isbn;
		this.titulo = titulo;

		this.numEjemplares = numEjemplares;
	}
	
	public void mostrar(boolean mostrarPrestamos) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
		System.out.println("ID:"+id
				+ "\tISBN:" + isbn + 
				"\tTitulo:" + titulo + 
				"\tEjemplares:" + numEjemplares);
		if(mostrarPrestamos) {
			System.out.println("Préstamos del libro:");
			for(int i=0;i<prestamos.size();i++) {
				prestamos.get(i).mostrar();
			}
		}
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumEjemplares() {
		return numEjemplares;
	}

	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}
	
	
	
}
