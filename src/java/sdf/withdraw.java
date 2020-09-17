
package sdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.sql.*;
import eu.bitwalker.useragentutils.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "withdraw", urlPatterns = {"/withdraw"})
public class withdraw extends HttpServlet {

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
                            if(request.getParameter("amint")==null && request.getParameter("amo")==null) {
                            body=body+"<p class=\"hedi\">Withdraw Funds</p><hr/>"
                            + "<form method=\"post\" name=\"wi\" action=\"withdraw\" onsubmit=\"return(wit())\">"
                            + "<input type=\"number\" name=\"amint\" placeholder=\"Enter Amount\" class=\"tfd\" /><p/>"
                            + "<button class=\"btn\" type=\"submit\">Proceed</button>"
                            + "</form>"
                            + "<hr/>";
                            }
                            
                            if(request.getParameter("amint")!=null && request.getParameter("cool")==null) {
                                
                                body=body+"<p class=\"hedi\">Withdraw Accounts</p><hr/>"
                                        + "<table border=\"7\" bordercolor=\"darkred\" cellpadding=\"7\" cellspacing=\"7\" width=\"100%\" >";
                                        
                                //get withdraw accounts from db
                                PreparedStatement pst3=con.prepareStatement("select *from withdraw where email=?");
                                pst3.setString(1,user); ResultSet rs3=pst3.executeQuery();
                                
                                while(rs3.next()) {
                                    
                                    body=body+"<tr><th class=\"trn\" bgcolor=\"aliceblue\">"+rs3.getString("service")+"</th>"
                                            + "<th class=\"trn\" bgcolor=\"aliceblue\">"+rs3.getString("country")+"</th>"
                                            + "<th class=\"trn\" bgcolor=\"aliceblue\">"+rs3.getString("account")+"</th>"
                                            + "<th class=\"trn\" bgcolor=\"aliceblue\">"+rs3.getString("BIC")+"</th>"
                                            + "<th class=\"trn\" bgcolor=\"aliceblue\">";
                                            
                                            if(rs3.getString("status").equals("approved")) {
                                            
                                    body=body+"<form method=\"post\" action=\"withdraw\">"
                                            + "<input type=\"hidden\" name=\"cool\" value=\""+rs3.getString("ID")+"\" />"
                                            + "<input type=\"hidden\" name=\"amint\" value=\""+request.getParameter("amint")+"\" />"
                                            + "<button type=\"submit\" class=\"btn2\">Select</button>"
                                            + "</form>"; } else {
                                                
                                                body=body+"Pending approval";
                                            }
                                                    
                                                    
                                    body=body+"</th></tr>";
                                    
                                }
                                
                                body=body+"</table>";
                                
                            }
                            
                            if(request.getParameter("amint")!=null && request.getParameter("cool")!=null) {
                                
                                PreparedStatement pst4=con.prepareStatement("select *from withdraw where ID=?");
                                pst4.setString(1,request.getParameter("cool"));
                                ResultSet rs4=pst4.executeQuery();
                                if(rs4.next()) {
                                    
                                    body=body+"<h3>Withdrawing to : </h3>"+rs4.getString("service")+"<br/>"+rs4.getString("country")+"<br/>"
                                            + rs4.getString("account")+"<br/>"+rs4.getString("BIC")+"<hr/>"+
                                            
                                            "<h3>Amount : </h3>$<b>"+request.getParameter("amint")+"</b><hr/>"
                                            + "<form method=\"post\" action=\"withdraw\">"
                                            + "<input type=\"hidden\" name=\"civ\" value=\""+rs4.getString("ID")+"\" />"
                                            + "<input type=\"hidden\" name=\"amo\" value=\""+request.getParameter("amint")+"\" />"
                                            + "<button class=\"btn\" type=\"submit\">Confirm & Withdraw</button><hr/>";
                                }
                                
                            }
                            
                            if(request.getParameter("civ")!=null && request.getParameter("amo")!=null) {
                                
                                //let us place the withdaw
                                PreparedStatement pst5=con.prepareStatement("select *from withdraw where ID=?");
                                pst5.setString(1,request.getParameter("civ"));
                                ResultSet rs5=pst5.executeQuery();
                                if(rs5.next()) {
                                    
                                    if(Integer.valueOf(rs.getString("main"))>=Integer.valueOf(request.getParameter("amo"))) {
                                        
                                    //let us do the update on balance
                                    int balnow=Integer.valueOf(rs.getString("main"))-Integer.valueOf(request.getParameter("amo"));
                                    
                                    PreparedStatement pst7=con.prepareStatement("update profile set main=? where email=?");
                                    pst7.setString(1,String.valueOf(balnow)); pst7.setString(2,String.valueOf(user));
                                    
                                    pst7.executeUpdate();
                                    
                                    //place the transaction
                                    PreparedStatement pst8=con.prepareStatement("insert into transactions"
                                            + "(email,trn_type,contact,date_time,amount,balance,status) values(?,?,?,?,?,?,?)");
                                    
                                    pst8.setString(1,user); pst8.setString(2,"withdraw"); pst8.setString(3,rs5.getString("service"));
                                    pst8.setString(4,dt); pst8.setString(5,request.getParameter("amo")); pst8.setString(6,String.valueOf(balnow));
                                    pst8.setString(7,"pending");
                                    
                                    pst8.executeUpdate();
                                    
                                    body=body+"Hello "+rs.getString("lname")+", Your request to withdraw $"+request.getParameter("amo")+" to "
                                            + rs5.getString("service")+" has been successfully submitted. Please wait while we complete your "
                                            + "transaction in a few moments.<hr/><a href=\"withdraw\"><button class=\"btn2\">OK</button></a>";
                                    
                                    } 
                                    
                                    else {
                                        
                                        body=body+"Hello "+rs.getString("lname")+", Your request to withdraw $"+request.getParameter("amo")+" to "
                                            + rs5.getString("service")+" could not be processed at the moment. This is because your balance is insufficient"
                                                + " to complete this action."
                                                + "<hr/><a href=\"withdraw\"><button class=\"btn2\">Return</button></a>";
                                        
                                    }
                                }
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"WITHDRAW"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Short description";
    }

}
