
package sdf;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;
import java.util.*;
/**
 *
 * @author AMOS
 */
@WebServlet(name = "forgot", urlPatterns = {"/forgot"})
public class forgot extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        //catch exceptions
        try {
            
            /*/identify user
            String user=null;
            Cookie[] cks=request.getCookies();
            if(cks!=null) {
            for(Cookie ck:cks) {
                if(ck.getName().equals("user")) {
                    user=ck.getValue();
                }
            }} else {
                try (PrintWriter out = response.getWriter()) { 
                out.println("<center><img src=\"images/logo.png\" width=\"137\" height=\"137\" alt=\"LOGO\" /><hr/>"
                        + "<h2>This session has expired. Please click Return below to go to the Home page;</h2>"
                        + "<p/><a href=\"index.html\"><button style=\"background-color: royalblue; padding: 27px; "
                        + "font-family: Cambria; color: white; font-size: 24px;\" "
                        + ">Return</button></a></center>");
                }
            }
            */
            //initialising factory
            factory u=new factory();
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        
        //create the page body content
        String body="";
        
        if(request.getParameter("emailo")==null && request.getParameter("done")==null) {
        body=body+"<hr/><p class=\"hedi\">Reset Your Passowrd</p><hr/>"
                + "<form name=\"reset\" action=\"forgot\" method=\"post\" onsubmit=\"return(rrsstt())\">"
                + "Please provide your registered email<hr/>"
                + "<input type=\"text\" class=\"tfd\" size=\"51\" placeholder=\"Enter Email\" name=\"emailo\" /><hr/>"
                + "<button type=\"submit\" class=\"btn2\">Continue</button>"
                + "</form><hr/>";
        }
        
        if(request.getParameter("emailo")!=null) {
            
            boolean exist=false;
            PreparedStatement pst3=con.prepareStatement("select *from profile");
            ResultSet rs3=pst3.executeQuery();
            
            while(rs3.next()) {
                if(rs3.getString("email").equals(request.getParameter("emailo"))) {
                    exist=true;
                }
            }
            
            if(exist) {
                
          /*       //generate a random code of 7digits
        int min=10000000,max=70000000;
        Random rd=new Random();
        int random=min+rd.nextInt(max);
        
        //OK! Let us send a varification email with code to the user
        //create the message
        String msg="Hello,<p/>"
                + "We have received your request to reset your password. We have sent the 7 digit code to "+request.getParameter("emailo")+". Please enter this code in required field to "
                + "continue. The code is provided below.<p/>Verification Code: "+String.valueOf(random)+"<p/>"
                + "If you didn't make this action, please contact us immediately!";
        
        String mail=u.mail1+"Reset Code"+u.mail2+msg+u.mail3;
        
        
        try {
        //prepare
        Sender sd=new Sender("smtp.gmail.com");
        sd.setFro("info.seumxplus@gmail.com"); sd.setTo(request.getParameter("emailo"));
        sd.setUser("info.seumxplus@gmail.com"); sd.setPass("absolute13");
        sd.setSubject("SD Finance - Verification Code"); sd.setContent(mail); sd.arrange();
        
        //send
        sd.send(); 
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        //request the sent code
        body=body+"<hr/>We just sent a 7 digit code to <b><i>"+request.getParameter("emailo")+"</i></b>. Please check your inbox and Spam messages "
                + "enter the code in the field below to continue;<hr/>"
                + "<form name=\"ecode\" method=\"post\" action=\"forgot\" onsubmit=\"return(valiate())\">"
                            + "<input type=\"text\" name=\"mycode\" placeholder=\"Enter 7 digit Code Here\" size=\"31\" class=\"tfd\" /><p/>"
                            + "<input type=\"hidden\" name=\"vcode\" value=\""+String.valueOf(random)+"\" />"
                            + "<input type=\"hidden\" name=\"done\" value=\""+request.getParameter("emailo")+"\" />"
                            + "<button type=\"submit\" class=\"btn2\">Continue</button> </form><hr/>";
        */        
          
          String dt=new java.util.Date().toString();
        
        //send customer message
                                PreparedStatement pst4=con.prepareStatement("insert into ctcus(subject,content,date_time,own)"
                                        + " values(?,?,?,?)"); pst4.setString(1,"Reset");
                                        pst4.setString(2,"Requesting A Password Reset"); pst4.setString(3,dt); pst4.setString(4,request.getParameter("emailo"));
                                        
                                        //Ohhohoooh DB!!
                                        pst4.executeUpdate();
                                        
                                        body=body+"<hr/>Success!<p/>Your request to reset your password has been successfully Submitted. Please watch "
                                                + "out for an email from us concerning your request.<p/>"
                                                + "<a href=\"index.html\"><button class=\"btn2\">Ok Thanks</button></a><hr/>";
        
        
            } else {
                
                body=body+"<hr/>Failed! Sorry, We found no account registered with "+request.getParameter("emailo")+". Please check your email"
                        + " and try agin.<hr/><a href=\"sign.html\"><button class=\"btn2\">Return</button></a><hr/>";
                
            }
            
        }
        
        if(request.getParameter("done")!=null) {
            
            body=body+"<hr/><p class=\"hedi\">Reset Password</p>"+request.getParameter("done")+"<hr/>"
                    + "<form name=\"respas\" method=\"post\" action=\"resetpass\" onsubmit=\"return(rist())\" >"
                    + "<input type=\"password\" size=\"51\" name=\"newp\" class=\"tfd\" placeholder=\"Enter new Password\" /><p/>"
                    + "<input type=\"password\" size=\"51\" name=\"conp\" class=\"tfd\" placeholder=\"Confirm new Password\" /><p/>"
                    + "<input type=\"hidden\" name=\"done\" value=\""+request.getParameter("done")+"\" />"
                    + "<button type=\"submit\" class=\"btn\">Submit</button>"
                    + "</form><hr/>";
            
        }
        
        //Position 
        
        String hom="<a href=\"index.html\">", btn=u.signin;
        
        String content=u.head1+hom+u.head2+btn+u.head3+"FORGOT PASSWORD"+u.comph+body+u.foot1+hom+u.foot2;
        
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
        return "Forgot Password help servlet.";
    }

}
