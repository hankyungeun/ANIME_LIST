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

    public ArrayList getSelectedAniList(){
        return null;
    }
}
