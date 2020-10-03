
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
@WebServlet(name = "payroll", urlPatterns = {"/payroll"})
public class payroll extends HttpServlet {

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
                            "<p class=\"hedi\">Pay Rolls</p><hr/>"
                            + "";
                    
                    if(request.getParameter("del")==null && request.getParameter("rmv")==null) {
                        
                        body=body+"<a href=\"newpayroll\"><button class=\"btn\">Create New Payroll</button></a><hr/>"
                                
                                + "<table border=\"8\" bordercolor=\"navy\" cellpadding=\"7\" cellspacing=\"7\" bgcolor=\"whitesmoke\" width=\"100%\">"
                                + "";
                        
                        //lets get the payrolls
                        PreparedStatement pst3=con.prepareStatement("select *from payroll where Pown=?");
                        pst3.setString(1,user); ResultSet rs3=pst3.executeQuery();
                        
                        while(rs3.next()) {
                            
                            body=body+"<tr><th class=\"trn\">"+rs3.getString("Pname")+" - "+rs3.getString("Company")+"</th>"
                                    + "<th><form action=\"viewpayroll\" method=\"post\">"
                                    + "<input type=\"hidden\" name=\"view\" value=\""+rs3.getString("ID")+"\" />"
                                    + "<button type=\"submit\" class=\"btn2\">Enter</button></form></th>"
                                    + "<th><form action=\"payroll\" method=\"post\">"
                                    + "<input type=\"hidden\" name=\"del\" value=\""+rs3.getString("ID")+"\" />"
                                    + "<button type=\"submit\" class=\"btn2\">Delete</button></form></th></tr>";
                            
                        }
                        
                        body=body+"</table><hr/>";
                        
                    }
                    
                    if(request.getParameter("del")!=null && request.getParameter("rmv")==null) {
                        
                        PreparedStatement pst4=con.prepareStatement("select *from payroll where ID=?");
                        pst4.setString(1,request.getParameter("del")); ResultSet rs4=pst4.executeQuery();
                        
                        if(rs4.next()) {
                            
                            body=body+"<p class=\"hedi\">Delete Payroll</p><hr/>"
                                    + "ID: "+rs4.getString("ID")+"<p/>"
                                    + "Payroll: "+rs4.getString("Pname")+"<p/>"
                                    + "Company: "+rs4.getString("Company")+"<hr/>"
                                    + "<form action=\"payroll\" method=\"post\">"
                                    + "<input type=\"hidden\" name=\"rmv\" value=\""+rs4.getString("ID")+"\" />"
                                    + "<button type=\"submit\" class=\"btn2\">Confirm & Delete</button></form><hr/>";
                            
                        }
                        
                    }
                    
                    if(request.getParameter("del")==null && request.getParameter("rmv")!=null) {
                        
                        PreparedStatement pst5=con.prepareStatement("delete from payroll where ID=?");
                        pst5.setString(1,request.getParameter("rmv"));
                        
                        pst5.executeUpdate();
                        
                        body=body+"<p class=\"hedi\">Deleted</p><hr/>"
                                + "Success!<p/>Payroll with ID: "+request.getParameter("rmv")+" has been deleted successfully.<hr/>"
                                + "<a href=\"payroll\"><button class=\"btn2\">Ok Thanks</button></a><hr/>";
                        
                    }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"PAYROLL"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Payrolls";
    }

}
