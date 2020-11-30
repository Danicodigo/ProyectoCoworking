package coworking;

import java.util.Random;

public class Persona implements Runnable {

	Thread t;
	private int idPersona;
	private Tarjeta tarjetaIzquierda;
	private Tarjeta tarjetaDerecha;
	private int idTarjetaIzquierda, idTarjetaDerecha;
	private PC pc;
	private Random rnd = new Random();
	private EntradaTarjeta entrada;
	private int usos;

	public Persona(int idPersona, int izquierda, int derecha, PC pc, EntradaTarjeta e) {
		this.setTarjetaDerecha(null);
		this.setTarjetaIzquierda(null);
		this.setIdPersona(idPersona);
		this.setIdTarjetaIzquierda(izquierda);
		this.setIdTarjetaDerecha(derecha);
		this.setPc(pc);
		this.setEntrada(e);
		t = new Thread(this);
		this.usos=0;
		t.start();
	}

	@Override
	public void run() {
		Mesa.showMessage("Persona " + idPersona + ": sentandose");
		try {
		while (usos <2 ) {
			
				this.pensar();
				this.cogerTarjetas();
				this.entrarAlPC();
				this.salirDelPC();
				usos++;
				Mesa.showMessage(""+usos);
		
		}
		Mesa.showMessage("Persona " + idPersona + ": saliendo");
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private void salirDelPC() {
		this.pc.salirDelPC(idPersona);
		this.soltarTarjetas();
	}

	private void entrarAlPC() throws InterruptedException {
		Mesa.showMessage("Persona " + idPersona + ": intentando entrar al pc");
		this.pc.entrarAlPC(idPersona);
		Thread.sleep(rnd.nextInt(1000));
	}

	private void cogerTarjetas() throws InterruptedException {
		Mesa.showMessage("Persona " + idPersona + ": cogiendo tarjetas");
		while (tarjetaIzquierda == null || tarjetaDerecha == null) {
			if (tarjetaIzquierda == null) {
				tarjetaIzquierda = entrada.cogerTarjetaPrueba(idTarjetaIzquierda, this);
			}
			if (tarjetaDerecha == null) {
				tarjetaDerecha = entrada.cogerTarjetaPrueba(idTarjetaDerecha, this);
			}
		}
		Mesa.showMessage("Persona " + idPersona + ": tarjetas cogidas");
	}

	private void soltarTarjetas() {
		this.soltarTarjetaIzquierda();
		this.soltarTarjetaDerecha();
	}

	public void soltarTarjeta() {
		if (this.tarjetaIzquierda != null) {
			this.soltarTarjetaIzquierda();
		}
		if (this.tarjetaDerecha != null) {
			this.soltarTarjetaDerecha();
		}
	}

	public void soltarTarjetaIzquierda() {
		entrada.soltarTarjetaPrueba(tarjetaIzquierda, idPersona);
		this.setTarjetaIzquierda(null);
	}

	public void soltarTarjetaDerecha() {
		entrada.soltarTarjetaPrueba(tarjetaDerecha, idPersona);
		this.setTarjetaDerecha(null);
	}

	private void pensar() throws InterruptedException {
		Mesa.showMessage("Persona " + this.idPersona + ": pensando");
		Thread.sleep(rnd.nextInt(500));
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Tarjeta getTarjetaIzquierda() {
		return tarjetaIzquierda;
	}

	public void setTarjetaIzquierda(Tarjeta tarjetaIzquierda) {
		this.tarjetaIzquierda = tarjetaIzquierda;
	}

	public Tarjeta getTarjetaDerecha() {
		return tarjetaDerecha;
	}

	public void setTarjetaDerecha(Tarjeta tarjetaDerecha) {
		this.tarjetaDerecha = tarjetaDerecha;
	}

	public int getIdTarjetaIzquierda() {
		return idTarjetaIzquierda;
	}

	public void setIdTarjetaIzquierda(int idTarjetaIzquierda) {
		this.idTarjetaIzquierda = idTarjetaIzquierda;
	}

	public int getIdTarjetaDerecha() {
		return idTarjetaDerecha;
	}

	public void setIdTarjetaDerecha(int idTarjetaDerecha) {
		this.idTarjetaDerecha = idTarjetaDerecha;
	}

	public PC getPc() {
		return pc;
	}

	public void setPc(PC pc) {
		this.pc = pc;
	}

	public EntradaTarjeta getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaTarjeta entrada) {
		this.entrada = entrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersona;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (idPersona != other.idPersona)
			return false;
		return true;
	}

}
