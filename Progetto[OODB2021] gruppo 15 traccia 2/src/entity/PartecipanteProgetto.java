package entity;

public class PartecipanteProgetto {
	
	//Attributi
	
	private Dipendente dipendente;
	private Progetto progetto;
	private String Ruolo;
	
	//Costruttore

	public PartecipanteProgetto(Dipendente dipendente, Progetto progetto, String ruolo) {
		super();
		this.dipendente = dipendente;
		this.progetto = progetto;
		Ruolo = ruolo;
	}

	//Getter e Setter
	
	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

	public String getRuolo() {
		return Ruolo;
	}

	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	
	

	
	
	

}
