package anime_list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import anime_list.model.dto.DetailModalDto;
import anime_list.model.vo.AniList;
import anime_list.model.vo.Comment;
import anime_list.service.DetailModalService;


@WebServlet("/DetailInfo/*")
public class DetailModalController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        String pathInfo = request.getPathInfo();

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
            String aniPk = request.getParameter("aniPk");
            


        } else  {
            response.sendRedirect(request.getContextPath()+"/error");
        }
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
