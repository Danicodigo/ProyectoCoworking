package coworking;

import hilos.Persona;
import recursoscompartidos.EntradaTarjeta;
import recursoscompartidos.Ordenador;
import recursoscompartidos.Tarjeta;

/**
 * The Class CoworkingMain.
 * 
 * Clase principal que se encarga de crear lo hilos y los recursos compartidos.
 * 
 * @author Daniil Kuradchyk
 */
public class CoworkingMain {
	
	/**  tarjetas.  el array de tarjetas*/
	static Tarjeta[] tarjetas = new Tarjeta[5];
	
	/** personas. el array de personas*/
	static Persona[] personas = new Persona[5];

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EntradaTarjeta entradaTar = new EntradaTarjeta();
		Ordenador ordenador = new Ordenador();
		/** Bucle que se encarga de crear las tarjetas**/
		for (int i = 0; i < tarjetas.length; i++) {
			tarjetas[i] = new Tarjeta(i);
		}
		/** Bucle que se encarga de crear las personas**/
		for (int i = 0; i < personas.length - 1; i++) {
			personas[i] = new Persona(i, i, i + 1, ordenador, entradaTar);
		}
		personas[4] = new Persona(4, 4, 0, ordenador, entradaTar);
	}

	/**
	 * Muestra mensaje.
	 * Muestra un mensaje de forma sincronizada con un formato.
	 *
	 * @param s La cadena de textos.
	 */
	public static synchronized void muestraMensaje(String s) {
		System.out.println("   " + s);
		System.out.println("|--                                                --|");
	}

	/**
	 * Coger tarjeta.
	 *
	 *Se encarga de devolver la tarjeta del array gracias al id de la tarjeta.
	 *
	 * @param idTarjeta id de la tarjeta
	 * @return tarjeta
	 */
	public static synchronized Tarjeta cogerTarjeta(int idTarjeta) {
		return tarjetas[idTarjeta];
	}
}
