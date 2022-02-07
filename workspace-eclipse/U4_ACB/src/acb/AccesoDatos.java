package acb;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
}
