package fopassignment.journaling01;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {
    
    private static final Properties props = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            
            if (input == null) {
                System.err.println("Unable to find db.properties. Ensure it is in src/main/resources.");
            } else {
                props.load(input);
                
                Class.forName(props.getProperty("db.driver"));
            }
        } catch (Exception e) {
            System.err.println("Database Configuration Failed to Load or Driver Not Found.");
            e.printStackTrace();
        }
    }

    
    public static String getUrl() {
        return props.getProperty("db.url");
    }
    
    
    public static String getUsername() {
        return props.getProperty("db.username");
    }

    
    public static String getPassword() {
        return props.getProperty("db.password");
    }
}

