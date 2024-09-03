package gr.aueb.cf.helloservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/inspect-request")
public class InspectrequestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Inspect parameter
        String username = request.getParameter("username");
        response.getWriter().println(username + " username");

        //Inspect request headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            response.getWriter().println(headerName + ": " + headerValue);

            //Inspect cookies
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("JSESSIONID")) {
                        response.getWriter().write("Cookie name:" + cookie.getValue() +" :" + cookie.getName());
                    }
                }
            }

            //Inspect session id(JSESSIONID)
            HttpSession session = request.getSession();
            String sessionid = session.getId();
            response.getWriter().write(sessionid + " sessionid");

            //Inspect session atributes
            request.getSession().setAttribute("username", username);
            String sesionUserName = (String) request.getSession().getAttribute("username");
            response.getWriter().write(sesionUserName + " sesionUserName");

            //Inspect request URI and context path
            System.out.println("request URI: " + request.getRequestURI());
            System.out.println("Context path: " + request.getContextPath());
            System.out.println("Servlet path: " + request.getServletPath());
            response.getWriter().write("request URI: " + request.getRequestURI() + "\n");
            response.getWriter().write("context path: " + request.getContextPath() + "\n");
            response.getWriter().write("servlet path: " + request.getServletPath() + "\n");
        }
    }
}
