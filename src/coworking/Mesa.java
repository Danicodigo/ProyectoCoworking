package coworking;

public class Mesa {
	static Tarjeta[] tarjetas = new Tarjeta[5];
	static Persona[] personas = new Persona[5];

	public static void main(String[] args) {
		EntradaTarjeta entrada = new EntradaTarjeta();
		PC pc = new PC();

		for (int i = 0; i < tarjetas.length; i++) {
			tarjetas[i] = new Tarjeta(i);
		}

		for (int i = 0; i < personas.length - 1; i++) {
			personas[i] = new Persona(i, i, i + 1, pc, entrada);
		}
		personas[personas.length - 1] = new Persona(personas.length - 1, personas.length - 1, 0, pc, entrada);
	}

	public static synchronized void showMessage(String s) {
		System.out.println(s);
	}

	public static synchronized Tarjeta cogerTarjeta(int idTarjeta) {
		return tarjetas[idTarjeta];
	}

	public static synchronized boolean compruebaTarjeta(int idTarjeta) {
		return tarjetas[idTarjeta].isOcupada();
	}
}
