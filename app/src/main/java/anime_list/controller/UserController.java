package anime_list.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anime_list.model.vo.User;
import anime_list.service.UserService;

@WebServlet("/insertUesr")
public class UserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        // 폼에서 전달된 값 가져오기
        String userPk = UUID.randomUUID().toString();
        String userId = request.getParameter("userId");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");

        // User 객체 생성
        User user = new User(userPk, userId, passwd, name);

        // UserService를 이용하여 사용자 추가
        int result = new UserService().insertUser(user);
        System.out.printf("result: %d\n", result);
        if (result > 0) {

            System.out.println("회원 추가에 성공하셨습니다 SUCCESS!");
            response.sendRedirect("/login");

        } else {
            System.out.println("회원 추가에 실해하셨습니다 Failed!!");
            response.sendRedirect("/newuser");

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
        /*
         * 서블릿은 REST API 구현 전 버전으로 단순히 서블릿 구현인 경우 GET,POST를 내부적으로 동일하게 처리
         * GET, POST를 구분해서 처리하는 경우도 별도로 코드 작성은 가능
         */
        System.out.println("doPost 메소드 실행!");
        doGet(request, response);
    }
}
