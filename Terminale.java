import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Terminale {
    Scanner scan = new Scanner(System.in);
    String inputString = "";
    String nome, cognome, email, username, password, passwordRep;

    List<Utente> utenti = new ArrayList<>();
    List<Prodotto> prodotti = new ArrayList<>();

    private static Terminale instance = null;
    private final static String name = "Terminale 1";

    private Terminale() {
        utenti.add(new Utente("Admin", "Admin", "admin@admin.com", "admin", "admin", Permessi.ADMINISTRATOR));
    }

    public static Terminale getInstance() {
        if (instance == null){
            instance = new Terminale();

        }
        return instance;
    }

    public boolean registrazione() {

        boolean found;

        do {
        
        found=false;

        System.out.println("Inserire nome: ");
        do {
            nome = scan.next();
            if (nome.isEmpty()) {
                System.out.println("Inserisci un nome valido");
            }
        }while(nome.isEmpty());
        
        System.out.println("Inserire cognome: ");
        do {
            cognome = scan.next();
            if (cognome.isEmpty()) {
                System.out.println("Inserisci un cognome valido");
            }
        }while(cognome.isEmpty());

        System.out.println("Inserire email: ");
        do {
            email = scan.next();
            if (email.isEmpty()) {
                System.out.println("Inserisci un email valido");
            }
        }while(email.isEmpty());
        System.out.println("Inserire username: ");
        do {
            username = scan.next();
            if (username.isEmpty()) {
                System.out.println("Inserisci un username valido");
            }
        }while(username.isEmpty());

        do {
            System.out.println("Inserire password: ");
            do {
                password = scan.next();
                if (password.isEmpty()) {
                    System.out.println("Inserisci un password valido");
                }
            }while(password.isEmpty());

            System.out.println("Ripeti password: ");
            do {
                passwordRep = scan.next();
                if (passwordRep.isEmpty()) {
                    System.out.println("Inserisci un passwordRep valido");
                }
            }while(passwordRep.isEmpty());

            if(!(password.equalsIgnoreCase(passwordRep))){
                System.out.println("Le due password devono coincidere");
            }

        } while (!(password.equalsIgnoreCase(passwordRep)));

        for (Utente utente : utenti) {
            if(utente.getEmail().equalsIgnoreCase(email) || utente.getUsername().equalsIgnoreCase(username)){
                found=true;
                break;
            }
        }

        if(found){
            System.out.println("Esiste già un utente registrato con questa mail o con questo username. ");
            return false;
        }

        }while(found);

        utenti.add(new Utente(nome, cognome, email, username, password));

        return true;

    }

    public Utente login() {


        System.out.println("Inserire username: ");
        do {
            username = scan.next();
            if (username.isEmpty()) {
                System.out.println("Inserisci un username valido");
            }
        }while(username.isEmpty());

        
        System.out.println("Inserire password: ");
        do {
            password = scan.next();
            if (password.isEmpty()) {
                System.out.println("Inserisci una password valida");
            }
        }while(password.isEmpty());

        for (Utente utente : utenti) {
            if(utente.getPassword().equalsIgnoreCase(password) && utente.getUsername().equalsIgnoreCase(username)){
                return utente;
            }
        }

        System.out.println("Email o password errate.");
        return null;
        

    }

    public void presentaMenu(){
        System.out.println("=================== MENU ===================");
        System.out.println("\t0) Esci");
        System.out.println("\t1) Visualizza i prodotti");
        System.out.println("\t2) Cerca prodotto");
    }

    public void presentaMenuAdmin(){
        System.out.println("\t3) Aggiungi un prodotto");
        System.out.println("\t4) Rimuovi un prodotto");
        System.out.println("\t5) Modifica un prodotto");
    }

    public boolean addProduct(){

        String nomeP, descrizioneP;
        double prezzoP=0.0;
        int quantitàP;
        boolean found;

        do {
        
        found=false;

        System.out.println("Inserire nome prodotto: ");
        do {
            nomeP = scan.next();
            if (nomeP.isEmpty()) {
                System.out.println("Inserisci un nome valido");
            }
        }while(nomeP.isEmpty());
        
        System.out.println("Inserire prezzo: ");
        do {

            try {
                prezzoP = scan.nextDouble();
            } catch (Exception e) {
                System.out.println("Input non valido. Assicurati di inserire un numero decimale valido. INT,DEC");
                break;
            }
            
            if (prezzoP<=0) {
                System.out.println("Inserisci un prezzo valido");
            }
        }while(prezzoP<=0);

        System.out.println("Inserire quantità: ");
        do {
            quantitàP = scan.nextInt();
            if (quantitàP<1) {
                System.out.println("Inserisci una quantità valida");
            }
        }while(quantitàP<1);

        System.out.println("Inserire descrizione: ");
        do {
            descrizioneP = scan.next();
            if (descrizioneP.isEmpty()) {
                System.out.println("Inserisci una descrizione valida");
            }
        }while(descrizioneP.isEmpty());

        for (Prodotto prodotto : prodotti) {
            if(prodotto.getNomeProdotto().equalsIgnoreCase(nomeP)){
                found=true;
                break;
            }
        }

        if(found){
            System.out.println("Esiste già un utente registrato con questa mail o con questo username. ");
            return false;
        }

        }while(found);

        prodotti.add(new Prodotto(nomeP,prezzoP,quantitàP,descrizioneP));

        return true;
    }


    public boolean removeProduct(){

        String nomeP;
        boolean found;
        Prodotto toRemove=null;

        found=false;

        System.out.println("Inserire nome o codice prodotto: ");
        do {
            nomeP = scan.next();
            if (nomeP.isEmpty()) {
                System.out.println("Inserisci valori validi");
            }
        }while(nomeP.isEmpty());
        
        
        for (Prodotto prodotto : prodotti) {
            if(prodotto.getNomeProdotto().equalsIgnoreCase(nomeP) || prodotto.getCodiceProdotto().equalsIgnoreCase(nomeP)){
                found=true;
                toRemove=prodotto;
                break;
            }
        }


        if(!found){
            System.out.println("Non esiste alcun prodotto con quel nome o codice ");
            return false;
        }

        prodotti.remove(toRemove);

        return true;
    }

    public void showDetails(){

        if (prodotti.size()!=0) {
            for (Prodotto prodotto : prodotti) {
                System.out.println("\n____________________________________\n" + prodotto);
            }
        }
        else{
            System.out.println("No products yet");
        }
        
    }

    public boolean findProduct(){
        String nomeP;
        boolean found;
        Prodotto toRemove=null;

        found=false;

        System.out.println("Inserire nome o codice prodotto: ");
        do {
            nomeP = scan.next();
            if (nomeP.isEmpty()) {
                System.out.println("Inserisci valori validi");
            }
        }while(nomeP.isEmpty());
        
        
        for (Prodotto prodotto : prodotti) {
            if(prodotto.getNomeProdotto().equalsIgnoreCase(nomeP) || prodotto.getCodiceProdotto().equalsIgnoreCase(nomeP)){
                found=true;
                System.out.println(prodotto);
                break;
            }
        }


        if(!found){
            System.out.println("Non esiste alcun prodotto con quel nome o codice ");
            return false;
        }

        return true;
    }

    public boolean modifyProduct(){

        String nomeP, inputString;
        boolean found, end=false;
        Prodotto toModify=null;
        int inputInt, index;
        double inputDouble;

        found=false;

        System.out.println("Inserire nome o codice prodotto: ");
        do {
            nomeP = scan.next();
            if (nomeP.isEmpty()) {
                System.out.println("Inserisci valori validi");
            }
        }while(nomeP.isEmpty());
        
        
        for (Prodotto prodotto : prodotti) {
            if(prodotto.getNomeProdotto().equalsIgnoreCase(nomeP) || prodotto.getCodiceProdotto().equalsIgnoreCase(nomeP)){
                found=true;
                toModify=prodotto;
                break;
            }
        }


        if(!found){
            System.out.println("Non esiste alcun prodotto con quel nome o codice ");
            return false;
        }

        do{
            inputString = null;
            inputInt=0;
            inputDouble=0;
            do{
                System.out.println("Inserire in numero associato per modificare: ");
                System.out.println("1) Nome");
                System.out.println("2) Quantità");
                System.out.println("3) Prezzo");
                System.out.println("4) Descrizione");

                inputInt = scan.nextInt();

                if (inputInt<1 || inputInt>4) {
                    System.out.println("Inserisci un input corretto. ");
                }
            }while(inputInt<1 || inputInt>4);

            switch (inputInt) {
                case 1:
                    do {
                        System.out.println("Inserisci nuovo nome: ");
                        inputString = scan.next();
                        if((inputString.equals("")) || (inputString.equals(toModify.getNomeProdotto()))){
                            System.out.println("Il nuovo nome non può essere nè vuoto, nè uguale al precedente");
                        }
                    }while((inputString.isEmpty()) || (inputString.equals(toModify.getNomeProdotto())));
                    index = prodotti.indexOf(toModify);
                    toModify.setNomeProdotto(inputString);
                    prodotti.set(index, toModify);
                    break;
                case 2:
                    do {
                        System.out.println("Inserisci nuova quantità: ");
                        inputInt = scan.nextInt();
                        if((inputInt<0) || (inputInt == toModify.getQuantità())){
                            System.out.println("La nuova quantità non può essere nè 0, nè uguale alla precedente");
                        }
                    }while((inputInt<0) || (inputInt == toModify.getQuantità()));
                    index = prodotti.indexOf(toModify);
                    toModify.setQuantità(inputInt);
                    prodotti.set(index, toModify);
                    break;
                case 3:
                    do {
                        System.out.println("Inserisci nuovo prezzo: ");
                        inputDouble = scan.nextInt();
                        if((inputDouble<0) || (inputDouble == toModify.getPrezzo())){
                            System.out.println("Il nuovo prezzo non può essere nè 0, nè uguale al precedente");
                        }
                    }while((inputDouble<0) || (inputDouble == toModify.getPrezzo()));
                    index = prodotti.indexOf(toModify);
                    toModify.setPrezzo(inputDouble);
                    prodotti.set(index, toModify);
                    break;
                case 4:
                    do {
                        System.out.println("Inserisci nuova descrizione: ");
                        inputString = scan.next();
                        if((inputString.isEmpty()) || (inputString.equals(toModify.getDescrizioneProdotto()))){
                            System.out.println("La nuova descrizione non può essere nè vuota, nè uguale alla precedente");
                        }
                    }while((inputString.isEmpty()) || (inputString.equals(toModify.getDescrizioneProdotto())));
                    index = prodotti.indexOf(toModify);
                    toModify.setDescrizioneProdotto(inputString);
                    prodotti.set(index, toModify);
                    break;
                default:
                    break;
            }

            System.out.println("Continuare con altre modifiche? 0 per sì, sennò qualsiasi altro tasto");
            inputString = scan.next();

            end = (!(inputString.equals("0"))) ? true : false;
            
        }while(!end);

        return true;
    }
}

