package acb;

public class Accion {
	private int codigo;
	private Partido partido;
	private TipoAccion tipoAccion;
	private Jugador jugador;
	private boolean anulada;
	
	public Accion(int codigo, Partido partido, TipoAccion tipoAccion, Jugador jugador, boolean anulada) {
		super();
		this.codigo = codigo;
		this.partido = partido;
		this.tipoAccion = tipoAccion;
		this.jugador = jugador;
		this.anulada = anulada;
	}
	
	public void mostrar() {
		System.out.println(
				"Codigo:" + codigo
				+ "\tPartido:" 
				+ "\tTipoAccion:"
				+ "\tJugador:"
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
		return tipoAccion;
	}

	public void setTipoAccion(TipoAccion tipoAccion) {
		this.tipoAccion = tipoAccion;
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
