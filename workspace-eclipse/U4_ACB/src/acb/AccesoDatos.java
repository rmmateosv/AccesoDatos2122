package acb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AccesoDatos {
	EntityManager conexion = null;

	public AccesoDatos() {
		try {
			
			conexion = 
					Persistence.
					createEntityManagerFactory("UP_ACB").
					createEntityManager();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void cerrar() {
		try {
			conexion.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public EntityManager getConexion() {
		return conexion;
	
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}

	public List<Partido> obtenerPartidos() {
		// TODO Auto-generated method stub
		List<Partido> resultado = new ArrayList<Partido>();
		try {
			Query consulta = conexion.createQuery("from Partido");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Partido obtenerPartido(int codigo) {
		// TODO Auto-generated method stub
		Partido resultado = null;
		try {
			resultado = conexion.find(Partido.class, codigo);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<TipoAccion> obtenerTiposAcc() {
		// TODO Auto-generated method stub
		List<TipoAccion> resultado = new ArrayList<TipoAccion>();
		try {
			Query consulta = conexion.createQuery("from TipoAccion");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public TipoAccion obtenerTipoAccion(char tipo) {
		// TODO Auto-generated method stub
		TipoAccion resultado = null;
		try {
			resultado = conexion.find(TipoAccion.class, String.valueOf(tipo));			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Jugador obtenerJugador(int codigoJ, Equipo local, Equipo visit) {
		// TODO Auto-generated method stub
		Jugador resultado = null;
		try {
			Query consulta = conexion.createQuery(
					"from Jugador "
					+ "where codigo = ?1 and "
					+ "equipo = ?2 or equipo = ?3");	
			consulta.setParameter(1, codigoJ);
			consulta.setParameter(2, local);
			consulta.setParameter(3, visit);
			
			List<Jugador> r = consulta.getResultList();
			if(r!=null) {
				resultado = (Jugador) r.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean registrarAccion(Partido partidoSeleccionado, Jugador j, TipoAccion tipoAccion) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Accion a = new Accion();
			a.setTipoAccion(tipoAccion);
			a.setPartido(partidoSeleccionado);
			a.setJugador(j);
			a.setAnulada(false);
			partidoSeleccionado.getAcciones().add(a);
			conexion.getTransaction().begin();
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean anularAccion(Partido partidoSeleccionado, int codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//buscamos la acción en la lista de acciones del partido
			for(Accion a:partidoSeleccionado.getAcciones()) {
				if(a.getCodigo() == codigo) {
					a.setAnulada(true);
					conexion.getTransaction().begin();
					conexion.getTransaction().commit();
					conexion.clear();
					return true;					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}
}
