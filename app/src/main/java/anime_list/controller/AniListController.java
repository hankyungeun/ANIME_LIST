package anime_list.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import anime_list.model.vo.AniList;
import anime_list.service.AniListService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LatestAniList")
public class AniListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        List<AniList> aniList = new AniListService().selectAllList();

        if (aniList.isEmpty()) {
            // 리스트가 비어있을 경우 처리
            System.out.println("데이터없음!!!");
            response.getWriter().write("데이터 없음!!!");
        } else {
            // 리스트가 비어있지 않을 경우 처리
            System.out.println(aniList);
            // 여기에서는 간단하게 리스트를 출력했지만, 필요에 따라 다른 처리를 수행할 수 있음
            // 예를 들어, JSON 형식으로 응답을 보낼 수도 있음
            // ObjectMapper를 사용하여 리스트를 JSON 형식으로 변환하여 클라이언트에게 응답을 보낼 수 있음
            // 예: response.getWriter().write(new ObjectMapper().writeValueAsString(aniList));
            // 아래 코드는 클라이언트에게 리스트를 출력하는 예시임
            PrintWriter out = response.getWriter();
            for (AniList ani : aniList) {
                out.println(ani);
            }
        }
    }

    public List<AniList> getAniList() {

        List<AniList> aniList = new AniListService().selectAllList();

        if (aniList.isEmpty()) {
            System.out.println("데이터없음!!!");
        } else {
            System.out.println(aniList);
        }
        return aniList;
    }
}
