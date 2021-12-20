package alumnos;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;



@XmlType(propOrder = {"nombre", "fechaN","notas"})
public class Alumno {
	private int exp;
	private String nombre;
	private Date fechaN;
	private ArrayList<Nota> notas = new ArrayList<>();
	
	
	
	public Alumno() {
		
	}



	public Alumno(int exp, String nombre, ArrayList<Nota> notas) {

		this.exp = exp;
		this.nombre = nombre;
		this.notas = notas;
	}


	@XmlAttribute(name="expediente")
	public int getExp() {
		return exp;
	}



	public void setExp(int exp) {
		this.exp = exp;
	}


	@XmlElement
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@XmlElement
	public Date getFechaN() {
		return fechaN;
	}



	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}


	@XmlElementWrapper
	@XmlElement(name="nota")
	public ArrayList<Nota> getNotas() {
		return notas;
	}



	public void setNotas(ArrayList<Nota> notas) {
		this.notas = notas;
	}
	
	
}
