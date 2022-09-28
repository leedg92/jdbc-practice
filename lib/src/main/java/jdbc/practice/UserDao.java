package jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.h2.engine.User;

public class UserDao {
	Connection con = null;
	PreparedStatement pstmt = null;

	
	public void create(User user) throws SQLException {
		JdbcTemplet jdbcTemplet = new JdbcTemplet();
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplet.executeUpdate(user, sql, pstmt -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		});
		
	}
	
	public User findByUserId(String userId) throws SQLException {
		JdbcTemplet jdbcTemplet = new JdbcTemplet();
		
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
		
		return  (User) jdbcTemplet.executeQuery(sql, 
				pstmt -> {pstmt.setString(1, userId);},
				resultMap -> 	new User(
						resultMap.getString("userId"),
						resultMap.getString("password"),
						resultMap.getString("name"),
						resultMap.getString("email")
						)
				
				);
		
		
		
	}



}
