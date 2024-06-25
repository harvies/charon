package io.github.harvies.test.spring.boot.web.tomcat.servlet;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author harvies
 */
@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();
    }

}
