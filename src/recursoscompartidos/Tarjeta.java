package recursoscompartidos;

import hilos.Persona;

/**
 * The Class Tarjeta.
 * 
 * Esta es la clase de la tarjeta al que intentaran acceder las personas.
 * 
 * @author Daniil Kuradchyk
 */
public class Tarjeta {
	
	/** The id tarjeta. el identificador de la tarjeta*/
	private int idTarjeta;
	
	/** The ocupada. para comprogar si la tarjeta esta ocupada*/
	private boolean ocupada;
	
	/** The persona. para saber a quien le pertenece la tarjeta en ese momento*/
	private Persona persona;

	/**
	 * Constructor de la tarjeta
	 *
	 * @param idTarjeta the id tarjeta
	 */
	public Tarjeta(int idTarjeta) {
		this.idTarjeta=idTarjeta;
		this.ocupada=false;
		this.persona = null;
	}

	/**
	 * Gets the id tarjeta.
	 *
	 * @return the id tarjeta
	 */
	public int getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * Sets the id tarjeta.
	 *
	 * @param idTarjeta the new id tarjeta
	 */
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	/**
	 * Checks if is ocupada.
	 *
	 * @return true, if is ocupada
	 */
	public boolean isOcupada() {
		return ocupada;
	}

	/**
	 * Sets the ocupada.
	 *
	 * @param ocupada the new ocupada
	 */
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	/**
	 * Gets the persona.
	 *
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * Sets the persona.
	 *
	 * @param persona the new persona
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
