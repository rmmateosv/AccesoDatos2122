package examen;

import java.util.ArrayList;

public class Ruta {
	private String nombre;
	private ArrayList<Integer> lugares = new ArrayList();
	private int tiempo;
	
	
	public Ruta(String nombre, ArrayList<Integer> lugares, int tiempo) {
		super();
		this.nombre = nombre;
		this.lugares = lugares;
		this.tiempo = tiempo;
	}
	public Ruta() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Integer> getLugares() {
		return lugares;
	}
	public void setLugares(ArrayList<Integer> lugares) {
		this.lugares = lugares;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
}
