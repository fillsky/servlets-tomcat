package pl.hit.servlets.http;

import com.sun.jmx.snmp.Enumerated;
import pl.hit.servlets.events.EventManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


@WebServlet(urlPatterns = "/diagnostics", loadOnStartup = 1)
public class DiagnosticServlet extends HttpServlet {


    @Override
    public void init() {

        EventManager em = (EventManager) getServletContext().getAttribute("eventManager");

        em.getOccurences().forEach((key, value) -> System.out.println("\n" + key + " = " + value));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //resp.sendError(404);
        PrintWriter writer = resp.getWriter();
        EventManager em = (EventManager) req.getServletContext().getAttribute("eventManager");
        //em.getOccurences().forEach((key, value) -> writer.println("\n" + key + " = " + value));


        String format = req.getParameter("format");

        if (format == null) {
            resp.setContentType("text/plain");
            writePlain(writer, em);


        } else if (format.equals("xml")) {
            resp.setContentType("application/xml");
            writeXML(writer, em);

        } else if (format.equals("json")) {
            resp.setContentType("application/json");
            writeJSON(writer, em);

        } else if (format.equals("html")) {
            resp.setContentType("text/html");
            writeHTML(writer, em);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "WRONG Parameters! Accepted parameters: xml, html, json, plain");

        }

    }

    private void writePlain(PrintWriter writer, EventManager em) {
        writer.println("Diagnostics: ");
        em.getOccurences().forEach((key, value) -> writer.println("\n" + key + " = " + value));
    }

    private void writeHTML(PrintWriter writer, EventManager em) {
        StringBuilder data = new StringBuilder();
        em.getOccurences().forEach((key, value) -> data.append("<tr>\n" +
                "<td>" + key + "</td>\n<td>" + value + "</td>\n</tr>\n"));
        String html = "<html>\n" +
                "<head><title>Diagnostics</title></head>\n" +
                "<body style=\"font-family: Arial, Helvetica, sans-serif;\">\n" +
                "   <h1>Diagnostics</h1>\n" +
                "   <table>\n" +
                "      <tr>\n" +
                "          <th>Zdarzenie</th>\n" +
                "          <th>Wystąpienia</th>\n" +
                "      </tr>\n" +
                data.toString() +
                "   </table>\n" +
                "</body>\n" +
                "</html>";
        writer.println(html);
    }

    private void writeXML(PrintWriter writer, EventManager em) {
        StringBuilder data = new StringBuilder();
        em.getOccurences().forEach((key, value) -> data.append("<event>\n" +
                "<type>" + key + "</type>\n<count>" + value + "</count>\n</event>\n"));
        String xml = "<diagnostics>\n" + data.toString() + "</diagnostics>";
        writer.println(xml);
    }

    private void writeJSON(PrintWriter writer, EventManager em) {
        StringBuilder data = new StringBuilder();
        em.getOccurences().forEach((key, value) -> data.append("{\n \"name\" : \"" + key
                + "\", \n \"count\" : " + value + "\n},\n"));
        String json = "{\n" +
                "   \"diagnostics\" : [\n" +
                data.toString() +
                "   ]\n" +
                "}   ";
        writer.println(json);
    }
}
