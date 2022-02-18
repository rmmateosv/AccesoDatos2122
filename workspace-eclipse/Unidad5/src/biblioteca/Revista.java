package biblioteca;

import java.util.ArrayList;
import java.util.Date;

public class Revista extends Libro{
	private String genero;
	private Date fechaP;
	public Revista(String isbn, String titulo, int numEjem, ArrayList<String[]> prestamos, String genero, Date fechaP) {
		super(isbn, titulo, numEjem, prestamos);
		this.genero = genero;
		this.fechaP = fechaP;
	}
	public Revista() {
		super();
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaP() {
		return fechaP;
	}
	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}
	
	
	
}
