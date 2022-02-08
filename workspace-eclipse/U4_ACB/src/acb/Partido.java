package acb;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="local", referencedColumnName = "nombre")
	private Equipo local;
	@ManyToOne
	@JoinColumn(name="visitante", referencedColumnName = "nombre")
	private Equipo visitante;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partido")
	private List<Accion> acciones;

	public Partido(int codigo, Equipo local, Equipo visitante, List<Accion> acciones, Date fecha) {
		super();
		this.codigo = codigo;
		this.local = local;
		this.visitante = visitante;
		this.acciones = acciones;
		this.fecha = fecha;
	}
	
	public void mostrar(boolean mostrarAcc) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Codigo:"+ codigo
				+ "\tFecha:" + formato.format(fecha) 
				+ "\tLocal:"+ local.getNombre()+"-"
				            +local.getLocalidad()
				+ "\tVisitante:" + visitante.getNombre()+"-"
				                   +visitante.getLocalidad());
		if(mostrarAcc) {
			for(Accion a: acciones) {
				a.mostrar();
			}
		}
	}

	public Partido() {
		super();
	}

	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	

}
