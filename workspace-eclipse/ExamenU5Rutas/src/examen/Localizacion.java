package examen;

public class Localizacion {
	private int latitud, longitud;

	public Localizacion(int latitud, int longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Localizacion() {
		super();
	}

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
}
