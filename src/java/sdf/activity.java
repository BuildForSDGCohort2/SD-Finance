
package sdf;

import eu.bitwalker.useragentutils.*;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "activity", urlPatterns = {"/activity"})
public class activity extends HttpServlet {

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
                    
                    if(request.getParameter("search")==null) {
                        
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
                            + "</table><p/><hr/>"+
                                
                                "<hr/><p class=\"hedi\">Transactions</p>"
                                + "<form action=\"activity\" method=\"post\">"
                                + "<input type=\"text\" name=\"search\" placeholder=\"Enter text to search\" size=\"51\" class=\"tfd\" />"
                                + "<button type=\"submit\" class=\"btn\">Search</button></form><p/><hr/>"
                                + "<table border=\"7\" bordercolor=\"darkslategrey\" cellpadding=\"7\" cellspacing=\"7\" bgcolor=\"white\" width=\"100%\">"
                                + "<tr><th class=\"txtw\" bgcolor=\"darkslateblue\">Transaction ID</th><th class=\"txtw\" bgcolor=\"darkslateblue\">Transaction Type</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Amount</th><th class=\"txtw\" bgcolor=\"darkslateblue\">Recipiet</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Date & Time</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Balance</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Status</th></tr>";
                        
                    PreparedStatement pst3=con.prepareStatement("select *from transactions where email=?");
                    pst3.setString(1,user); ResultSet rs3=pst3.executeQuery();
                    
                    rs3.last();
                    
                    if(rs3.last()) {
                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs3.getString("ID")+"</th><th class=\"txtwh\">"+rs3.getString("trn_type")+"</th>"
                                + "<th class=\"txtwh\">"+"$"+rs3.getString("amount")+"</th><th class=\"txtwh\">"+rs3.getString("contact")+"</th>"
                                + "<th class=\"txtwh\">"+rs3.getString("date_time")+"</th><th class=\"txtwh\">"+"$"+rs3.getString("balance")+"</th>"
                                + "<th class=\"textwh\">"+rs3.getString("status")+"</th></tr>";
                    }
                    
                    while(rs3.previous()) {
                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs3.getString("ID")+"</th><th class=\"txtwh\">"+rs3.getString("trn_type")+"</th>"
                                + "<th class=\"txtwh\">"+"$"+rs3.getString("amount")+"</th><th class=\"txtwh\">"+rs3.getString("contact")+"</th>"
                                + "<th class=\"txtwh\">"+rs3.getString("date_time")+"</th><th class=\"txtwh\">"+"$"+rs3.getString("balance")+"</th>"
                                + "<th class=\"textwh\">"+rs3.getString("status")+"</th></tr>";
                    }
                    
                    body=body+"</table><hr/>";
                    
                    String content=u.head1+hom+u.head2+btn+u.head3+"ACTIVITY"+u.comph+body+u.foot1+hom+u.foot2;
                    
                    out.println(content);
                    
                    } else {
                        
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
                                
                                "<hr/><p class=\"hedi\">Transactions</p>"
                                + "<form action=\"activity\" method=\"post\">"
                                + "<input type=\"text\" name=\"search\" placeholder=\"Enter text to search\" size=\"51\" class=\"tfd\" />"
                                + "<button type=\"submit\" class=\"btn\">Search</button></form><p/><hr/>"
                                + "<table border=\"7\" bordercolor=\"darkslategrey\" cellpadding=\"7\" cellspacing=\"7\" bgcolor=\"white\" width=\"100%\">"
                                + "<tr><th class=\"txtw\" bgcolor=\"darkslateblue\">Transaction ID</th><th class=\"txtw\" bgcolor=\"darkslateblue\">Transaction Type</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Amount</th><th class=\"txtw\" bgcolor=\"darkslateblue\">Recipiet</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Date & Time</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Balance</th>"
                                + "<th class=\"txtw\" bgcolor=\"darkslateblue\">Status</th></tr>";
                        
                    PreparedStatement pst3=con.prepareStatement("select *from transactions where email=?");
                    pst3.setString(1,user); ResultSet rs3=pst3.executeQuery();
                    String src;
                    
                    rs3.last();
                    
                    if(rs3.last()) {
                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs3.getString("ID")+"</th><th class=\"txtwh\">"+rs3.getString("trn_type")+"</th>"
                                + "<th class=\"txtwh\">"+"$"+rs3.getString("amount")+"</th><th class=\"txtwh\">"+rs3.getString("contact")+"</th>"
                                + "<th class=\"txtwh\">"+rs3.getString("date_time")+"</th><th class=\"txtwh\">"+"$"+rs3.getString("balance")+"</th>"
                                + "<th class=\"textwh\">"+rs3.getString("status")+"</th></tr>";
                    }
                    while(rs3.previous()) {
                        src=rs3.getString("ID")+" "+rs3.getString("trn_type")+" "+rs3.getString("amount")+" "+rs3.getString("contact")+" "
                                +rs3.getString("date_time")+" "+rs3.getString("balance")+rs3.getString("status");
                        
                        if(src.contains(request.getParameter("search"))) {
                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs3.getString("ID")+"</th><th class=\"txtwh\">"+rs3.getString("trn_type")+"</th>"
                                + "<th class=\"txtwh\">"+"$"+rs3.getString("amount")+"</th><th class=\"txtwh\">"+rs3.getString("contact")+"</th>"
                                + "<th class=\"txtwh\">"+rs3.getString("date_time")+"</th><th class=\"txtwh\">"+"$"+rs3.getString("balance")+"</th>"
                                + "<th class=\"textwh\">"+rs3.getString("status")+"</th></tr>";
                        }
                    }
                        
                    
                    body=body+"</table><hr/>";
                    
                    String content=u.head1+hom+u.head2+btn+u.head3+"ACTIVITY"+u.comph+body+u.foot1+hom+u.foot2;
                    
                    out.println(content);
                        
                    }
                    
                }else {
                    response.sendRedirect("sign.html");
                }
            }else {
                    response.sendRedirect("sign.html");
                }
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
        return "Activity Servlet";
    }

}
