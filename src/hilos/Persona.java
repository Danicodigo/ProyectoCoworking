package hilos;

import java.util.Random;

import coworking.CoworkingMain;
import recursoscompartidos.EntradaTarjeta;
import recursoscompartidos.Ordenador;
import recursoscompartidos.Tarjeta;
/**
 * The Class Persona.
 * 
 * Esta es la clase del hilo persona que sera la encargada de conseguir las 2 tarjetas
 * para poder entrar al ordenador.
 * 
 * @author Daniil Kuradchyk
 */
public class Persona implements Runnable {

	/** The t. */
	Thread t;
	
	/** The id persona. Es el identificador de las persona */
	private int idPersona;
	
	/** The id tar izq. es el id de la tarjeta izquierda */
	private int idTarIzq;
	
	/** The id tar der.el id de la tarjeta derecha */
	private int idTarDer;
	
	/** The t izquierda. es la tarjeta izquierda*/
	private Tarjeta tIzquierda;
	
	/** The t derecha. es la tarjeda derecha*/
	private Tarjeta tDerecha;
	
	/**el ordenador */
	private Ordenador ordenador;
	
	/** The entrada tar. se encargara de coger la tarjeta solicitada por la persona*/
	private EntradaTarjeta entradaTar;
	
	/** The usos. es el numero de veces que la persona ha usado el pc */
	private int usos;
	
	/** The rand. */
	private Random rand = new Random();

	/**
	 * Constructor de la persona.
	 *
	 * @param idPersona  id persona
	 * @param tizquierda  id de la tarjeta izquierda
	 * @param tderecha id de la tarjeta izquierda derecha
	 * @param ordenador el ordenador
	 * @param entradaTar la entrada Tarjeta
	 */
	public Persona(int idPersona, int tizquierda, int tderecha, Ordenador ordenador, EntradaTarjeta entradaTar) {
		this.idPersona = idPersona;
		this.idTarIzq = tizquierda;
		this.idTarDer = tderecha;
		this.ordenador = ordenador;
		this.tDerecha = null;
		this.tIzquierda = null;
		this.entradaTar = entradaTar;
		this.usos = 1;
		t = new Thread(this);
		t.start();
	}

