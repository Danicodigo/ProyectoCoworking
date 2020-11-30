package coworking;

public class Tarjeta {
	Object mutex = new Object();
	private int idTarjeta;
	private boolean ocupada;
	private Persona persona;

	public Tarjeta(int idTarjeta) {
		this.setIdTarjeta(idTarjeta);
		this.setOcupada(false);
	}

	public int getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTarjeta;
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
		Tarjeta other = (Tarjeta) obj;
		if (idTarjeta != other.idTarjeta)
			return false;
		return true;
	}

}
