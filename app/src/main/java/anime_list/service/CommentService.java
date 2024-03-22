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
        
        return list;
    }
    public ArrayList<Comment> selectList(String aniPk){
        Connection conn = JDBC.getConnection();
        ArrayList<Comment> list = new CommentDao().selectList(conn, aniPk);

        JDBC.close(conn);
        
        return list;
    }
    public int insertComment(String commentPk, 
            String userPk,String aniPk,String content,float initGrade){
                Connection conn = JDBC.getConnection();
                Comment comment = new Comment();
                comment.setAniPk(aniPk);
                comment.setCommentPk(commentPk);
                comment.setUserPk(userPk);
                comment.setContent(content);
                comment.setInitGrade(initGrade);


                int result = new CommentDao().insertComment(conn, comment);
                JDBC.close(conn);
                return result;
    }
}
