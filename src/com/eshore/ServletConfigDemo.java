package com.eshore;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ServletConfigDemo",urlPatterns = {"/servletConfigDemo.do"},
loadOnStartup = 1,displayName = "demo",
initParams = {
        @WebInitParam(name = "success",value ="success.html"),
        @WebInitParam(name="error",value = "error.html")
})
public class ServletConfigDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletConfig config=getServletConfig();
        String success=config.getInitParameter("success");
        String error=config.getInitParameter("error");
        System.out.println("success......"+success);
        System.out.println("error........"+error);

        Enumeration enumeration=config.getInitParameterNames();
        while (enumeration.hasMoreElements()){
            String name=(String)enumeration.nextElement();
            String value=config.getInitParameter(name);
            System.out.println("name........."+name);
            System.out.println("value........"+value);
        }

        ServletContext servletContext=config.getServletContext();
        System.out.println("servletContext."+servletContext);
        String servletName=config.getServletName();
        System.out.println("servletName.."+servletName);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userID=request.getParameter("userID");
        String passwd=request.getParameter("passwd");

        if(userID!=null&&"linl".equals(userID)&&passwd!=null&&"123456".equals(passwd)){
            HttpSession session=request.getSession();
            session.setAttribute("user", userID);
            RequestDispatcher dispatcher=request.getRequestDispatcher(success);
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher=request.getRequestDispatcher(error);
            dispatcher.forward(request, response);
        }
    }

    public void init() throws ServletException{

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public ServletConfigDemo(){
        super();
    }
    public void destroy(){
        super.destroy();
    }
}
