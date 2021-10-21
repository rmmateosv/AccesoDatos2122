package EjerciciosDOM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Biblioteca {
	private String nombre;
	private Date fecha;
	private ArrayList<PrestamoXML> prestamos = new ArrayList<>();
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Nombre:" + nombre + 
				"\tFechaCreación:"+formato.format(fecha));
		for(PrestamoXML p : prestamos) {
			p.mostrar();
		}
		
	}
	public Biblioteca(String nombre, Date fecha, ArrayList<PrestamoXML> prestamos) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.prestamos = prestamos;
	}
	public Biblioteca() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<PrestamoXML> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(ArrayList<PrestamoXML> prestamos) {
		this.prestamos = prestamos;
	}
	
	
}
