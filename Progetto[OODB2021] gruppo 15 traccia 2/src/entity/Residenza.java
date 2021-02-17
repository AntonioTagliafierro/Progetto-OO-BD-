package entity;

public class Residenza {

	// Attributi

	private String città;
	private String provincia;
	private String cap;
	private String via;
	private String nCivico;
	private Dipendente dipendente;

	// Costruttore

	public Residenza(String città, String provincia, String cap, String via, String nCivico, Dipendente dipendente) {
		super();
		this.città = città;
		this.provincia = provincia;
		this.cap = cap;
		this.via = via;
		this.nCivico = nCivico;
		this.dipendente = dipendente;
	}
	

	public Residenza(String città, String provincia) {
		super();
		this.città = città;
		this.provincia = provincia;
	}


	public Residenza() {
	
	}


	// Getter e Setter
	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getnCivico() {
		return nCivico;
	}

	public void setnCivico(String nCivico) {
		this.nCivico = nCivico;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

}
