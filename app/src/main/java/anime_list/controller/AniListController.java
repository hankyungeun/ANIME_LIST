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

@WebServlet("/AniList/*")
public class AniListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }

        if (pathInfo.equals("/latest")) {
            List<AniList> latestAniList = new AniListService().getLatestAniList();
            getLatestAniList(response, latestAniList);
        }
        else if(pathInfo.equals("/select")){
            String year = request.getParameter("year");
            String quarter = request.getParameter("quarter");

            List<AniList> selectedAniList = new AniListService().getSelectedAniList(
                                                    Integer.parseInt(year), Integer.parseInt(quarter));
            getSelectedAniList(request, response, selectedAniList);
        }
        else if(pathInfo.equals("/search")){
            String keyword = request.getParameter("keyword");

            List<AniList> searchAniList = new AniListService().getSearchAniList(keyword);
            getSearchAniList(response, searchAniList);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void getLatestAniList(HttpServletResponse response, List<AniList> aniList) throws IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(aniList);
        response.getWriter().write(json);
    }

    private void getSelectedAniList(HttpServletRequest request, HttpServletResponse response, List<AniList> aniList) throws IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(aniList);
        response.getWriter().write(json);
    }

    private void getSearchAniList(HttpServletResponse response, List<AniList> aniList) throws IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(aniList);
        response.getWriter().write(json);
    }
}