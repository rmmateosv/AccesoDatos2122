package biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Socio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5705729624523763354L;
	@Id
	@Column
	private String dni;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaN;
	@Column(nullable = false)
	private boolean activo;
	
	public Socio(String dni, String nombre, Date fechaN, boolean activo) {
		
		this.dni = dni;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.activo = activo;
	}
	public Socio() {
		
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("DNI:" +  dni +
				"\tNombre:" + nombre +
				"\tfechaN:" + formato.format(fechaN) + 
				"\tActivo:" + activo);
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaN() {
		return fechaN;
	}
	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
