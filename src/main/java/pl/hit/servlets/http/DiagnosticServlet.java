package pl.hit.servlets.http;

import pl.hit.servlets.events.EventManager;
import pl.hit.servlets.listeners.ContextEventsHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/diagnostics", loadOnStartup = 1)
public class DiagnosticServlet extends HttpServlet {


    @Override
    public void init() {

        EventManager em = (EventManager)getServletContext().getAttribute("eventManager");

        em.getOccurences().forEach((key, value) -> System.out.println("\n" + key + " = " + value));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        EventManager em = (EventManager)req.getServletContext().getAttribute("eventManager");
        em.getOccurences().forEach((key, value) -> writer.println("\n" + key + " = " + value));
    }
}
