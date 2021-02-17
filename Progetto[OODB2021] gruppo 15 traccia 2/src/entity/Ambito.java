package entity;

import java.util.ArrayList;

public class Ambito {
	
	private String nomeAmbito;
	private ArrayList <Progetto> progetti;
	
	//Costruttore
	
	public Ambito(String nomeAmbito, ArrayList<Progetto> progetti) {
		super();
		this.nomeAmbito = nomeAmbito;
		this.progetti = new ArrayList <Progetto>();
	}

	public Ambito(String nomeAmbito) {
		super();
		this.nomeAmbito = nomeAmbito;
	}

	//Getter e Setter
	public String getNomeAmbito() {
		return nomeAmbito;
	}

	public void setNomeAmbito(String nomeAmbito) {
		this.nomeAmbito = nomeAmbito;
	}

	public ArrayList<Progetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(ArrayList<Progetto> progetti) {
		this.progetti = progetti;
	}
	
	

}
