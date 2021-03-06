package biblioteca;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class PrestamoClave implements Serializable{

	private Socio socio;

	private Libro libro;

	private Date fechaP;
	
	public PrestamoClave(Socio socio, Libro libro, Date fechaP) {
		super();
		this.socio = socio;
		this.libro = libro;
		this.fechaP = fechaP;
	}
	public PrestamoClave() {
		super();
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Date getFechaP() {
		return fechaP;
	}
	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}
	
	
	
}
