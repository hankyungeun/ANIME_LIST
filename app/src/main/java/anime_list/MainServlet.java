package anime_list;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // index.html 파일을 읽어서 출력
        try (InputStream inputStream = getServletContext().getResourceAsStream("templates/index.html")) {
            if (inputStream != null) {
                int data;
                while ((data = inputStream.read()) != -1) {
                    out.write(data);
                }
            } else {
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<h1>Could not find index.html</h1>");
                out.println("</body></html>");
            }
        }
    }
}
