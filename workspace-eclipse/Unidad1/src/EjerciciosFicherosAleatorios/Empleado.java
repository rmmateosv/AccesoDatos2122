package EjerciciosFicherosAleatorios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleado {
	private int codigo;
	private String nombre;
	private Date fechaC;
	private String turno;
	private boolean baja;
	
	
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código:" + codigo + 
				"\tNombre:" + nombre + 
				"\tFechaC:" + formato.format(fechaC) + 
				"\tTurno:" +  turno + 
				"\tBaja:" + baja);
	}
	public Empleado(int codigo, String nombre, Date fechaC, String turno, boolean baja) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaC = fechaC;
		this.turno = turno;
		this.baja = baja;
	}
	public Empleado() {
	
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaC() {
		return fechaC;
	}
	public void setFechaC(Date fechaC) {
		this.fechaC = fechaC;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	
	
}
