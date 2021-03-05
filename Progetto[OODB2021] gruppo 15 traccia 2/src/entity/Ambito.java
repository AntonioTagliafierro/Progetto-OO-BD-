package entity;

import java.util.ArrayList;

public class Ambito {

	private String nomeAmbito;
	private Progetto progetto;

	// Costruttore

	public Ambito(String nomeAmbito) {
		super();
		this.nomeAmbito = nomeAmbito;
	}

	public Ambito() {
		// TODO Auto-generated constructor stub
	}

	// Getter e Setter
	public String getNomeAmbito() {
		return nomeAmbito;
	}

	public void setNomeAmbito(String nomeAmbito) {
		this.nomeAmbito = nomeAmbito;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

}
