package pl.hit.servlets.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class ResponseInfoFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        chain.doFilter(req,res);
        System.out.println("HTTP Status: "+ res.getStatus());
        System.out.println("Content Type: "+ res.getContentType());
        System.out.println("Headers:");
        res.getHeaderNames().forEach(System.out::println);


    }
}
