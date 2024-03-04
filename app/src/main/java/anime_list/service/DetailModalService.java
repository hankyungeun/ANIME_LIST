package anime_list.service;

import anime_list.config.JDBC;
import anime_list.model.dao.DetailModalDao;
import anime_list.model.vo.AniList;

import java.sql.Connection;
import java.util.ArrayList;

public class DetailModalService {
    public ArrayList<AniList> selectEachList() { 
        Connection conn = JDBC.getConnection();
        ArrayList<AniList> list = new DetailModalDao().selectEachList(conn, key);
        JDBC.close(conn);

        return list;
    }
}
