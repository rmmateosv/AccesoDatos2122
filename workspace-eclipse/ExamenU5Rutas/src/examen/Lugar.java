package examen;

public class Lugar {
	private int codigo;
	private String nombre;
	private Localizacion info;
	public Lugar(int codigo, String nombre, Localizacion info) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.info = info;
	}
	public Lugar() {
		super();
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
	public Localizacion getInfo() {
		return info;
	}
	public void setInfo(Localizacion info) {
		this.info = info;
	}
	public void mostrar() {
		// TODO Auto-generated method stub
		System.out.println("Lugar:" + codigo + 
				"\tNombre:"  + nombre +
				"\tLatitud:"+ info.getLatitud()+
				"\tLongitud:" + info.getLongitud());
	}
	
	
	
}	
