package anime_list.service;

import anime_list.model.vo.Comment;

import java.sql.Connection;
import java.util.ArrayList;

import anime_list.config.JDBC;
import anime_list.model.dao.CommentDao;

public class CommentService {
    public ArrayList<Comment> selectAllList(){
        Connection conn = JDBC.getConnection();
        ArrayList<Comment> list = new CommentDao().selectAllList(conn);
        JDBC.close(conn);
        JDBC.close(conn);

        return list;
    }
}
