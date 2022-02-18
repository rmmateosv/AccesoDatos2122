package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Socio {
	private int id;
	private String  nombre;
	private Direccion direccion;
	private Date fechaNac;
	
	
	public Socio() {
		super();
	}
	public Socio(int id, String nombre, Direccion direccion, Date fechaNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNac = fechaNac;
	}
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Socio:" + id +
				"\tNombre:"+nombre+
				"\tDirección:" + 
				"\tFechaN:"+formato.format(fechaNac));
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
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	
	
}
