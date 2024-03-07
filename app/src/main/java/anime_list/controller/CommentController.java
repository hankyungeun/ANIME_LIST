package anime_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import anime_list.model.vo.Comment;

import anime_list.service.CommentService;
@WebServlet("/Comment")
public class CommentController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        List<Comment> comment = new CommentService().selectAllList();
        if(comment.isEmpty()) {
            response.getWriter().write("데이터 없음");
        }else{
            PrintWriter out = response.getWriter();
             Gson gson = new Gson();
                String json = gson.toJson(comment);
                out.println(json);
        }
    }

    
}
