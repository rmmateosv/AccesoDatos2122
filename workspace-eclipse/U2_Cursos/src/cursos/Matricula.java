package cursos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Matricula {
	private Curso curso;
    private String alumno;
    private Date fecha;
    private int nota;
    
    
    public void mostrar() {
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
    	System.out.println("Curso:" + curso.getId() 
    	                   + " " + curso.getNombre()
    	                   +"\tAlumno:" +  alumno 
    	                   +"\tFecha:" + formato.format(fecha)
    	                   +"\tNota:" + nota);
    }
	public Matricula() {
		super();
	}
	public Matricula(Curso curso, String alumno, Date fecha, int nota) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.fecha = fecha;
		this.nota = nota;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
    
    
}
