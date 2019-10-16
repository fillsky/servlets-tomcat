package pl.hit.servlets.listeners;


import pl.hit.servlets.events.EventManager;
import pl.hit.servlets.events.EventType;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestEventsHandler implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        EventManager em = EventManager.getInstance();
        //sre.getServletRequest().setAttribute("eventManager",  EventManager.getInstance());

        em.notify(EventType.REQUEST_STARTED);



    }
}
