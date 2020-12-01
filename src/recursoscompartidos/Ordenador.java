package recursoscompartidos;

import coworking.CoworkingMain;
import hilos.Persona;

/**
 * The Class Ordenador.
 * 
 * La clase ordenador es un recurso comprartido al que accederan las personas.
 * 
 * @author Daniil Kuradchyk
 */
public class Ordenador {

	/** The llave. */
	private Object llave;
	
	/** The ocupado que comprobara si esta ocupado o no el ordenador */
	private boolean ocupado;

	/**
	 * Constructor de el ordenador
	 */
	public Ordenador() {
		this.ocupado=false;
		this.llave = new Object();
	}

	/**
	 * Entrar.
	 *
	 *Se encarga de comproba si el ordenador esta ocupado cuando intenta entrar una persona en caso de que lo este
	 *el hilo se queda esperando hasta ser depertado.
	 *
	 * @param p la Persona que quiere entrar al ordenador
	 * @throws InterruptedException the interrupted exception
	 */
	public void entrar(Persona p) throws InterruptedException {
		synchronized (this.llave) {
			while (ocupado) {
				this.llave.wait();
			}
			CoworkingMain.muestraMensaje(
					"La persona F" + p.getIdPersona() + " ha entrado al ordenador por " + p.getUsos() + "ª vez");
			this.setOcupado(true);

		}
	}


	/**
	 * Salir.
	 *
	 *Se encarga de hacer que el hilo salga del recurso compartido y notificar a los demas para que 
	 *puedan entrar 
	 *
	 * @param p Persona que sale del ordenador.
	 */
	public void salir(Persona p) {
		synchronized (this.llave) {
			this.setOcupado(false);
			CoworkingMain.muestraMensaje("La persona F" + p.getIdPersona() + " ha salido del ordenador");
			this.llave.notifyAll();

		}
	}

	/**
	 * Checks if is ocupado.
	 *
	 * @return true, if is ocupado
	 */
	public boolean isOcupado() {
		return ocupado;
	}

	/**
	 * Sets the ocupado.
	 *
	 * @param ocupado the new ocupado
	 */
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
