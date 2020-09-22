
package sdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "valiate", urlPatterns = {"/valiate"})
public class valiate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        
        //catch exceptions
        try {
            //identify user
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
            
            //initialising factory
            factory u=new factory(); String body,content="";
            String hom="<a href=\"login\">", btn=u.signin;
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        String qry="select *from profile where email=?";
        PreparedStatement pst=con.prepareStatement(qry); pst.setString(1,user);
        ResultSet rs=pst.executeQuery();
		
		String fn=null,ln=null;
		while(rs.next())
		{
			fn=rs.getString("fname"); ln=rs.getString("lname");
		}
        
                if(request.getParameter("mycode")==null && request.getParameter("done")==null)  {
        //generate a random code of 7digits
        int min=10000000,max=70000000;
        Random rd=new Random();
        int random=min+rd.nextInt(max);
        
        //OK! Let us send a varification email with code to the user
        //create the message
        String msg="Hello "+fn+" "+ln+",<p/>"
                + "Thank you for joining Us. Here we will move a cool financial journey with you. Just by your side.<p/>"
                + "This is to verify your email address. We have sent the 7 digit code. Please enter this code in required field to "
                + "continue. The code is provided below.<p/>Verification Code: "+String.valueOf(random)+"<p/>"
                + "Once again, We are super glad to have you here!";
        
        String mail=u.mail1+"Verification Code"+u.mail2+msg+u.mail3;
        
        //prepare
        Sender sd=new Sender("smtp.gmail.com");
        sd.setFro("info.seumxplus@gmail.com"); sd.setTo(user);
        sd.setUser("info.seumxplus@gmail.com"); sd.setPass("absolute13");
        sd.setSubject("SD Finance - Verification Code"); sd.setContent(mail); sd.arrange();
        
        //send
        sd.send(); 
        
        
        //create the page body content
        body="<hr/>We Just sent a CODE to "+user+". Please check your inbox and spam messages and  enter the code in the field below to continue.<p/>"
                            + "<form name=\"ecode\" method=\"post\" action=\"valiate\" onsubmit=\"return(valiate())\">"
                            + "<input type=\"text\" name=\"mycode\" placeholder=\"Enter 7 digit Code Here\" size=\"31\" class=\"tfd\" /><p/>"
                            + "<input type=\"hidden\" name=\"vcode\" value=\""+String.valueOf(random)+"\" />"
                            + "<input type=\"hidden\" name=\"done\" value=\"Vostage\" />"
                            + "<button type=\"submit\" class=\"btn2\">Continue</button> </form><hr/>";
        
        
        content=u.head1+hom+u.head2+btn+u.head3+"VALIDATION"+u.comph+body+u.foot1+hom+u.foot2; }
        
        try (PrintWriter out = response.getWriter()) {
            if(user!=null) {
                if(request.getParameter("mycode")!=null) {
                    
                    //see if the code is valid
                    if(request.getParameter("mycode").equals(request.getParameter("vcode"))) {
                        
                        //lets go to pending now
                        PreparedStatement pst3=con.prepareStatement("update profile set status=? where email=?");
                        pst3.setString(1,"pending"); pst3.setString(2,user);
                        
                        //Let proceed
                        pst3.executeUpdate();
                        
                        notifyme m=new notifyme(); m.doNofify("Verification Success!", "Not Captured", user, "No Captured");
                        
                        response.sendRedirect("login");
                        
                    } else {
                        
                        body="Sorry, we couldn't proceed with this process. <b>You just entered a wrong code</b><p/>"
                                + "<a href=\"sign.html\"><button class=\"btn2\">Return</button></a>";
                        
                        String cont1=u.head1+hom+u.head2+btn+u.head3+"VALIDATION SUCCESS"+u.comph+body+u.foot1+hom+u.foot2;
                        
                        out.println(cont1);
                    }
                    
                } else {
                out.println(content); }
                
            } else {
                response.sendRedirect("sign.html");
            }
        }
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
        processRequest(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        processRequest(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Confrimation Servlet to confirm user's email and identification.";
    }

}
