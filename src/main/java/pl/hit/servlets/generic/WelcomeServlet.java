package pl.hit.servlets.generic;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import java.util.Arrays;

@WebServlet(urlPatterns = "/welcome-generic")

public class WelcomeServlet extends GenericServlet {
    @Override

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println("Hello, servlet world!!");
        StringBuilder info = new StringBuilder()
                .append("Client info: ")
                .append("\n\tName: " + req.getServerName())
                .append("\n\tAddress: " + req.getLocalAddr())
                .append("\n\tPort: " + req.getLocalPort())
                .append("\n Server info: ")
                .append("\n\tServer name: " + req.getServerName())
                .append("\n\tServer host: " + req.getRemoteHost())
                .append("\n\tServer address: " + req.getRemoteAddr())
                .append("\n\tServer port: " + req.getRemotePort())
                .append("\nProtocol: " + req.getProtocol())
                .append("\nChar Encoding: " + req.getCharacterEncoding())
                .append("\nContent Type: " + req.getContentType())
                .append("\nContent Type: " + req.getContentType())
                .append("\nRequest Parameters: ");
                req.getParameterMap().forEach((key,value) -> info.append("\n" + key +" = "+ Arrays.toString(value)));

        res.getWriter().println(info.toString());
    }
}
