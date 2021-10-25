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
	private String socio, titulo;
	
	public void mostrar(){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Id:" +id +
				"\tFecha:"+formato.format(fecha)+
				"\tSocio:" + socio +
				"\tTitulo:" + titulo);
		
	}
	public PrestamoXML() {
		super();
	}
	public PrestamoXML(int id, Date fecha, String socio, String titulo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.socio = socio;
		this.titulo = titulo;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
