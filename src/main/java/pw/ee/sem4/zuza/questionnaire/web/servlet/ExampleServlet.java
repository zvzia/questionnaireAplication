/*
 * Created by .
 * User: gaika
 * Date: 07.04.2022
 * Time: 17:35
 * GóZIK
 */
package pw.ee.sem4.zuza.questionnaire.web.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import pw.ee.sem4.zuza.questionnaire.serwices.QuestionaireService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(
        name = "ExampleServlet",
        description = "Example Servlet Using Annotations",
        urlPatterns = {"/ExampleServlet"}
)
public class ExampleServlet extends HttpServlet {

    @Autowired
    QuestionaireService tstService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        System.err.println(req.getParameter("ala"));
        String param_xxx = req.getParameter("ala");



        tstService.metoda(param_xxx);


        Enumeration<String> ennn = req.getParameterNames();
        while (ennn.hasMoreElements()) {
            String s = ennn.nextElement();
            System.err.println(s + " ->" + req.getParameter(s));
        }


        System.err.println("====>" + req.getParameterMap());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World!</p><br>" + param_xxx);
        out.println("<br>");
        out.println("<input type=\"text\" id=\"lname\" name=\"lname\" value=\"" + param_xxx + "\"></input>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        System.err.println(req.getParameter("ala"));
        String param_xxx = req.getParameter("ala");

        Enumeration<String> ennn = req.getParameterNames();
        while (ennn.hasMoreElements()) {
            String s = ennn.nextElement();
            System.err.println(s + " ->" + req.getParameter(s));
        }

        response.setContentType("text/html");
        /*PrintWriter out = response.getWriter();
        out.println("<p>Hello World!</p><br>" + param_xxx);
        out.println("<br>");
        out.println("<input type=\"text\" id=\"lname\" name=\"lname\" value=\"" + param_xxx + "\"></input>");
*/
        System.err.println( req.getHeader("Referer"));

        response.sendRedirect(req.getHeader("Referer"));
    }

}
