package anime_list.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import anime_list.model.vo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 1000);

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginUser") != null) {
            // 로그인 상태일 경우, /main 페이지로 리다이렉트합니다.
            response.sendRedirect(request.getContextPath() + "/main");
        } else {


            ServletContext context = getServletContext();

            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(context);

            templateResolver.setTemplateMode("XHTML");
            templateResolver.setCharacterEncoding("UTF-8");
            templateResolver.setPrefix("/templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setCacheTTLMs(3600000L);
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(),
                    request.getLocale());

            templateEngine.process("login", ctx, response.getWriter());
        }
    }
}
