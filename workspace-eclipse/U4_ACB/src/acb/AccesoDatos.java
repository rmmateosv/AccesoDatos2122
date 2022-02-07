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
}
