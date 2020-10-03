
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
@WebServlet(name = "newpayroll", urlPatterns = {"/newpayroll"})
public class newpayroll extends HttpServlet {

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
                            + "</table><p/><hr/>"+
                            
                            //Here is my body
                            "";
                    
                    if(request.getParameter("pname")==null) {
                        
                        body=body+"<p class=\"hedi\">Create New Payroll<p/><hr/>"
                                + "Please Fill These Fields<p/>"
                                + "<form name=\"payrol\" action=\"newpayroll\" method=\"post\" onsubmit=\"return(crtpl())\" >"
                                + "<input type=\"text\" class=\"tfd\" size=\"41\" name=\"pname\" placeholder=\"Payroll Name\" /><p/>"
                                + "<input type=\"text\" class=\"tfd\" size=\"41\" name=\"com\" placeholder=\"Company\" /><p/>"
                                + "<button type=\"submit\" class=\"btn2\">Create Payroll Now</button>"
                                + "</form><hr/>";
                        
                    }
                    
                    if(request.getParameter("pname")!=null) {
                        
                        PreparedStatement pst7=con.prepareStatement("insert into payroll(Pname,Pown,Company) values(?,?,?)");
                        pst7.setString(1,request.getParameter("pname")); pst7.setString(2,user);
                        pst7.setString(3,request.getParameter("com"));
                        
                        pst7.executeUpdate();
                        
                        body=body+"Success!<hr/>Payroll "+request.getParameter("pname")+" of "+request.getParameter("com")+" has been "
                                + "successfully created. You can now Enter and add members to this payroll.<hr/>"
                                + "<a href=\"payroll\"><button class=\"btn\">Ok Thanks</button></a><hr/>";
                        
                    }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"NEW PAYROLL"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Create New Payroll";
    }

}
