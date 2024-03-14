package anime_list.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import anime_list.model.vo.User;
import anime_list.config.JDBC;

public class UserDao {
private Properties prop = new Properties();

    public UserDao() {
        try {
            prop.loadFromXML(new FileInputStream("app/src/main/resources/db/userQuery.xml"));

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public int insertUser(Connection conn,User user){
        System.out.println(user.getUserPk());
        System.out.println(user.getUserId());
        System.out.println(user.getPasswd());
        System.out.println(user.getName());
        PreparedStatement pstmt = null;

        int result=0;
        String sql = prop.getProperty("insertUser");
       // String sql = "INSERT INTO USER VALUES(?,?,?,?)";
       try{
        pstmt = conn.prepareStatement(sql);

        
        
        pstmt.setString(1,user.getUserPk());
        pstmt.setString(2,user.getUserId());
        pstmt.setString(3,user.getPasswd());
        pstmt.setString(4,user.getName());

        result = pstmt.executeUpdate();
       }catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC.close(pstmt);
		}
		return result; 
    }

    public User loginUser(Connection conn,String userId,String passwd){

        System.out.println(userId);
        User user = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginuser");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, passwd);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
                System.out.println("==============");
				user = new User (
                        rset.getString("user_Pk"),
						rset.getString("user_id"),
                        rset.getString("passwd"),
                        rset.getString("name")
						);
                System.out.println(user.getUserPk());
                System.out.println(user.getUserId());
                System.out.println(user.getPasswd());
                System.out.println(user.getName());
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBC.close(rset);
			JDBC.close(pstmt);
		}
		return user;
    }


    

}
