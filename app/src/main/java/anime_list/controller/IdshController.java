package anime_list.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anime_list.model.dto.UserDto;
import anime_list.service.UserService;



@WebServlet("/idsh")
public class IdshController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
         

        // response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        UserDto userdto = new UserService().idshUser(passwd,name);
        
        if(userdto == null){
            request.getSession().setAttribute("Msg", "FAILL");
            response.getWriter().write("Failed");

        }else{
            session.setAttribute("userdto",userdto);
            response.getWriter().write("UserId = " + userdto.getUserId());
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
 
        System.out.println("doPost 메소드 실행!");
        doGet(request, response);
    }
}