package anime_list.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anime_list.model.dto.UserDto;
import anime_list.service.UserService;



@WebServlet("/chId")
public class IdchController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("name");
         
        System.out.println(userId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        UserDto userdto = new UserService().idchUser(userId);
        
        if(userdto != null){
            response.getWriter().print("");
        }else{
            response.getWriter().print(userdto);
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
 
        System.out.println("doPost 메소드 실행!");
        doGet(request, response);
    }
}