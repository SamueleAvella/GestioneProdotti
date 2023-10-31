import java.util.Scanner;
//MAIN
class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int inputInt = 0;
        boolean logged=false;
        Utente loggedUser=null;
        Terminale terminale1 = Terminale.getInstance();

        do {
            
        System.out.println("=================== TERMINALE PRONTO ===================");
        System.out.println("1) Registrati --- 2) Accedi --- 3) Uscita");
        do{
            inputInt = scan.nextInt();
            if(inputInt<1 || inputInt>3){
                System.out.println("Input errato, riprova");
            }

        }while(inputInt<1 || inputInt>3);

        switch (inputInt) {
            case 1:
                if(terminale1.registrazione()) {
                    System.out.println("Registrazione eseguita con successo. ");
                }
                else{
                    System.out.println("La registrazione non è stata eseguita.");
                }
                break;
            case 2:
                loggedUser = terminale1.login();
                if(loggedUser!=null){
                    System.out.println("Accesso eseguito");
                    logged=true;
                }
                else{
                    System.out.println("Accesso non eseguito");
                }
                break;
            case 3:
                System.out.println("Uscita dal terminale...");
                System.exit(0);
        
            default:
                break;
        }
        

        }while (!logged);

        do {

            terminale1.presentaMenu();
            if (loggedUser.getPermesso() == Permessi.ADMINISTRATOR) {
                terminale1.presentaMenuAdmin();
                do{
                    inputInt = scan.nextInt();
                    if (inputInt<0 || inputInt>5) {
                        System.out.println("Inserisci un input valido. ");
                    }
                }while(inputInt<0 || inputInt>5);
            }
            else{
                do{
                    inputInt = scan.nextInt();
                    if (inputInt<0 || inputInt>2) {
                        System.out.println("Inserisci un input valido. ");
                    }
                }while(inputInt<0 || inputInt>2);
            }

            switch (inputInt) {
                case 0:
                    System.out.println("Uscita...");
                    logged=false;
                    loggedUser=null;
                    break;
                case 1:
                    terminale1.showDetails();
                    break;
                case 2:
                    terminale1.findProduct();
                    break;
                case 3:
                    if (terminale1.addProduct()) {
                        System.out.println("Prodotto aggiunto");
                    }
                    else{
                        System.out.println("Prodotto non aggiunto");
                    }
                    break;
                case 4:
                    if (terminale1.removeProduct()) {
                        System.out.println("Prodotto rimosso");
                    }
                    else{
                        System.out.println("Prodotto non rimosso");
                    }
                    break;
                case 5:
                    terminale1.modifyProduct();
                default:

                    break;
            }

            
        }while (logged);

        

        

    }
}
