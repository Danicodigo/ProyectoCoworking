package coworking;

public class EntradaTarjeta {
	
	public Tarjeta cogerTarjetaPrueba(int idTarjeta, Persona p) throws InterruptedException {
		Tarjeta tar = Mesa.cogerTarjeta(idTarjeta);
		int espera = 0;
			synchronized (tar) {
				while (tar.isOcupada()) {
					tar.wait(50);
					espera++;
					if (espera >= 2 && tar.isOcupada()) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		}
	
	public void soltarTarjetaPrueba(Tarjeta tarjeta, int idPersona) {
		Tarjeta tar = tarjeta;
			synchronized (tar) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tar.notifyAll();
			}
		}
	

}
