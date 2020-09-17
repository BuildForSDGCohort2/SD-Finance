
package sdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "resetpass", urlPatterns = {"/resetpass"})
public class resetpass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
        //initialising factory
            factory u=new factory();
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        
        PreparedStatement pst=con.prepareStatement("update profile set pass=? where email=?");
        pst.setString(1,request.getParameter("conp")); pst.setString(2,request.getParameter("done"));
        
        //change the password
        pst.executeUpdate();
        
        String body="<hr/><font face=\"Cambria\" size=\"+2\" color=\"green\">Success!</font><hr/>"
                + "Your password has been successfully reset.<hr/>"
                + "<a href=\"sign.html\"><button class=\"btn2\">SignIn</button></a><p/>";
        
        String hom="<a href=\"login\">", btn=u.menu;
        String content=u.head1+hom+u.head2+btn+u.head3+"REQUESTS"+u.comph+body+u.foot1+hom+u.foot2;
        
        try (PrintWriter out = response.getWriter()) {
            out.println(content);
        }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Reset Password";
    }

}
