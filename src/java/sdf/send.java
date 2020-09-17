
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
@WebServlet(name = "send", urlPatterns = {"/send"})
public class send extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {  if(user!=null) {
            
            String qry="select *from profile where email=?"; 
            PreparedStatement pst=con.prepareStatement(qry); pst.setString(1,user);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()) {
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
             
             if(request.getParameter("recpt")==null && request.getParameter("amont")==null) {
                 if(request.getParameter("stat")==null){
             body=body+"<p class=\"hedi\">Send Money</p><hr/>"
                     + "<form method=\"post\" action=\"send\" name=\"senda\" onsubmit=\"return(sendvl())\" >"
                     + "<input type=\"text\" name=\"recpt\" placeholder=\"Enter email\" size=\"51\" class=\"tfd\" /><p/>"
                     + "<input type=\"number\" name=\"amont\" placeholder=\"Enter amount\" size=\"51\" class=\"tfd\" /><p/>"
                     + "<button type=\"submit\" class=\"btn\">Proceed</button></form><hr/>";
             
             String content=u.head1+hom+u.head2+btn+u.head3+"SEND"+u.comph+body+u.foot1+hom+u.foot2;
             out.println(content);}
             }
             
             if(request.getParameter("recpt")!=null && request.getParameter("amont")!=null) {
                 
                 //get recipiet details
                 PreparedStatement pst4=con.prepareStatement("select *from profile where email=?");
                 pst4.setString(1,request.getParameter("recpt")); ResultSet rs4=pst4.executeQuery();
                 
                 String name=null,mail=null,count=null,address=null;
                 
                 while(rs4.next()) {
                     name=rs4.getString("fname")+" "+rs4.getString("lname");
                     mail=rs4.getString("email"); count=rs.getString("country");
                     address=rs4.getString("address");
                 }
                 if(name!=null && !request.getParameter("recpt").equals(user)) {
                 body=body+"<p class=\"hedi\">Sending</p><hr/>"
                         + "<form method=\"post\" action=\"send\">"
                         + "<h4>Sending to: </h4>"
                         + "<b>"+name+"<br/>"+mail+"<br/>"+count+"<br/>"+address+"</b><hr/>"
                         + "<h4>Amount: </h4>$"+request.getParameter("amont")+"<p/>"
                         + "<input type=\"hidden\" name=\"stat\" value=\"ready\" />"
                         + "<input type=\"hidden\" name=\"mail\" value=\""+mail+"\" />"
                         + "<input type=\"hidden\" name=\"amount\" value=\""+request.getParameter("amont")+"\" /><hr/>"
                         + ""
                         + "<button class=\"btn\" type=\"submit\">Confirm and Send</button><hr/>"
                         + "</form>";
                 
                 String content=u.head1+hom+u.head2+btn+u.head3+"SEND"+u.comph+body+u.foot1+hom+u.foot2;
                   out.println(content);} else {
                     
                     //Tell that user does not exist
                     body=body+"<hr/>Sorry, the user you are trying to send money to does not exist on our network. <p/>"
                             + "<a href=\"send\"><button class=\"btn2\">Return</button><hr/>";
                     String content=u.head1+hom+u.head2+btn+u.head3+"SEND"+u.comph+body+u.foot1+hom+u.foot2;
                     out.println(content);
                 }
             }
             
             if(request.getParameter("stat")!=null) {
                 
                 int amt=Integer.valueOf(request.getParameter("amount"));
                 String mal=request.getParameter("mail");
                 
                 int selfup,clup=0; 
                 
                 if(Integer.valueOf(rs.getString("main"))>=amt) {
                     
                     //perform transaction on self
                     selfup=Integer.valueOf(rs.getString("main"))-amt;
                     
                     PreparedStatement pst4=con.prepareStatement("update profile set main=? where email=?");
                     pst4.setString(1,String.valueOf(selfup)); pst4.setString(2,String.valueOf(user));
                     
                     pst4.executeUpdate(); System.out.println("Update...01");
                     
                     //put the transaction to self
                     PreparedStatement pst5=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                     pst5.setString(1,user); pst5.setString(2,"sending"); pst5.setString(3,mal); pst5.setString(4,dt);
                     pst5.setString(5,String.valueOf(amt)); pst5.setString(6,String.valueOf(selfup)); pst5.setString(7,"completed");
                     
                     pst5.executeUpdate();  System.out.println("Update...02");
                     
                     
                     //perfrom transaction on client
                     PreparedStatement pst7=con.prepareStatement("select *from profile where email=?");
                     pst7.setString(1,mal); ResultSet rs7=pst7.executeQuery();
                     while(rs7.next()) {
                         clup=Integer.valueOf(rs7.getString("main"))+amt;
                     }
                     
                     PreparedStatement pst8=con.prepareStatement("update profile set main=? where email=?");
                     pst8.setString(1,String.valueOf(clup)); pst8.setString(2,mal);
                     
                     pst8.executeUpdate(); System.out.println("Update...03");
                     
                     //put the transaction to cl
                     PreparedStatement pst9=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                     pst9.setString(1,mal); pst9.setString(2,"receiving"); pst9.setString(3,user); pst9.setString(4,dt);
                     pst9.setString(5,String.valueOf(amt)); pst9.setString(6,String.valueOf(clup)); pst9.setString(7,"completed");
                     
                     pst9.executeUpdate(); System.out.println("Update...04");
                     
                     
                     body=body+"<hr/>Transaction successfully completed<p/>$"+amt+" has been sent to "+mal+""
                             + "<hr/><a href=\"send\"><button class=\"btn2\">Ok</button></a><hr/>";
                     
                     String content=u.head1+hom+u.head2+btn+u.head3+"SEND"+u.comph+body+u.foot1+hom+u.foot2;
                     out.println(content);
                     
                     
                 } else {
                     
                     body=body+"<hr/>Sorry, This transaction could not be completed at this moment. <br/>This is because your account "
                             + "balance is insufficient to complete this Action.<p/>Please add more funds and try again or "
                             + "Click <b><i>Return</i></b> below to try sending a figure less than or equal to your main account balance.<p/>"
                             + "<a href=\"send\"><button class=\"btn2\">Return</button></a><hr/>";
                     String content=u.head1+hom+u.head2+btn+u.head3+"SEND"+u.comph+body+u.foot1+hom+u.foot2;
                     out.println(content);
                 }
                 
             }
             
             
            } else {
                
                response.sendRedirect("signin.html");
                
            }
        } else {
            response.sendRedirect("signin.html");
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
