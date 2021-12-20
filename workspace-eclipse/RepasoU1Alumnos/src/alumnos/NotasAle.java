package alumnos;

public class NotasAle {
	private int exp;
	private String nombre, asig;
	private int nota;
	
	
	
	public void mostrar() {
		System.out.println("Exp:" + exp +
				"\tNombre:" + nombre + 
				"\tAsig:" + asig+
				"\tNota:" + nota);
	}
	public NotasAle(int exp, String nombre, String asig, int nota) {
		
		this.exp = exp;
		this.nombre = nombre;
		this.asig = asig;
		this.nota = nota;
	}
	public NotasAle() {
		
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAsig() {
		return asig;
	}
	public void setAsig(String asig) {
		this.asig = asig;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
}
