package console.consoleit.tools;
import org.mindrot.jbcrypt.BCrypt;


public class HasherMdp {
    public boolean hasherMotDePasse(String hash, String password) {
        return BCrypt.checkpw(password, hash);
    }
}
