package anime_list.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
// import java.io.BufferedInputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detailmodal")
public class DetailModalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
		
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        try{
            InputStream inputStream = getServletContext().getResourceAsStream("/templates/Modal_Anime.html");
            // BufferedInputStream bufins = new BufferedInputStream(inputStream);
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

            char[] buffer = new char[1024];
            int bytesRead;

            while((bytesRead = reader.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        /*
            while(bufins.read() != -1) {
                out.write(bufins, 0, bytesRead);
            }
        */

            inputStream.close();
            reader.close();        
        } catch (NullPointerException e) {
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }
}

