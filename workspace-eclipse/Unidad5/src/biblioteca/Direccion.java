package biblioteca;

public class Direccion {
	private String calle;
	private int numero, cp;
	
	public Direccion(String calle, int numero, int cp) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.cp = cp;
	}
	public Direccion() {
		super();
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	

}
