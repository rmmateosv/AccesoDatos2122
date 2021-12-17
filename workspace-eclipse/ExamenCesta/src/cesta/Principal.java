package cesta;

import java.util.Scanner;

public class Principal {
	static Scanner t = new java.util.Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos("cesta.bin");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opcíon:");
			System.out.println("0-Salir");
			System.out.println("1-Opción 1");
			System.out.println("2-Opción 2");
			System.out.println("3-Opción 3");
			System.out.println("4-Opción 4");
		
			
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
				case 1: {
					anadirProducto();
					break;
				}
				case 2: {
					
					break;
				}
				case 3: {
					
					break;
				}
				case 4: {
					
					break;
				}
				case 5: {
					
					break;
				}
				case 6: {
					
					break;
				}
				
			}
			
		} while (opcion != 0);
		
	}
	private static void anadirProducto() {
		// TODO Auto-generated method stub
		System.out.println("Código de registro:");
		int codigoR = t.nextInt();t.nextLine();
		//Comprobar si existe
		Producto p = ad.obtenerProducto(codigoR);
		if(p==null) {
			p = new Producto();
			p.setCodigoR(codigoR);
			System.out.println("Codigo producto");
			p.setCodigoP(t.nextLine());
			System.out.println("Nombre");
			p.setNombre(t.nextLine());
			System.out.println("Cantidad");
			p.setCantidad(t.nextInt()); t.nextLine();
			System.out.println("Importe");
			p.setImporte(t.nextFloat()); t.nextLine();
			
			if(!ad.crearProducto(p)) {
				System.out.println("Error al crear el producto");
			}
			else {
				//Mostrar todos los productos
			}
		}
		else {
			System.out.println("Error, producto no existe");
		}
	}

}

