
package sdf;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;
import eu.bitwalker.useragentutils.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "receive", urlPatterns = {"/receive"})
public class receive extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
            factory u=new factory();
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        
        //get user local details
            String userAgentString = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            OperatingSystem os = userAgent.getOperatingSystem();
            Browser br=userAgent.getBrowser();
            /*WE GO*/
            String platform=os.getName()+" : "+br.getName();
            java.util.Date dn=new java.util.Date();
            String dt=dn.toString().replaceAll("MST"," | ");
            
            
        //create the page body content
        String body;
        
        String hom="<a href=\"login\">", btn=u.menu;
        
        try (PrintWriter out = response.getWriter()) { if(user!=null) {
            //check on user status
            String qry="select *from profile where email=?"; 
            PreparedStatement pst=con.prepareStatement(qry); pst.setString(1,user);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()) {
                if(rs.getString("status").equals("approved")) {
                    
                    //Now this is it
                    body="<hr/><p/><table border=\"0\" cellpadding=\"3\" cellspacing=\"3\" bgcolor=\"white\" width=\"100%\">"
                            + "<tr>"
                            + "<th class=\"tdn\">"
                            + "<h3>Hi "+rs.getString("fname")+" "+rs.getString("lname")+"</h3><p/>"
                            + "<b>Client ID: </b>"+rs.getString("ID")+"<p/>"
                            + "</th>"
                            + "<th class=\"tdn\">"
                            + "<b>Platform: </b>"+platform+"<p/>"
                            + "<b>Date & Time: </b>"+dt
                            + "</th>"
                            + "</tr>"
                            + "</table><p/><hr/>";
                            
                            //Here is my body
                            if(request.getParameter("clicka")!=null) {
                                
                                try {
                    u.activate(user); 
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                                
                                if(request.getParameter("clicka").equals("deposit")) {
                                    
                                    body=body+"";/*
                                            + "<b>By Clicking the Demo button below. You will reset your account balance to $1000"
                                            + " for DEMO since the project is still under demostrations and testings.</b><hr/>"
                                            + "<a href=\"Demo\"><button class=\"btn\">Demo Reset To $1000</button></a><hr/>"
                                            + "<a href=\"receive\"><button class=\"btn2\">Return</button></a><hr/>";*/
                                    
                                    body=body+"<p class=\"hhdd\">Deposit Funds</p><hr/><font face=\"Cambria\" color=\"navy\" size=\"+2\">Paypal</font><hr/>"
                                            + "<form action=\"AuthorizePaymentServlet\" method=\"post\" name=\"dep\" onsubmit=\"return(depvlo())\">"
                                            
                                            + "<input type=\"hidden\" name=\"product\" value=\"Deposit\" />"
                                            + "<input type=\"hidden\" name=\"shipping\" value=\"0\" />"
                                            + "<input type=\"hidden\" name=\"tax\" value=\"0\" />"
                                            
                                            + "<input type=\"hidden\" name=\"cancel\" value=\"http://env-8279297.lon.wafaicloud.com/SD-Finance/receive\" />"
                                            + "<input type=\"hidden\" name=\"return\" value=\"http://env-8279297.lon.wafaicloud.com/SD-Finance/ReviewPaymentServlet\" />"
                                            
                                            /*
                                            + "<input type=\"hidden\" name=\"cancel\" value=\"http://localhost:8080/SD-Finance/receive\" />"
                                            + "<input type=\"hidden\" name=\"return\" value=\"http://localhost:8080/SD-Finance/ReviewPaymentServlet\" />"
                                            */
                                            
                                            + "<input type=\"number\" step=\"0.01\" class=\"tfd\" size=\"41\" placeholder=\"Amount\" name=\"cool\" /><p/>"
                                            + "<button type=\"submit\" class=\"btn\">Proceed to Deposit</button><p/>"
                                            + "</form><hr/>";
                                    
                                }
                                if(request.getParameter("clicka").equals("request")) {
                                    
                                    try {
                    u.activate(user); 
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                                    
                                    body=body+"<h3>Request Money</h3>"
                                            + "<form name=\"senda\" method=\"post\" action=\"request\" onsubmit=\"return(sendvl())\">"
                                            + "<input type=\"text\" name=\"recpt\" size=\"41\" class=\"tfd\" placeholder=\"Enter email\" /><p/>"
                                            + "<input type=\"number\" step=\"0.01\" name=\"amont\" size=\"41\" class=\"tfd\" placeholder=\"Amount\" /><p/>"
                                            + "<button type=\"submit\" class=\"btn\">Request Money</button> </form><hr/>";
                                    
                                }
                                
                            } else {
                                
                                body=body+"<hr/><h3>Receive money to your account in two ways.<br/> *Deposit<br/> *Request Money"
                                        + "<div style=\"background-color: purple\"><hr/>"
                                        + "<form method=\"post\" action=\"receive\">"
                                        + "<input type=\"hidden\" name=\"clicka\" value=\"deposit\" />"
                                        + "<button type=\"submit\" class=\"btn2\">Deposit</button>"
                                        + "</form><p/>"
                                        
                                        + "<form method=\"post\" action=\"receive\">"
                                        + "<input type=\"hidden\" name=\"clicka\" value=\"request\" />"
                                        + "<button type=\"submit\" class=\"btn2\">Request</button>"
                                        + "</form><p/>"
                                        
                                        + "<hr/></div>";
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"RECEIVE"+u.comph+body+u.foot1+hom+u.foot2;
                    
                    out.println(content);
                    
                    
                }else {
                    response.sendRedirect("sign.html");
                }
            }else {
                    response.sendRedirect("sign.html");
                }
        }else {
                    response.sendRedirect("sign.html");
                }
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
        return "Servlet Managing Receiving of money.";
    }

}
