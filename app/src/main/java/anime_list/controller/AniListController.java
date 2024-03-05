package anime_list.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import anime_list.model.vo.AniList;
import anime_list.service.AniListService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AniList")
public class AniListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        List<AniList> aniList = new AniListService().selectLatestAniList();

        if (aniList.isEmpty()) {
            response.getWriter().write("데이터 없음!!!");
        } else {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String json = gson.toJson(aniList);
            out.println(json);
        }
    }
}
