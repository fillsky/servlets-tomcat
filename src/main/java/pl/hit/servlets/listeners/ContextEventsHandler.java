package pl.hit.servlets.listeners;

import pl.hit.servlets.events.EventManager;
import pl.hit.servlets.events.EventType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextEventsHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("eventManager", EventManager.getInstance());

    }
}
