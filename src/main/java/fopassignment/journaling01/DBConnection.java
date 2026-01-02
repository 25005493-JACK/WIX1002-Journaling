//TanWeiFengStart
//For SQL connection
package fopassignment.journaling01;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DBConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties not found in resources folder");
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}
//TanWeiFengEnd