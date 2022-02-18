package biblioteca;

import java.util.ArrayList;

public class Cuento extends Libro{
	private int edadMin;
	private boolean audio;
	
	public Cuento(String isbn, String titulo, int numEjem, ArrayList<String[]> prestamos, int edadMin, boolean audio) {
		super(isbn, titulo, numEjem, prestamos);
		this.edadMin = edadMin;
		this.audio = audio;
	}

	public Cuento() {
		super();
	}

	public int getEdadMin() {
		return edadMin;
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}

	public boolean isAudio() {
		return audio;
	}

	public void setAudio(boolean audio) {
		this.audio = audio;
	}
	
	
}
