package acb;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager conexion = 
	Persistence.createEntityManagerFactory("UP_ACB").createEntityManager();
	}

}
