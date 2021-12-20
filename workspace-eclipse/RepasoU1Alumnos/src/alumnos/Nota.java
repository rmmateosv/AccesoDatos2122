package alumnos;

import javax.xml.bind.annotation.XmlAttribute;

public class Nota {
	private String asig;
	private int nota;
	
	public Nota(String asig, int nota) {
		
		this.asig = asig;
		this.nota = nota;
	}

	public Nota() {
		
	}
	@XmlAttribute
	public String getAsig() {
		return asig;
	}

	public void setAsig(String asig) {
		this.asig = asig;
	}
	@XmlAttribute
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
}
