package ru.macrobit.recept.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.pojo.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by [david] on 07.09.16.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Logout.class);
    @Inject
    private ContextService ctx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User manager = ctx.getCurrentUser();
            if (manager != null) {
                response.setContentType("text/html");
                request.getSession().invalidate();
                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                Cookie[] cookies = request.getCookies();
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                try {
                    request.logout();
                } catch (ServletException e) {
                    logger.error("{}", e);
                }
                response.setHeader("Location", "/recept/login");
            } else {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "/recept/login");
            }
        } catch (Exception e) {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/recept/login");
        }
    }
}
