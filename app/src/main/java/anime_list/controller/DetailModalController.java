package anime_list.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anime_list.model.dao.CommentDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import anime_list.model.dto.DetailModalDto;
import anime_list.model.vo.AniList;
import anime_list.model.vo.Comment;
import anime_list.model.vo.User;
import anime_list.service.CommentService;
import anime_list.service.DetailModalService;


@WebServlet("/DetailInfo/*")
public class DetailModalController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();

        if(pathInfo == null || pathInfo.equals("/")) {
            response.sendRedirect(request.getContextPath()+"/error");
            return;
        }
        if(pathInfo.equals("/ani_detail")) {
            String aniPk = request.getParameter("aniPk");

            AniList selectAnimation = new DetailModalService().selectAnimation(aniPk);
            // selectAni(response, selectAnimation);
            // ArrayList<AniList> detailAniInfo = new DetailModalService().selectEachListinModal(aniPk);
            // selectEachListinModal(response, detailAniInfo);
            ArrayList<Comment> detailCommInfo = new DetailModalService().selectCommentinModal(aniPk);
            // selectCommentinModal(response, detailCommInfo);
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
            // Gson을 통해서 setDateFormat형식을 지정하여, 들어오는 DB 데이터에서 DATE 형태만 변경해줌

            DetailModalDto detailInfoDTO = new DetailModalDto(selectAnimation, detailCommInfo);
            String json = gson.toJson(detailInfoDTO);

            response.getWriter().write(json);
        } else if(pathInfo.equals("/insertComment")) {

            request.setCharacterEncoding("utf-8");

            String commentPk = UUID.randomUUID().toString();
            String userPk = ((User) session.getAttribute("loginUser")).getUserPk();
            // User loginUser = (User) session.getAttribute("loginUser");
            // String userPk = loginUser.getUserPk();
            String aniPk = request.getParameter("aniPk");
            String content = request.getParameter("content");
            float initGrade = Float.parseFloat(request.getParameter("initGrade"));

            CommentService commentservice = new CommentService();
            
            int result = commentservice.insertComment(commentPk, userPk, aniPk, content, initGrade);
            
            if (result > 0) {
                ArrayList<Comment> detailCommInfo = new DetailModalService().selectCommentinModal(aniPk);
                Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();

                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(gson.toJson(detailCommInfo));

                // 댓글 입력 성공 시 해당aniPk의 평점을 업데이트
                new CommentService().updateGrade(aniPk);

            } else {
                response.getWriter().write("error");
            }            
        } else  {
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}


    
//     private void selectAni(HttpServletResponse response, AniList list) throws IOException {
//         if(list == null) {
//             response.getWriter().write("[No data]");
//         } else {
//             PrintWriter out = response.getWriter();
//             Gson gson = new Gson();
//             String json = gson.toJson(list);
//             out.println(json);            
//         }
//     }

//     private void selectEachListinModal(HttpServletResponse response, ArrayList<AniList> list) throws IOException {
//         if(list.isEmpty()) {
//             response.getWriter().write("[No data]");
//         } else {
//             PrintWriter out = response.getWriter();
//             Gson gson = new Gson();
//             String json = gson.toJson(list);
//             out.println(json);            
//         }
//     }

//     private void selectCommentinModal(HttpServletResponse response, ArrayList<Comment> list) throws IOException {
//         if(list.isEmpty()) {
//             response.getWriter().write("[No data]");
//         } else {
//             PrintWriter out = response.getWriter();
//             Gson gson = new Gson();
//             String json = gson.toJson(list);
//             out.println(json);            
//         }
//     }
// }
