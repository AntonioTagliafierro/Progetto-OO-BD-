package entity;

import java.util.ArrayList;
import java.util.Date;

public class Progetto {

	// Attributi
	private String codProgetto;
	private String nomeProgetto;
	private String tipologiaProgetto;
	private Date dataInizioProgetto;
	private Date dataScadenzaProgetto;
	private String statusProgetto;
	private String nomeCliente;
	private String descrizioneProgetto;
	private ArrayList<Ambito> ambiti;
	private PartecipanteProgetto projectManager;
	private ArrayList<PartecipanteProgetto> partecipantiProgetto;

	// Costruttori

	public Progetto(String codProgetto) {
		super();
		this.codProgetto = codProgetto;
	}

	public Progetto(String codProgetto, String nomeProgetto, String tipologiaProgetto, String statusProgetto) {
		super();
		this.codProgetto = codProgetto;
		this.nomeProgetto = nomeProgetto;
		this.tipologiaProgetto = tipologiaProgetto;
		this.statusProgetto = statusProgetto;
	}

	public Progetto(String nomeProgetto, String tipologiaProgetto, Date dataInizioProgetto, Date dataScadenzaProgetto,
			String statusProgetto, String nomeCliente, String descrizioneProgetto, ArrayList<Ambito> ambiti,
			PartecipanteProgetto projectManager, ArrayList<PartecipanteProgetto> partecipantiProgetto) {
		super();
		this.nomeProgetto = nomeProgetto;
		this.tipologiaProgetto = tipologiaProgetto;
		this.dataInizioProgetto = dataInizioProgetto;
		this.dataScadenzaProgetto = dataScadenzaProgetto;
		this.statusProgetto = statusProgetto;
		this.nomeCliente = nomeCliente;
		this.descrizioneProgetto = descrizioneProgetto;
		this.ambiti = ambiti;
		this.projectManager = projectManager;
		this.partecipantiProgetto = partecipantiProgetto;
	}

	// Getter e Setter

	public String getCodProgetto() {
		return codProgetto;
	}

	public void setCodProgetto(String codProgetto) {
		this.codProgetto = codProgetto;
	}

	public String getNomeProgetto() {
		return nomeProgetto;
	}

	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}

	public String getTipologiaProgetto() {
		return tipologiaProgetto;
	}

	public void setTipologiaProgetto(String tipologiaProgetto) {
		this.tipologiaProgetto = tipologiaProgetto;
	}

	public Date getDataInizioProgetto() {
		return dataInizioProgetto;
	}

	public void setDataInizioProgetto(Date dataInizioProgetto) {
		this.dataInizioProgetto = dataInizioProgetto;
	}

	public Date getDataScadenzaProgetto() {
		return dataScadenzaProgetto;
	}

	public void setDataScadenzaProgetto(Date dataScadenzaProgetto) {
		this.dataScadenzaProgetto = dataScadenzaProgetto;
	}

	public String getStatusProgetto() {
		return statusProgetto;
	}

	public void setStatusProgetto(String statusProgetto) {
		this.statusProgetto = statusProgetto;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescrizioneProgetto() {
		return descrizioneProgetto;
	}

	public void setDescrizioneProgetto(String descrizioneProgetto) {
		this.descrizioneProgetto = descrizioneProgetto;
	}

	public ArrayList<Ambito> getAmbiti() {
		return ambiti;
	}

	public void setAmbiti(ArrayList<Ambito> ambiti) {
		this.ambiti = ambiti;
	}

	public PartecipanteProgetto getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(PartecipanteProgetto projectManager) {
		this.projectManager = projectManager;
	}

	public ArrayList<PartecipanteProgetto> getPartecipantiProgetto() {
		return partecipantiProgetto;
	}

	public void setPartecipantiProgetto(ArrayList<PartecipanteProgetto> partecipantiProgetto) {
		this.partecipantiProgetto = partecipantiProgetto;
	}

}
