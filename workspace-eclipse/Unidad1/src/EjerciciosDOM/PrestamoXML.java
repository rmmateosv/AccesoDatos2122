package EjerciciosDOM;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
