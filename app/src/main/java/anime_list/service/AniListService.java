package anime_list.service;

import anime_list.config.JDBC;
import anime_list.model.dao.AniListDao;
import anime_list.model.vo.AniList;

import java.sql.Connection;
import java.util.ArrayList;

public class AniListService {
    public ArrayList<AniList> getLatestAniList() {
        Connection conn = JDBC.getConnection();
        ArrayList<AniList> list = new AniListDao().getLatestAniList(conn);
        JDBC.close(conn);

        return list;
    }

    public ArrayList<AniList> getSelectedAniList(int year, int quarter){
        Connection conn = JDBC.getConnection();
        ArrayList<AniList> list = new AniListDao().getSelectedAniList(conn, year, quarter);
        JDBC.close(conn);

        return list;
    }

    public ArrayList<AniList> getSearchAniList(String keyword){
        Connection conn = JDBC.getConnection();
        ArrayList<AniList> list = new AniListDao().getSearchAniList(conn, keyword);
        JDBC.close(conn);

        return list;
    }
}
