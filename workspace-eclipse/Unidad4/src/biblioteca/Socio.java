package biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaSancion;
	
	public Date getFechaSancion() {
		return fechaSancion;
	}
	public void setFechaSancion(Date fechaSancion) {
		this.fechaSancion = fechaSancion;
	}
	//Relación uno a muchos entre socio y préstamo
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.socio")
	private List<Prestamo> prestamos  = new ArrayList<>();
	
	
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	public Socio(String dni, String nombre, Date fechaN, boolean activo, Date fechaSancion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.activo = activo;
		this.fechaSancion = fechaSancion;
	}
	public Socio() {
		
	}
	
	public void mostrar(boolean mostrarPrestamos) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fechaS;
		if(fechaSancion == null) {
			fechaS = "";
		}
		else {
			fechaS= formato.format(fechaSancion);
		}
		System.out.println("DNI:" +  dni +
				"\tNombre:" + nombre +
				"\tfechaN:" + formato.format(fechaN) + 
				"\tActivo:" + activo +
				"\tfechaSancion:" + fechaS);
		if(mostrarPrestamos) {
			System.out.println("Préstamos del socio");
			for(Prestamo p : prestamos) {
				p.mostrar();
			}
		}
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
