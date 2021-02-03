
package supermercato;

public class Articolo {
    private double prezzo;
    private String codice;
    private String Nome;
    

    public Articolo(double prezzo, String codice, String Nome){ 
        this.prezzo = prezzo;
        this.codice = codice;
        this.Nome = Nome;
        
    }

    public Articolo() {
    }

    
    public double getPrezzo() {
        return prezzo;
    }

  
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

   
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return Nome;
    }

    
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    
}
