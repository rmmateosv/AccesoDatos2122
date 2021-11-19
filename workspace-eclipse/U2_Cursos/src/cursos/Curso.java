package cursos;

public class Curso {
	private int id;
	private String nombre;
	private int horas;
	private String nivel;
	
	
	public void mostrar() {
		System.out.println("Curso: " + id + 
				"\tNombre:" + nombre + 
				"\tHoras:" + horas  + 
				"\tNivel:" + nivel);
	}
	public Curso(int id, String nombre, int horas, String nivel) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
		this.nivel = nivel;
	}

	public Curso() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
}
