
package sdf;

import java.io.*;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;
import eu.bitwalker.useragentutils.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
            
            //get user local detailsS
            
            String userAgentString = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            OperatingSystem os = userAgent.getOperatingSystem();
            Browser br=userAgent.getBrowser();
            /*WE GO*/
            String platform=os.getName()+" : "+br.getName();
            java.util.Date dn=new java.util.Date();
            String dt=dn.toString().replaceAll("MST"," | ");
            
            //initialising factory
            factory u=new factory();
        
        //I am now establishing my Database Connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host, u.user, u.password);
        
        //create the page body content
        String body;
        
        String hom="<a href=\"login\">", btn=u.menu;
        
        String content;
        
        //
        
        try (PrintWriter out = response.getWriter()) {  if(user!=null) { 
            
            //check on user status
            String qry="select *from profile where email=?"; 
            PreparedStatement pst=con.prepareStatement(qry); pst.setString(1,user);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()) {
                if(rs.getString("status").equals("approved")) { 
                    
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
                    
                    try {
                    u.activate(user); 
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                            
                    //let us get boss and solicit account balances
                            float bs=0,sc=0;
                            //boss
                            PreparedStatement pst7=con.prepareStatement("select *from requests where email=? && status=\"waiting\"");
                            pst7.setString(1,user); ResultSet rs7=pst7.executeQuery();
                            while(rs7.next()) {
                                bs=bs+Float.valueOf(rs7.getString("amount"));
                            }
                            //solicit
                            PreparedStatement pst8=con.prepareStatement("select *from requests where own=? && status=\"waiting\"");
                            pst8.setString(1,user); ResultSet rs8=pst8.executeQuery();
                            while(rs8.next()) {
                                sc=sc+Float.valueOf(rs8.getString("amount"));
                            }
                            
                  body=body + "<div class=\"hedi\">Balances</div>"
                            + "<table border=\"9\" bordercolor=\"navy\" cellpadding=\"3\" cellspacing=\"3\" bgcolor=\"white\" width=\"100%\">"
                            + "<tr>"
                            + "<th class=\"tdn\">"
                            + "<h3>Main</h3><p/>"
                            + "<b>This is your main account where your available money is!</b>"
                            + "<h2>$"+rs.getString("main")+"<p/>*******"
                            + "</th>"
                            + "<th class=\"tdn\">"
                            + "<h3>Boss</h3><p/>"
                            + "<b>This is the money requested from you by other user(s)</b>"
                            + "<h2>$"+bs+"<p/>*******"
                            + "</th>"
                            + "<th class=\"tdn\">"
                            + "<h3>Solicit</h3><p/>"
                            + "<b>This is the money you requested from other user(s)</b>"
                            + "<h2>$"+sc+"<p/>*******"
                            + "</th>"
                            + "</tr>"
                            + "</table><hr/><div class=\"hedi\">Recent Transactions</div><hr/>"
                          + "<div style=\"background-color: lightslategrey;\">";
                            
                            /*
                            Here let us get the last 7 transactions
                            */
                            String qry4="select *from transactions where email=?";
                            PreparedStatement pst4=con.prepareStatement(qry4); pst4.setString(1,user);
                            ResultSet rs4=pst4.executeQuery();
                            List<String> yu=new ArrayList();
                            
                            while(rs4.next()) {
                                yu.add(rs4.getString("ID")+"<br/>"+rs4.getString("trn_type")+"    :::    $"+rs4.getString("amount")+"    :::    "+rs4.getString("contact")
                                +"    :::    "+rs4.getString("date_time")+"<br/>balance: $"+rs4.getString("balance")+"    :::    "+rs4.getString("status")+"");
                            }
                            
                            int times=0;
                            for(int i=yu.size()-1; i>-1; i--) {
                                if(times<8) {
                                body=body+"<div style=\"background-color: navy; color: white;\">"+yu.get(i)+"</div><hr/>";
                                }
                                times++;
                            }
                            body=body+"</div><p/>";
                            
                            content=u.head1+hom+u.head2+btn+u.head3+"WELCOME"+u.comph+body+u.foot1+hom+u.foot2;
                            
                            out.println(content);
                            
                            try {
                notifyme m=new notifyme(); m.doNofify("Login", dt, user, platform);
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                            
                }else if(rs.getString("status").equals("pending")) {
                    body="<hr/><h3>ACCOUNT STATUS</h3><hr/>"
                        + "Your account is currently under review. This will be completed within a few hours.<p/>"
                        + "<b>Email: </b>"+rs.getString("email")+"<p/><b>User Names: </b>"+rs.getString("fname")+" "+rs.getString("lname")+""
                        + "<p/><b>Account Status: </b>Pending Approval<p/><a href=\"logout.jsp\"><button class=\"btn\">LOG OUT</button></a>";
                
                String cont=u.head1+hom+u.head2+u.head3+"WELCOME"+u.comph+body+u.foot1+hom+u.foot2;
                out.println(cont);
                } else if(rs.getString("status").equals("waiting")) {
                    response.sendRedirect("valiate");
                }
                
                
            } else {
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
        return "This servlet authenticates the input from user prompting to login.";
    }

}
