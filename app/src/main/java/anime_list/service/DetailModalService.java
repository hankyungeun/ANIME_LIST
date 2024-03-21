package anime_list.service;

import anime_list.config.JDBC;
import anime_list.model.dao.DetailModalDao;
import anime_list.model.vo.AniList;
import anime_list.model.vo.Comment;


import java.sql.Connection;
import java.util.ArrayList;

public class DetailModalService {
    

    public AniList selectAnimation(String aniPk) {

        Connection conn = JDBC.getConnection();
        AniList list = new DetailModalDao().selectAnimation(conn, aniPk);
        JDBC.close(conn);

        return list;

    }
/*
    public ArrayList<AniList> selectEachListinModal(String aniPk) { 
        Connection conn = JDBC.getConnection();
        ArrayList<AniList> list = new DetailModalDao().selectEachListinModal(conn, aniPk);
        JDBC.close(conn);

        return list;
    }
*/
    public ArrayList<Comment> selectCommentinModal(String aniPk, Float grade,String[] comment) {
        Connection conn = JDBC.getConnection();
        ArrayList<Comment> list = new DetailModalDao().selectCommentinModal(conn, aniPk, grade, comment);
        
        
        JDBC.close(conn);

        return list;
    }
}
