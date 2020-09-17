
package sdf;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

//SQL import
import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class signup extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //catch exceptions
        try {
            
        //initialising factory
        factory u=new factory();
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        
        //create the page body content
        String body="";
        
        //Am handling how my body looks on authentication of signin nand signup dts
        
        /**
         * This is an authentication algorithm for user signing up
         */
        if(request.getParameter("type").equals("signup")) {
            
            String qry1="select email from profile";
            PreparedStatement pst1=con.prepareStatement(qry1); ResultSet rs1=pst1.executeQuery();
            
            List<String> lst1=new ArrayList();
            while(rs1.next())
            {
                lst1.add(rs1.getString(1));
            }
            
            if(!u.Exists(lst1, request.getParameter("email")))
            {
                
                //let us submit the form to administrator
                String qry2="insert into profile(fname,lname,email,phone,gender,country,dob,address,pass,main,boss,lancer,user_type,status)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                PreparedStatement pst2=con.prepareStatement(qry2);
                
                pst2.setString(1, request.getParameter("fname")); pst2.setString(2, request.getParameter("lname"));
                pst2.setString(3, request.getParameter("email")); pst2.setString(4, request.getParameter("phone"));
                pst2.setString(5, request.getParameter("gender")); pst2.setString(6, request.getParameter("country"));
                pst2.setString(7, request.getParameter("dob")); pst2.setString(8, request.getParameter("address"));
                pst2.setString(9, request.getParameter("pass")); pst2.setString(10, "0");
                pst2.setString(11, "0"); pst2.setString(12, "0");
                pst2.setString(13, "client"); pst2.setString(14, "pending");
                
                pst2.executeUpdate();
                
                //show user that the application has been submitted 
                
                body=body+""
                        + "<hr/><h4>ACCOUNT SUCCESSFULLY CREATED</h4><hr/>"
                        + "<font face=\"Century Gothic\" size=\"+0\" color=\"darkgreen\">"
                        + "Your application has been successfully submited and account with email <b>"+request.getParameter("email")+" has "
                        + "been created. <p/>Your account is now under review before it is approved. This will be completed within a few hours.<br/>"
                        + "Once approved, You'll be able to receive, send, and use all service at SD Finance<p/>You can also login "
                        + "to view the staus of your application.<p/><button class=\"btn\">Sign In</button><hr/>";
                
                
            } else {
                body=body+""
                        + "<hr/><h4>EMAIL EXISTS</h4><hr/>"
                        + "<font face=\"Lucida fax\" color=\"darkred\">"
                        + "The email address <b>"+request.getParameter("email")+"</b> is already registered with another account."
                        + "If its your account, please <a href=\"signin.html\">Sigin In</a> right away. Else, please review your email and "
                        + "try Creating account again <a href=\"create.html\">HERE</a></font><p/>"
                        + "<h5>Click Return to go back to the registration page</h5>"
                        + "<a href=\"create.html\"><button class=\"btn\">Return</button></a><hr/>";
            }
            
        }
        
        /**
         * This is an authentication algorithm for user logging in
         */
        if(request.getParameter("type").equals("signin")) {
            
            String qry3="select *from profile where email=?";
            PreparedStatement pst3=con.prepareStatement(qry3); pst3.setString(1,request.getParameter("user"));
            ResultSet rs3=pst3.executeQuery();
            
            if(rs3.next()) {
                
                if(rs3.getString("pass").equals(request.getParameter("pass"))) {
                    
                    //let us add user cookie to the []
                    Cookie ck=new Cookie("user",request.getParameter("user")); ck.setMaxAge(60*60*7);
                    response.addCookie(ck);
                    
                    //redirect
                    response.sendRedirect("login");
                    
                } else {
                    response.sendRedirect("sign.html");
                }
                
            } else {
                response.sendRedirect("sign.html");
            }
            
        }
        //Writing it down
        
        String hom="<a href=\"index.html\">", btn=u.signin;
        
        String content=u.head1+hom+u.head2+btn+u.head3+"ACCOUNT CREATION"+u.comph+body+u.foot1+hom+u.foot2;
        
        try (PrintWriter out = response.getWriter()) {
            out.println(content);
        }
        } catch (IOException | SQLException | ClassNotFoundException ex) {
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
        return "Account creation Servlet. To create and send Client application to admin for review";
    }
    
    public boolean IsConnected(Connection conn) {
        boolean rtn=true;
        /*if(conn!=null) {
            rtn=true;
        } else {
            rtn=false;
        }*/
        return rtn;
    }

}
