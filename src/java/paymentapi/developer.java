
package paymentapi;

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
@WebServlet(name = "developer", urlPatterns = {"/developer"})
public class developer extends HttpServlet {

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
            sdf.factory u=new sdf.factory();
        
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
                            + "</table><p/><hr/>"+
                            
                            //Here is my body
                            "<p class=\"hhdd\">Developer | Merchant Applications</p><hr/>"
                            + "<a href=\"newapp\"><button class=\"btn\">Create New App</button></a><hr/>"
                            + "<table border=\"7\" bordercolor=\"navy\" bgcolor=\"whitesmoke\" width=\"100%\" cellspacing=\"7\" cellpadding=\"7\">"
                            + "<tr>"
                            + "<th bgcolor=\"darkred\"><font size=\"+1\" color=\"white\">ID</font></th>"
                            + "<th bgcolor=\"darkred\"><font size=\"+1\" color=\"white\">App Name</font></th>"
                            + "<th bgcolor=\"darkred\"><font size=\"+1\" color=\"white\">Company</font></th>"
                            + "<th bgcolor=\"darkred\"><font size=\"+1\" color=\"white\">Mode</font></th>"
                            + "<th bgcolor=\"darkred\"><font size=\"+1\" color=\"white\">Action</font></th>"
                            + "</tr>";
                    
                    if(request.getParameter("activate")!=null) {
                        
                        PreparedStatement pst4=con.prepareStatement("update marchant set Status=\"active\" where ID=?");
                        pst4.setString(1,request.getParameter("activate")); 
                        
                        pst4.executeUpdate();
                        
                    }
                    
                    if(request.getParameter("deactivate")!=null) {
                        
                        PreparedStatement pst4=con.prepareStatement("update marchant set Status=\"demo\" where ID=?");
                        pst4.setString(1,request.getParameter("deactivate")); 
                        
                        pst4.executeUpdate();
                        
                    }
                    
                    //Get the values to the table
                    PreparedStatement pst3=con.prepareStatement("select *from marchant where Email=?");
                    pst3.setString(1,user); ResultSet rs3=pst3.executeQuery();
                    
                    while(rs3.next()) {
                        
                        body=body+"<tr><th>"+rs3.getString("ID")+"</th><th>"+rs3.getString("Mname")+"</th>"
                                + "<th>"+rs3.getString("Company")+"</th><th>"+rs3.getString("Status")+"</th>";
                        if(!rs3.getString("Status").equals("active")) {
                            
                                body=body+"<th><form method=\"post\" action=\"developer\">"
                                + "<input type=\"hidden\" name=\"activate\" value=\""+rs3.getString("ID")+"\" />"
                                        + "<button type=\"submit\" class=\"btn2\">Switch to Active</button></form></th></tr>";
                                
                        } else {
                            
                            body=body+"<th><form method=\"post\" action=\"developer\">"
                                + "<input type=\"hidden\" name=\"deactivate\" value=\""+rs3.getString("ID")+"\" />"
                                        + "<button type=\"submit\" class=\"btn2\">Switch to Demo</button></form></th></tr>";
                            
                        }
                        
                    }
                    
                    body=body+"</table><p/><hr/>";
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"DEVELOPER"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Marchants";
    }

}
