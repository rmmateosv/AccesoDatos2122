package acb;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private String nombre;
	private String localidad;
	
	private List<Jugador> jugadores = new ArrayList<>();
	private List<Partido> partidosLocal = new ArrayList<>();
	private List<Partido> partidosVisit = new ArrayList<>();
	public Equipo(String nombre, String localidad, List<Jugador> jugadores, List<Partido> partidosLocal,
			List<Partido> partidosVisit) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.jugadores = jugadores;
		this.partidosLocal = partidosLocal;
		this.partidosVisit = partidosVisit;
	}
	
	public void mostrar(boolean mostrarJ, 
			boolean mostrarL, boolean mostrarV) {
		System.out.println("Nombre:" + nombre
				+ "\tLocalidad:" + localidad);
		if(mostrarJ) {
			for(Jugador j:jugadores) {
				j.mostrar();
			}
		}
		if(mostrarL) {
			for(Partido p:partidosLocal) {
				p.mostrar();
			}
		}
		if(mostrarV) {
			for(Partido p:partidosVisit) {
				p.mostrar();
			}
		}
	}
	public Equipo() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	public List<Partido> getPartidosLocal() {
		return partidosLocal;
	}
	public List<Partido> getPartidosVisit() {
		return partidosVisit;
	}
	
	
}
