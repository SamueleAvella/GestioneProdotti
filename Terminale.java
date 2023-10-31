import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Terminale {
    Scanner scan = new Scanner(System.in);
    String inputString = "";
    String nome, cognome, email, username, password, passwordRep;

    List<Utente> utenti = new ArrayList<>();

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

            if(!(password.equals(passwordRep))){
                System.out.println("Le due password devono coincidere");
            }

        } while (!(password.equals(passwordRep)));

        for (Utente utente : utenti) {
            if(utente.getEmail().equals(email) || utente.getUsername().equals(username)){
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
            if(utente.getPassword().equals(password) && utente.getUsername().equals(username)){
                return utente;
            }
        }

        System.out.println("Email o password errate.");
        return null;
        

    }

    public void presentaMenu(){
        System.out.println("=================== MENU ===================");
        System.out.println("\t0) Esci");
        System.out.println("\t1) Visualizza dettagli di un prodotto");
        System.out.println("\t2) Visualizza quanti prodotti sono disponibili");
    }

    public void presentaMenuAdmin(){
        System.out.println("\t3) Aggiungi un prodotto");
        System.out.println("\t4) Rimuovi un prodotto");
    }


}

