//TanWeiFengStart
//Password Encoder Decoder
package fopassignment.journaling01;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class pwHashing {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
//TanWeiFengEnd
