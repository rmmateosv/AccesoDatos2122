package bibliotecaDB4O;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Prestamo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1197436886026391206L;
	

	private PrestamoClave clave;

	private Date fechaD;

	private boolean devuelto=false;
	
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Socio:" + clave.getSocio().getDni() + " "+ clave.getSocio().getNombre() +
				"\tLibro:" + clave.getLibro().getIsbn() + " " + clave.getLibro().getTitulo() + 
				"\tFechaPréstamo:" + formato.format(clave.getFechaP()) + 
				"\tFechaDevolución:" + formato.format(fechaD) + 
				"\tDevuelto:" + devuelto);
	}

	public Prestamo(PrestamoClave clave, Date fechaD, boolean devuelto) {
		super();
		this.clave = clave;
		this.fechaD = fechaD;
		this.devuelto = devuelto;
	}

	public Prestamo() {
		
	}

	public PrestamoClave getClave() {
		return clave;
	}

	public void setClave(PrestamoClave clave) {
		this.clave = clave;
	}

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	public boolean isDevuelto() {
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}

	
	
	
	
}