	/**
	 * Run.
	 * 
	 * Este metodo es el encargado de llamar a todos los metodos para realizar las funciones nesesarias.
	 * 
	 */
	@Override
	public void run() {
		CoworkingMain.muestraMensaje("La persona F" + idPersona + " se ha sentado");
		try {
			while (usos != 4) {

				this.pensar();
				this.cogerTarjetas();
				this.entrarAlOrdenador();
				this.trabajaEnOrdenador();
				this.salirDelOrdenador();
				usos++;
			}
			CoworkingMain.muestraMensaje("La persona F" + idPersona + " ha terminado su trabajo");
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Trabaja en ordenador.
	 * Se encarga de dar un tiempo aleatorio a la persona para que trabaje en el ordenador
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void trabajaEnOrdenador() throws InterruptedException {
		CoworkingMain.muestraMensaje("La persona F" + this.idPersona + " esta trabajando en el ordenador");
		Thread.sleep(rand.nextInt(500));
	}

	/**
	 * Salir del ordenador.
	 * se encarga de salir del ordenador llamando al metodo salir del ordenador utilizado
	 * 
	 */
	private void salirDelOrdenador() {
		this.ordenador.salir(this);
		this.soltarTarjetas();
	}

	/**
	 * Entrar al ordenador.
	 *
	 *se encarga de entrar al ordenador llamando al metodo entrar del ordenador utilizado
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void entrarAlOrdenador() throws InterruptedException {
		CoworkingMain.muestraMensaje("La persona F" + idPersona + " intenta entrar al ordenador");
		this.ordenador.entrar(this);
	}

	/**
	 * Coger tarjetas.
	 *
	 *se encarga de coger las tarjetas hasta que las no consiga las dos tarjetas no acabara el metodo
	 *comprueba cual de las tarjetas estan a null para cogerlas gracias al metodo coger de las clase de EntradaTarjeta
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void cogerTarjetas() throws InterruptedException {
		CoworkingMain.muestraMensaje("La persona F" + idPersona + " cogiendo tarjetas");
		while (tIzquierda == null || tDerecha == null) {
			if (tIzquierda == null) {
				tIzquierda = entradaTar.coger(idTarIzq, this);
			}
			if (tDerecha == null) {
				tDerecha = entradaTar.coger(idTarDer, this);
			}
		}
		CoworkingMain.muestraMensaje("La persona F" + idPersona + " ha conseguido las tarjetas");
	}

	/**
	 * Soltar tarjetas.
	 * 
	 * Suelta las tarjetas comprobando cuales tiene asignadas.
	 */
	public void soltarTarjetas() {
		if (this.tIzquierda != null) {
			entradaTar.soltar(tIzquierda, idPersona);
			this.setTIzquierda(null);
		}
		if (this.tDerecha != null) {
			entradaTar.soltar(tDerecha, idPersona);
			this.setTDerecha(null);
		}
	}
	
	/**
	 * Pensar.
	 * 
	 *	Se encarga de dar un tiempo aleatorio a la persona para que piense una idea
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void pensar() throws InterruptedException {
		CoworkingMain.muestraMensaje("La persona F" + this.idPersona + " esta pensando una idea");
		Thread.sleep(rand.nextInt(1000));
	}

	/**
	 * Gets the id persona.
	 *
	 * @return the id persona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * Sets the id persona.
	 *
	 * @param idPersona the new id persona
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * Gets the t izquierda.
	 *
	 * @return the t izquierda
	 */
	public Tarjeta getTIzquierda() {
		return tIzquierda;
	}

	/**
	 * Sets the t izquierda.
	 *
	 * @param tarjetaIzquierda the new t izquierda
	 */
	public void setTIzquierda(Tarjeta tarjetaIzquierda) {
		this.tIzquierda = tarjetaIzquierda;
	}

	/**
	 * Gets the t derecha.
	 *
	 * @return the t derecha
	 */
	public Tarjeta getTDerecha() {
		return tDerecha;
	}

	/**
	 * Sets the t derecha.
	 *
	 * @param tarjetaDerecha the new t derecha
	 */
	public void setTDerecha(Tarjeta tarjetaDerecha) {
		this.tDerecha = tarjetaDerecha;
	}

	/**
	 * Gets the id tar izq.
	 *
	 * @return the id tar izq
	 */
	public int getIdTarIzq() {
		return idTarIzq;
	}

	/**
	 * Sets the id tar izq.
	 *
	 * @param idTarjetaIzquierda the new id tar izq
	 */
	public void setIdTarIzq(int idTarjetaIzquierda) {
		this.idTarIzq = idTarjetaIzquierda;
	}

	/**
	 * Gets the id tar der.
	 *
	 * @return the id tar der
	 */
	public int getIdTarDer() {
		return idTarDer;
	}

	/**
	 * Sets the id tar der.
	 *
	 * @param idTarjetaDerecha the new id tar der
	 */
	public void setIdTarDer(int idTarjetaDerecha) {
		this.idTarDer = idTarjetaDerecha;
	}

	/**
	 * Gets the ordenador.
	 *
	 * @return the ordenador
	 */
	public Ordenador getOrdenador() {
		return ordenador;
	}

	/**
	 * Sets the ordenador.
	 *
	 * @param ordenador the new ordenador
	 */
	public void setOrdenador(Ordenador ordenador) {
		this.ordenador = ordenador;
	}

	/**
	 * Gets the usos.
	 *
	 * @return the usos
	 */
	public int getUsos() {
		return usos;
	}

	/**
	 * Sets the usos.
	 *
	 * @param usos the new usos
	 */
	public void setUsos(int usos) {
		this.usos = usos;
	}

}
