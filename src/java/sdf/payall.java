
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
@WebServlet(name = "payall", urlPatterns = {"/payall"})
public class payall extends HttpServlet {

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
                    
                    if(request.getParameter("view")!=null) {
                        
                        try {
                        
                            if(u.IsActive(user)) {
                        //am sure let us 1st get The name of payroll
                        String payrol=null;
                        PreparedStatement pst2=con.prepareStatement("select *from payroll where ID=?");
                        pst2.setString(1,request.getParameter("view")); ResultSet rs2=pst2.executeQuery();
                        if(rs2.next()) {
                            
                            payrol=rs2.getString("Pname")+" - "+rs2.getString("Company");
                            
                        }
                        
                        PreparedStatement pst3=con.prepareStatement("select *from payroller where PID=?");
                        pst3.setString(1,request.getParameter("view")); ResultSet rs3=pst3.executeQuery();
                        
                        float total=0;
                        while(rs3.next()) {
                            total=total+Float.valueOf(rs3.getString("Amount"));
                        }
                        
                        if(Float.valueOf(rs.getString("main"))>=total) {
                            
                            float bal=Float.valueOf(rs.getString("main"))-total;
                            rs3.beforeFirst();
                            while(rs3.next()) {
                                
                                float indt=Float.valueOf(rs3.getString("Amount"));
                                //deal with balances first
                                PreparedStatement pst5=con.prepareStatement("select *from profile where email=?");
                                pst5.setString(1,rs3.getString("Email")); ResultSet rs5=pst5.executeQuery();
                                
                                if(rs5.next()) {
                                    indt=indt+Float.valueOf(rs5.getString("main"));
                                }
                                
                                
                                //add transaction
                                PreparedStatement pst4=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount"
                                        + ",balance,status) values(?,?,?,?,?,?,?)");
                                pst4.setString(1,rs3.getString("Email")); pst4.setString(2,"Receive Payment"); pst4.setString(3,user); 
                                pst4.setString(4,dt); pst4.setString(5,rs3.getString("Amount")); pst4.setString(6,String.valueOf(indt)); 
                                pst4.setString(7,"completed"); 
                                
                                pst4.executeUpdate();
                                
                                //update Ind bal
                                PreparedStatement pst8=con.prepareStatement("update profile set main=? where email=?");
                                pst8.setString(1,String.valueOf(indt)); pst8.setString(2,rs3.getString("Email"));
                                
                                pst8.executeUpdate();
                                
                            }
                            
                                //cur user transactions
                                PreparedStatement pst7=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount"
                                        + ",balance,status) values(?,?,?,?,?,?,?)");
                                pst7.setString(1,user); pst7.setString(2,"Send Payment"); pst7.setString(3,payrol);
                                pst7.setString(4,dt); pst7.setString(5,String.valueOf(total)); pst7.setString(6,String.valueOf(bal));
                                pst7.setString(7,"completed");
                                
                                pst7.executeUpdate();
                                
                                //update user bal
                                PreparedStatement pst9=con.prepareStatement("update profile set main=? where email=?");
                                pst9.setString(1,String.valueOf(bal)); pst9.setString(2,user);
                                
                                pst9.executeUpdate();
                                
                            
                            body=body+"Success!<p/>Payment of $"+total+" to "+payrol+" has been successfully completed.<hr/>"
                                        + "<a href=\"payroll\"><button class=\"btn2\">Ok Thanks</button></a><hr/>";
                            
                            try {
                    u.deactivate(user); 
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                            
                            
                        } else {
                            
                            body=body+"Failed!<p/>Payment of $"+total+" to "+payrol+" has been declined. This is because you do bot have enough "
                                    + "Funds on your account to complete this Transaction. <hr/>"
                                    + "<a href=\"payroll\"><button class=\"btn2\">Return</button></a><hr/>";
                            
                        }
                        
                    } else {
                                response.sendRedirect("login");
                            }
                    
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        }
                        
                    } else {
                        
                        body=body+"This Session has expired<p/>"
                                + "<a href=\"payroll\"><button class=\"btn\">Return</button></a><hr/>";
                        
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
        return "PayAll";
    }

}
