//TanWeiFengStart
//SQL database related(For user data)
package fopassignment.journaling01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

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

    public String getUserByEmail(String email) {
        String sql = "SELECT name FROM users WHERE email = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
            ) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getPwByEmail(String email) {
        String sql = "SELECT password_hash FROM users WHERE email = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
            ) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password_hash");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
//TanWeiFengEnd
// ChengYingChenStarts

    public LocalDate getCreatedDByEmail(String email) 
    {
        String sql = "SELECT created_at FROM users WHERE email = ?"; 

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return rs.getTimestamp("created_at").toLocalDateTime().toLocalDate(); 
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
}
// ChengYingChenEnds
}