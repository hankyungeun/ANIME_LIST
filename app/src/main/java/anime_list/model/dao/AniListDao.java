package anime_list.model.dao;

import anime_list.config.JDBC;
import anime_list.model.vo.AniList;

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

public class AniListDao {
    private Properties prop = new Properties();

    public AniListDao() {
        try {
            prop.loadFromXML(new FileInputStream("app/src/main/resources/db/aniListQuery.xml"));

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AniList> getLatestAniList(Connection conn){
        ArrayList<AniList> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("LatestAniList");
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery(sql);

            while(rset.next()) {			// .next():데이터가 있는지 여부 체크
                AniList aniList = new AniList(
                        rset.getString("ANI_PK"),
                        rset.getString("TITLE"),
                        rset.getString("GENRE"),
                        rset.getString("DETAIL"),
                        rset.getFloat("GRADE"),
                        rset.getDate("START_DATE"),
                        rset.getString("IMAGE_URL"),
                        rset.getString("VIDEO_URL")                );
                list.add(aniList);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close(rset);
            JDBC.close(pstmt);
        }
        return list;
    }
    public ArrayList<AniList> getSelectedAniList(Connection conn, int year, int quarter) {
        ArrayList<AniList> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectedAniList");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, quarter);
            rset = pstmt.executeQuery();

            while(rset.next()) {			// .next():데이터가 있는지 여부 체크
                AniList aniList = new AniList(
                        rset.getString("ANI_PK"),
                        rset.getString("TITLE"),
                        rset.getString("GENRE"),
                        rset.getString("DETAIL"),
                        rset.getFloat("GRADE"),
                        rset.getDate("START_DATE"),
                        rset.getString("IMAGE_URL"),
                        rset.getString("VIDEO_URL")                );
                list.add(aniList);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close(rset);
            JDBC.close(pstmt);
        }
        return list;}

}
