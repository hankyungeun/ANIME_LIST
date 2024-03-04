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
            prop.loadFromXML(new FileInputStream("app/src/main/resources/db/anyListQuery.xml"));
            prop.loadFromXML(new FileInputStream("app/src/main/resources/db/commmentQuery.XML"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<AniList> selectEachList(Connection conn, String key) {
        ArrayList<AniList> list = new ArrayList<>();
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // String sql = "Select sss from anilist by "
        String sql = prop.getProperty("each_info_aniList");
        try{
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery(sql);
            pstmt.setString(1, key);

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
}
