package anime_list.model.dao;


import anime_list.model.vo.Comment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

        PreaparedStatement pstmt = null;
        s

        return list;
    }

   
        
        







    
    















}

