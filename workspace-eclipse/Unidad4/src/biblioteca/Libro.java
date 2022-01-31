package biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
public class Libro implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2077981661327561318L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(nullable = false, unique = true)
	private String isbn;	
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String autor;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaLanzamiento;
	@Column(nullable = false)
	private int numEjemplares;
	
	//RElación 1 libro muchos préstamos
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "clave.libro")
	private List<Prestamo> prestamos = new ArrayList<>();
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}


	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}


	public Libro() {
		
	}

	
	public Libro(int id, String isbn, String titulo, String autor, Date fechaLanzamiento, int numEjemplares) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaLanzamiento = fechaLanzamiento;
		this.numEjemplares = numEjemplares;
	}


	public Libro(String isbn, String titulo, String autor, Date fechaLanzamiento, int numEjemplares) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaLanzamiento = fechaLanzamiento;
		this.numEjemplares = numEjemplares;
	}
	
	public void mostrar(boolean mostrarPrestamos) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
		System.out.println("ISBN:" + isbn + 
				"\tTitulo:" + titulo + 
				"\tAutor:" + autor +
				"\tFecha:" + formato.format(fechaLanzamiento) +
				"\tEjemplares:" + numEjemplares);
		if(mostrarPrestamos) {
			System.out.println("Préstamos del libro:");
			for(int i=0;i<prestamos.size();i++) {
				prestamos.get(i).mostrar();
			}
		}
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getNumEjemplares() {
		return numEjemplares;
	}

	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}
	
	
	
}
