package anime_list.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anime_list.model.dto.UserDto;
import anime_list.service.UserService;

    
@WebServlet("/pwsh")
public class PwshController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
         

        // response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserDto userPw = new UserService().pwshUser(userId,name);
        
        if(userPw == null){
            request.getSession().setAttribute("Msg", "FAILL");
            response.getWriter().write("Failed");
        }else{
            session.setAttribute("userPw",userPw);
            response.getWriter().write("UserPassword = " + userPw.getUserId());
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
 
        System.out.println("doPost 메소드 실행!");
        doGet(request, response);
            }
    }
