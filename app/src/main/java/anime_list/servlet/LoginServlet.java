package anime_list.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // index.html 파일을 읽어서 출력
        try (InputStream inputStream = getServletContext().getResourceAsStream("/templates/login.html")) {
            if (inputStream != null) {
                int data;
                while ((data = inputStream.read()) != -1) {
                    out.write(data);
                }
            } else {
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<h1>Could not find login.html</h1>");
                out.println("</body></html>");
            }
        }
    }
}
