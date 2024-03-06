package anime_list.model.dao;

import anime_list.config.JDBC;
import anime_list.model.vo.Comment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class CommentDao {

    private Properties prop = new Properties(); 

    public CommentDao(){        
      
            try {
                prop.loadFromXML(new FileInputStream("app/src/main/resources/db/commentQuery.xml"));
            } catch (InvalidPropertiesFormatException e) {                
                e.printStackTrace();
            } catch (FileNotFoundException e) {               
                e.printStackTrace();
            } catch (IOException e) {                
                e.printStackTrace();
            } 
    }       

    public ArrayList<Comment> selectAllList(Connection conn){
        ArrayList<Comment> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rset= null;

        String sql = prop.getProperty("commentList");
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while(rset.next()){
                Comment comment = new Comment(
                    rset.getString("COMMENT_PK"),
                    rset.getString("USER_PK"),
                    rset.getString("ANI_PK"),
                    rset.getString("CONTENT"),
                    rset.getDate("COMMENT_DATE"),
                    rset.getFloat("INIT_GRADE")  );
                list.add(comment);
            }
        } catch (SQLException e) {           
            e.printStackTrace();
        }finally{
            JDBC.close(rset);
            JDBC.close(pstmt);
        }
        
        

        return list;
    }

   
        
        







    
    















}

