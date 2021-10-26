package EjerciciosJaxB;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"socio","libro"})
public class PrestamoXML {
	private int id;
	private Date fecha;
	private String socio, libro;
	
	public void mostrar(){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Id:" +id +
				"\tFecha:"+formato.format(fecha)+
				"\tSocio:" + socio +
				"\tLibro:" + libro);
		
	}
	public PrestamoXML() {
		super();
	}
	public PrestamoXML(int id, Date fecha, String socio, String titulo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.socio = socio;
		this.libro = titulo;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlAttribute
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElement
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	@XmlElement
	public String getLibro() {
		return libro;
	}
	public void setLibro(String titulo) {
		this.libro = titulo;
	}
	
	
}
