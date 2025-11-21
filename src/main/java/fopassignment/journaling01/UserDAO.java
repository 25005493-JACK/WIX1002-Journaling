//TanWeiFengStart
package fopassignment.journaling01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {//DAO=data access object

    public boolean saveUser(int userId, String name, String email, String password) {
        String sql = "INSERT INTO users (user_id, name, email, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // @yingchen, this code u can use to auto read user name from sqldatabase using email
    public String getUserByEmail(String email) {
        String sql = "SELECT name FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
//TanWeiFengEnd