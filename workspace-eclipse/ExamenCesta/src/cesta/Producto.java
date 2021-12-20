package cesta;

public class Producto {
	private int codigoR;
	private String codigoP, nombre;
	private int cantidad;
	private float importe;
	public Producto() {
		
	}
	public Producto(int codigoR, String codigoP, String nombre, int cantidad, float importe) {
		super();
		this.codigoR = codigoR;
		this.codigoP = codigoP;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.importe = importe;
	}
	
	public void mostrar() {
		System.out.println("codigoRegistro:" + codigoR + 
		"\tcodigoProducto:" + codigoP.trim() +
		"\tnombre:" + nombre.trim() +
		"\tcantidad:" + cantidad +
		"\tprecio:" + importe);
	}
	public int getCodigoR() {
		return codigoR;
	}
	public void setCodigoR(int codigoR) {
		this.codigoR = codigoR;
	}
	public String getCodigoP() {
		return codigoP;
	}
	public void setCodigoP(String codigoP) {
		this.codigoP = codigoP;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	
	
}
