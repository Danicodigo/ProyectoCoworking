package coworking;

public class PC {

	private Object mutex;
	private boolean ocupado;
	public static int usos=0;

	public PC() {
		this.setOcupado(false);
		this.mutex = new Object();
	}

	public void entrarAlPC(int idPersona) throws InterruptedException {
		synchronized (this.mutex) {
			while (ocupado) {
				this.mutex.wait();
			}
			Mesa.showMessage("Persona " + idPersona + ": usando el pc");
			this.setOcupado(true);
			
		}
	}

	public void salirDelPC(int idPersona) {
		synchronized (this.mutex) {
			this.setOcupado(false);
			Mesa.showMessage("Persona " + idPersona + ": saliendo del pc");
			this.mutex.notifyAll();
			PC.usos++;
			
		}
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
