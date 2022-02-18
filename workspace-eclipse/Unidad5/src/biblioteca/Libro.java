package biblioteca;

import java.util.ArrayList;

public class Libro {
	private String isbn, titulo;
	private int numEjem;
	private ArrayList<String[]> prestamos =new ArrayList();
	public Libro(String isbn, String titulo, int numEjem, ArrayList<String[]> prestamos) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.numEjem = numEjem;
		this.prestamos = prestamos;
	}
	public Libro() {
		
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
	public int getNumEjem() {
		return numEjem;
	}
	public void setNumEjem(int numEjem) {
		this.numEjem = numEjem;
	}
	public ArrayList<String[]> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(ArrayList<String[]> prestamos) {
		this.prestamos = prestamos;
	}
	
	
	
	
}
