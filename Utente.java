
/**
 * Utente
 */
public class Utente {

    private static Integer codiceClientePrograssivo=1;
    private final String codiceCliente;
    private String nome, cognome, email, username, password;
    private Permessi permesso=Permessi.BASIC_USER;

    public Utente(String nome, String cognome, String email, String username, String password){
        this.nome= nome;
        this.cognome=cognome;
        this.email=email;
        this.username=username;
        this.password = password;
        codiceCliente = "ID_" + codiceClientePrograssivo.toString();
        codiceClientePrograssivo++;
    }

    public Utente(String nome, String cognome, String email, String username, String password, Permessi permesso){
        this(nome,cognome,email,username,password);
        this.permesso = permesso;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public static Integer getCodiceClientePrograssivo() {
        return codiceClientePrograssivo;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public Permessi getPermesso() {
        return permesso;
    }

    public String getUsername() {
        return username;
    }

    
}