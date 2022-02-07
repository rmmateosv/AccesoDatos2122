package acb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Equipo {
	@Id
	private String nombre;
	@Column(nullable = false)
	private String localidad;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "equipo")
	private List<Jugador> jugadores = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "local")
	private List<Partido> partidosLocal = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "visitante")
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
				j.mostrar(false);
			}
		}
		if(mostrarL) {
			for(Partido p:partidosLocal) {
				p.mostrar(false);
			}
		}
		if(mostrarV) {
			for(Partido p:partidosVisit) {
				p.mostrar(false);
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
