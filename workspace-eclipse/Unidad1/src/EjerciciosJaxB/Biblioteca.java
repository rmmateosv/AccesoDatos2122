package EjerciciosJaxB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




@XmlRootElement
@XmlType(propOrder = {"nombre","fecha","prestamos"})
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
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElementWrapper(name = "prestamos")
	@XmlElement(name="prestamo")
	public ArrayList<PrestamoXML> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(ArrayList<PrestamoXML> prestamos) {
		this.prestamos = prestamos;
	}
	
	
}
