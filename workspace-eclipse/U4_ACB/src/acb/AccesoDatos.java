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
			resultado = conexion.find(TipoAccion.class, tipo);			
			
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
					+ "where codigo = ?1 and"
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
}
