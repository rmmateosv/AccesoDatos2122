package acb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Accion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="partido",referencedColumnName = "codigo")
	private Partido partido;
	@ManyToOne
	@JoinColumn(name="tipo",referencedColumnName = "tipo")
	private TipoAccion tipo;
	@ManyToOne
	@JoinColumn(name="jugador",referencedColumnName = "codigo")
	private Jugador jugador;
	private boolean anulada;
	
	public Accion(int codigo, Partido partido, TipoAccion tipoAccion, Jugador jugador, boolean anulada) {
		super();
		this.codigo = codigo;
		this.partido = partido;
		this.tipo = tipoAccion;
		this.jugador = jugador;
		this.anulada = anulada;
	}
	
	public void mostrar() {
		System.out.println(
				"Codigo:" + codigo
				+ "\tPartido:" + partido.getLocal().getNombre() + 
				  "-"+partido.getVisitante().getNombre()
				+ "\tTipoAccion:" + tipo.getDescrip()
				+ "\tJugador:" + jugador.getNombre() +"-" +
				                 jugador.getDorsal()
				+ "\tAnulada:" +  anulada);
	}

	public Accion() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public TipoAccion getTipoAccion() {
		return tipo;
	}

	public void setTipoAccion(TipoAccion tipoAccion) {
		this.tipo = tipoAccion;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public boolean isAnulada() {
		return anulada;
	}

	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}
	
	
}
