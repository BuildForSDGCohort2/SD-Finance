
package paypal;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;
import eu.bitwalker.useragentutils.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "Receipt", urlPatterns = {"/Receipt"})
public class Receipt extends HttpServlet {

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
                            + "</table><p/><hr/>";
                    
                    //lets get the attributes
                    Transaction trn=(Transaction)request.getAttribute("transaction");
                    PayerInfo pyr=(PayerInfo)request.getAttribute("payer");
                    
                    //deposit The Money For This comrade
                    
                    float total=Float.valueOf(rs.getString("main"))+Float.valueOf(trn.getAmount().getDetails().getSubtotal());
                    
                    PreparedStatement pst3=con.prepareStatement("update profile set main=? where email=?");
                    pst3.setString(1,String.valueOf(total)); pst3.setString(2,user);
                    
                    pst3.executeUpdate();
                    
                    //Add to transactions
                    PreparedStatement pst5=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                     pst5.setString(1,user); pst5.setString(2,"depositing"); pst5.setString(3,"Paypal"); pst5.setString(4,dt);
                     pst5.setString(5,trn.getAmount().getDetails().getSubtotal()); pst5.setString(6,String.valueOf(total)); pst5.setString(7,"completed");
                     
                     pst5.executeUpdate();
                    
                            
                            //Here is my body
                            body=body+"<font color=\"green\" size=\"+2\">Success!</font><p/>"
                                    + "Your deposit of $"+trn.getAmount().getDetails().getSubtotal()+" using Paypal has been successfully completed.<hr/>"
                                    + "<h3>Transaction Details</h3>"
                                    + "Merchant: SD Finance<p/>"
                                    + "User Names: "+pyr.getFirstName()+" "+pyr.getLastName()+"<p/>"
                                    + "Amount: $"+trn.getAmount().getDetails().getSubtotal()+"<hr/>"
                                    + "Thank You "+rs.getString("lname")+"<p/>"
                                    + "<a href=\"login\"><button class=\"btn2\">Ok You Are Welcome</button></a><hr/>";
                    
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
        return "Receipt";
    }

}
