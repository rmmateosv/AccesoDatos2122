package acb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="equipo", referencedColumnName = "nombre")
	private Equipo equipo;
	@Column(nullable = false)
	private int dorsal;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String tipo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private List<Accion> acciones = new ArrayList<>();

	
	public void mostrar(boolean mostrarAcc) {
		System.out.println("Codigo:"+ codigo
				+ "\tEquipo:"+ equipo.getNombre()+"-"+equipo.getLocalidad()
				+ "\tDorsal:" + dorsal
				+ "\tNombre:" + nombre
				+ "\tTipo:" + tipo);
		if(mostrarAcc) {
			for(Accion a: acciones) {
				a.mostrar();
			}
		}
	}
	public Jugador() {
		super();
	}

	public Jugador(int codigo, Equipo equipo, int dorsal, String nombre, String tipo, List<Accion> acciones) {
		super();
		this.codigo = codigo;
		this.equipo = equipo;
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.tipo = tipo;
		this.acciones = acciones;
	}

	public int getCodigo() {
		return codigo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public int getDorsal() {
		return dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}
	
	
}
