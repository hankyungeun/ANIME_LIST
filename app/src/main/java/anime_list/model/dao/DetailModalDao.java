package anime_list.model.dao;


import anime_list.config.JDBC;
import anime_list.model.vo.AniList;
import anime_list.model.vo.Comment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DetailModalDao {
    private Properties prop = new Properties();

    public DetailModalDao() {
        try {
            prop.loadFromXML(new FileInputStream("app/src/main/resources/db/detailModalQuery.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AniList selectAnimation(Connection conn, String aniPk) {
        AniList list = new AniList();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("each_info_aniList");



        return list;
    }
    
    public ArrayList<AniList> selectEachListinModal(Connection conn, String aniPk) {
        ArrayList<AniList> list = new ArrayList<>();        
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // String sql = "Select sss from anilist by "
        String sql = prop.getProperty("each_info_aniList");
        try{
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery(sql);
            pstmt.setString(1, aniPk);

            while(rset.next()) {
                AniList each_info_aniList = new AniList(
                        rset.getString("ANI_PK"),
                        rset.getString("TITLE"),
                        rset.getString("GENRE"),
                        rset.getString("DETAIL"),
                        rset.getFloat("GRADE"),
                        rset.getDate("START_DATE"),
                        rset.getString("IMAGE_URL"),
                        rset.getString("VIDEO_URL")                );
                list.add(each_info_aniList);            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close(rset);
            JDBC.close(pstmt);
        }
        
        return list;
    }

    public ArrayList<Comment> selectCommentinModal(Connection conn, String aniPk) {
        ArrayList<Comment> list = new ArrayList<>();
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // String sql = "Select sss from anilist by "
        String sql = prop.getProperty("each_info_comment");
        try{
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery(sql);
            pstmt.setString(1, aniPk);

            while(rset.next()) {
                Comment each_info_comment = new Comment(
                        rset.getString("ANI_PK"),
                        rset.getString("COMMENT_PK"),
                        rset.getString("USER_PK"),
                        rset.getString("CONTENT"),
                        rset.getDate("COMMENT_DATE"),
                        rset.getFloat("INIT_GRADE")        );
                list.add(each_info_comment);            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close(rset);
            JDBC.close(pstmt);
        }
        
        return list;
    }
}
