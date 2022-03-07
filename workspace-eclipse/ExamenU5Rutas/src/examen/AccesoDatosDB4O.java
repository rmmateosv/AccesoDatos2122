package examen;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;



public class AccesoDatosDB4O {
	private ObjectContainer conexion = null;

	public AccesoDatosDB4O() {
		
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();	
	
		
		conexion = Db4oEmbedded.openFile(config,"rutas.obj");
	}

	public ObjectContainer getConexion() {
		return conexion;
	}

	public void setConexion(ObjectContainer conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		try {
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
