package recursoscompartidos;

import coworking.CoworkingMain;
import hilos.Persona;

/**
 * The Class EntradaTarjeta.
 * Esta clase se encargara de coger y soltar las tarjetas de de forma sincronizada 
 * para las personas.
 * 
 * @author Daniil Kuradchyk
 */
public class EntradaTarjeta {

	/**
	 * Metodo Coger.
	 * 
	 * Se encarga de de asignar una tarjeta a la persona comprobando si la tarjeta solicitada estocupada
	 * usando la llave de la misma tarjeta y en caso de que este ocupada realizara 2 intentos para intentar
	 * coger la tarjeta y en caso de que no la consiga soltara las tarjetas que tuviera en ese momento.
	 *
	 * @param idTarjeta el id de la tarjeta
	 * @param p la persona que solicita laarjeta
	 * @return la tarjeta
	 * @throws InterruptedException the interrupted exception
	 */
	public Tarjeta coger(int idTarjeta, Persona p) throws InterruptedException {
		Tarjeta tarjeta = CoworkingMain.cogerTarjeta(idTarjeta);
		int intentosCogerTar = 0;
		synchronized (tarjeta) {
			while (tarjeta.isOcupada()) {
				tarjeta.wait(75);
				intentosCogerTar++;
				if (intentosCogerTar >= 2 && tarjeta.isOcupada()) {
					p.soltarTarjetas();
					intentosCogerTar = 0;
				}
			}
			tarjeta.setOcupada(true);
			CoworkingMain.muestraMensaje(
					"La persona F" + p.getIdPersona() + " ha cogido la tarjeta: [" + tarjeta.getIdTarjeta()+"]");
			tarjeta.setPersona(p);
			return tarjeta;
		}
	}

	/**
	 * Soltar.
	 *
	 *Este metodo se encaga de soltar la tarjeta y notificar a los hilos que puedan cogerla
	 *comprobando que solo se despierten los que la puedan tener gracias a la tarjeta.
	 *
	 * @param tarjeta que quieres soltar
	 * @param idPersona id de la persona para poder mostrar el mensaje por pantalla
	 */
	public void soltar(Tarjeta tarjeta, int idPersona) {
		Tarjeta t = tarjeta;
		synchronized (t) {
			t.setOcupada(false);
			t.setPersona(null);
			CoworkingMain.muestraMensaje("La persona F" + idPersona + " ha soltado la tarjeta [" + t.getIdTarjeta()+"]");
			t.notifyAll();
		}
	}

}
