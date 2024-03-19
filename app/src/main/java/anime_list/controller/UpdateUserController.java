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

@WebServlet("/updateuser")
public class UpdateUserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        
        User user = new User();
        user.setUserId(userId);
        user.setPasswd(passwd);
        user.setName(name);

        int result = new UserService().updateUser(user);


        if (result > 0 ) {
            System.out.println("UPDATE SUCCESS!");
            HttpSession session = request.getSession();

            session.setAttribute("loginUser", user);
            response.sendRedirect("/main");
        } else{
            response.sendRedirect(request.getContextPath()+"/error");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * 서블릿은 REST API 구현 전 버전으로 단순히 서블릿 구현인 경우 GET,POST를 내부적으로 동일하게 처리
         * GET, POST를 구분해서 처리하는 경우도 별도로 코드 작성은 가능
         */
        System.out.println("doPost 메소드 실행!");
        doGet(request, response);
    }
}
