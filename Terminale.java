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
            nome = scan.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Inserisci un nome valido");
            }
        }while(nome.isEmpty());
        
        System.out.println("Inserire cognome: ");
        do {
            cognome = scan.nextLine();
            if (cognome.isEmpty()) {
                System.out.println("Inserisci un cognome valido");
            }
        }while(cognome.isEmpty());

        System.out.println("Inserire email: ");
        do {
            email = scan.nextLine();
            if (email.isEmpty()) {
                System.out.println("Inserisci un email valido");
            }
        }while(email.isEmpty());
        System.out.println("Inserire username: ");
        do {
            username = scan.nextLine();
            if (username.isEmpty()) {
                System.out.println("Inserisci un username valido");
            }
        }while(username.isEmpty());

        do {
            System.out.println("Inserire password: ");
            do {
                password = scan.nextLine();
                if (password.isEmpty()) {
                    System.out.println("Inserisci un password valido");
                }
            }while(password.isEmpty());

            System.out.println("Ripeti password: ");
            do {
                passwordRep = scan.nextLine();
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
            username = scan.nextLine();
            if (username.isEmpty()) {
                System.out.println("Inserisci un username valido");
            }
        }while(username.isEmpty());

        
        System.out.println("Inserire password: ");
        do {
            password = scan.nextLine();
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
    }

    public void presentaMenuAdmin(){
        System.out.println("\t2) Aggiungi un prodotto");
        System.out.println("\t3) Rimuovi un prodotto");
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
            nomeP = scan.nextLine();
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
            descrizioneP = scan.nextLine();
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
            nomeP = scan.nextLine();
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
                System.out.println("\n____________________________________" + prodotto);
            }
        }
        else{
            System.out.println("No products yet");
        }
        
    }
}

