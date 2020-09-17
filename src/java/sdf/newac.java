
package sdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import eu.bitwalker.useragentutils.*;
import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "newac", urlPatterns = {"/newac"})
public class newac extends HttpServlet {

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
                            + "<b>Platfrom: </b>"+platform+"<p/>"
                            + "<b>Date & Time: </b>"+dt
                            + "</th>"
                            + "</tr>"
                            + "</table><p/><hr/>";
                            
                            //Here is my body
                            if(request.getParameter("svc")==null && request.getParameter("con")==null) {
                                
                                body=body+"<h3>Please fill the required fields below</h3><hr/>"
                                        + "<form action=\"newac\" method=\"post\" name=\"new\" onsubmit=\"return(newvl())\">"
                                        + "<select name=\"type\" class=\"tfd\">"
                                        + "<option value=\"\" selected>Select type</option>"
                                        + "<option value=\"bank\">Bank Account</option>"
                                        + "<option value=\"paypal\">Paypal</option>"
                                        + "<option value=\"phone\">Phone</option>"
                                        + "</select><p/>"
                                        + "<input type=\"text\" class=\"tfd\" size=\"41\" placeholder=\"Service provider e.g. Standard Bank\" name=\"svc\" /><p/>"
                                        + "<input type=\"text\" class=\"tfd\" size=\"41\" placeholder=\"Country e.g. Uganda\" name=\"ctry\" /><p/>"
                                        + "<input type=\"text\" class=\"tfd\" size=\"41\" placeholder=\"Account Number/Phone/Paypal\" name=\"acn\" /><p/>"
                                        
                                        + "<input type=\"text\" class=\"tfd\" size=\"41\" placeholder=\"SWIFT/BIC Code (for bank A/C only)\" name=\"bic\" /><hr/>"
                                        + "By clicking submit below you agree to the <a href=\"terms.html\">terms and conditions</a> as stated by SD Finance<p/>"
                                        + "<button type=\"submit\" class=\"btn\">Submit</button>"
                                        + "</form><hr/>";
                                
                            }
                            
                            if(request.getParameter("svc")!=null) {
                                
                                PreparedStatement pst5=con.prepareStatement("insert into withdraw(email,account_typr,service,country,account,BIC,status)"
                                        + " values(?,?,?,?,?,?,?)");
                                pst5.setString(1,user); pst5.setString(2,request.getParameter("type")); pst5.setString(3,request.getParameter("svc"));
                                pst5.setString(4,request.getParameter("ctry")); pst5.setString(5,request.getParameter("acn"));
                                pst5.setString(6,request.getParameter("bic")); pst5.setString(7,"pending");
                                
                                //insert to withdraw
                                pst5.executeUpdate();
                                
                                //let user know
                                body=body+"Success! Hello,"+rs.getString("lname")+", new account <b>"+request.getParameter("svc")+"</b> has been added to your Profile."
                                        + "<p/><a href=\"wacs\"><button class=\"btn2\">OK</button></a><hr/>";
                                
                            }
                            
                            if(request.getParameter("con")==null) {
                                
                                
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"ADD NEW A/C"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Adding new Withdraw Account to Profile";
    }

}
