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

    public CommentDao() {

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

    public ArrayList<Comment> selectAllList(Connection conn) {
        ArrayList<Comment> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("commentList");
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Comment comment = new Comment(
                        rset.getString("COMMENT_PK"),
                        rset.getString("USER_PK"),
                        rset.getString("ANI_PK"),
                        rset.getString("CONTENT"),
                        rset.getDate("COMMENT_DATE"),
                        rset.getFloat("INIT_GRADE"));
                list.add(comment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close(rset);
            JDBC.close(pstmt);
        }

        return list;
    }

    public ArrayList<Comment> selectList(Connection conn, String aniPk){
        ArrayList<Comment> list = new ArrayList<>();
        
        PreparedStatement pstmt = null;
        ResultSet rset= null;
        String sql = prop.getProperty("commentList_anipk");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aniPk);
            
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

    public int insertComment(Connection conn, Comment comment){
        int result = 0;
        PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, comment.getCommentPk());
			pstmt.setString(2, comment.getUserPk());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getAniPk());
			pstmt.setFloat(5, comment.getInitGrade());
			
			// sql문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(pstmt);
		}
		
		return result;
    }
    public int updateGrade(Connection conn, String aniPk) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = this.prop.getProperty("updateGrade");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aniPk);
            pstmt.setString(2, aniPk);
            result = pstmt.executeUpdate();
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            JDBC.close(pstmt);
        }

        return result;
    }

}
