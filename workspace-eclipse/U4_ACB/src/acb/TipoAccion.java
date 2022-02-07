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
public class TipoAccion {
	@Id
	private String tipo;
	@Column(nullable = false)
	private String descrip;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
	private List<Accion> acciones = new ArrayList<Accion>();

	
	public void mostrar() {
		System.out.println("Tipo:"+tipo + 
				"\tDescripcion:"+ descrip);
	}
	public TipoAccion(String tipo, String descrip, List<Accion> acciones) {
		super();
		this.tipo = tipo;
		this.descrip = descrip;
		this.acciones = acciones;
	}

	public TipoAccion() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	
}
