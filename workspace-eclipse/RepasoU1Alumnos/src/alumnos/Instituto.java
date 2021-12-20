package alumnos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"nombre","alumnos"})
public class Instituto {
	private String nombre;
	private ArrayList<Alumno> alumnos = new ArrayList<>();
	
	
	public Instituto() {


	}


	public Instituto(String nombre, ArrayList<Alumno> alumnos) {
		
		this.nombre = nombre;
		this.alumnos = alumnos;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElementWrapper
	@XmlElement(name="alumno")
	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
	
}
