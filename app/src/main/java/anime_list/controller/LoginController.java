package anime_list.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anime_list.model.vo.User;
import anime_list.service.UserService;

@WebServlet("/loginUser")
public class LoginController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // 폼에서 전달된 값 가져오기
        String userId = request.getParameter("userId");
        String passwd = request.getParameter("passwd");

        // User 객체 생성
        User loginUser = new UserService().loginUser(userId, passwd);
        
        if (loginUser == null) {
            System.out.println("로그인 실패 !");
            
            response.sendRedirect("/main?loginFailed=true");
            
		} else {
            
            System.out.println("로그인 성공 SUCCESS!");
            
			session.setAttribute("loginUser", loginUser);
            response.sendRedirect("/main");
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
