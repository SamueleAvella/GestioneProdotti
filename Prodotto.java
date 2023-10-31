/*
 * Nome del prodotto (una stringa che rappresenta il nome del prodotto), 
 * Prezzo del prodotto (un valore numerico che rappresenta il prezzo del prodotto) 
 * Quantità disponibile (un valore numerico che rappresenta la quantità di prodotti disponibili)
 */

class Prodotto {

    private static Integer codiceProdottoProgressivo=1;
    private final String codiceProdotto;
    private String nomeProdotto;
    private double prezzo;
    private int quantità;
    private String descrizioneProdotto;

    public Prodotto(String nomeProdotto, double prezzo, int quantità, String descrizioneProdotto){
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
        this.quantità=quantità;
        this.descrizioneProdotto = descrizioneProdotto;
        codiceProdotto = "PROD_" + codiceProdottoProgressivo.toString();
        codiceProdottoProgressivo++;
    }

    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    public String getDescrizioneProdotto() {
        return descrizioneProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantità() {
        return quantità;
    }

    @Override
    public String toString() {
        String descrizione="";

        descrizione += "Nome prodotto: " + this.getNomeProdotto();
        descrizione += "\tCod. : " + this.getCodiceProdotto();
        descrizione += "\nPrezzo Euro: " + this.getPrezzo();
        descrizione += "\tQta. : " + this.getQuantità();
        descrizione += "\nDesc.: " + this.getDescrizioneProdotto();

        return descrizione;
    }

    public void setDescrizioneProdotto(String descrizioneProdotto) {
        this.descrizioneProdotto = descrizioneProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }



    

}
