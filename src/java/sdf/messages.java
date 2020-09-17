
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
@WebServlet(name = "messages", urlPatterns = {"/messages"})
public class messages extends HttpServlet {

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
                            + "</table><p/><hr/>"
                            
                            //Here is my body
                            +"<p class=\"hedi\">Conversations</p><hr/>"
                            + "<a href=\"newmsg\"><button class=\"btn2\">Start New Conversation</button></a><hr/>"
                            + "<table border=\"8\" bordercolor=\"purple\" \"cellpadding=\"3\" cellspacing=\"3\" bgcolor=\"aliceblue\" width=\"100%\"> <tr>";
                            
                            //let us do the conversations list
                            PreparedStatement pst3=con.prepareStatement("select *from covs where own=? || recepiet=?");
                            pst3.setString(1,user); pst3.setString(2,user); ResultSet rs3=pst3.executeQuery();
                            
                            rs3.last();
                            
                            body=body+"<th class=\"trn\" valign=\"top\">"
                                    + "<table border=\"0\" cellpadding=\"3\" cellspacing=\"3\" bgcolor=\"white\" width=\"100%\"> ";
                            
                            if(rs3.last()) {
                                body=body+"<tr><th class=\"trn\"><form action=\"messages\" method=\"post\">"
                                        + "<input type=\"hidden\" name=\"mssg\" value=\""+rs3.getString("ID")+"\" />"
                                        + "<input type=\"hidden\" name=\"tit\" value=\""+rs3.getString("subject")+"\" />"
                                        + "<input type=\"submit\" class=\"btn\" name=\"submit\" value=\""+rs3.getString("subject")+"\" /> </form></th></tr>";
                            }
                            
                            while(rs3.previous()) {
                                body=body+"<tr><th class=\"trn\"><form action=\"messages\" method=\"post\">"
                                        + "<input type=\"hidden\" name=\"mssg\" value=\""+rs3.getString("ID")+"\" />"
                                        + "<input type=\"hidden\" name=\"tit\" value=\""+rs3.getString("subject")+"\" />"
                                        + "<button type=\"submit\" class=\"btn\">"+rs3.getString("subject")+"</button></form></th></tr>";
                            }
                            
                            //complete subjects panel
                            body=body+"</table></th>  <th class=\"trn\" valign=\"top\">";
                            
                            //if subject selected view messages
                            if(request.getParameter("mssg")!=null && request.getParameter("tit")!=null) {
                                
                                
                                PreparedStatement pst8=con.prepareStatement("select *from messages where ID=?");
                                pst8.setString(1,request.getParameter("mssg"));
                                
                                ResultSet rs8=pst8.executeQuery(); rs8.last();
                                
                                if(rs8.last()) {
                                if(request.getParameter("mg")!=null && !rs8.getString("message").equals(request.getParameter("mg")+" -<hr/> "+user)) {
                                    
                                    PreparedStatement pst7=con.prepareStatement("insert into messages(ID,message,date_time,own) values(?,?,?,?)");
                                    pst7.setString(1,request.getParameter("mssg")); pst7.setString(2,request.getParameter("mg").replaceAll("\n","<p/>")+" -<hr/> "+user); 
                                    pst7.setString(3,dt); pst7.setString(4,user);
                                    
                                    //oohhhhh YEEEESSSSS!
                                    pst7.executeUpdate();
                                    
                                } }
                                
                                PreparedStatement pst4=con.prepareStatement("select *from messages where ID=?");
                                pst4.setString(1,request.getParameter("mssg")); ResultSet rs4=pst4.executeQuery(); 
                                
                                body=body+"<p class=\"hhdd\">"+request.getParameter("tit")+"</p><hr/>";
                                
                                String hl;
                                
                                while(rs4.next()) {
                                    
                                    if(rs4.getString("own").equals(user)) {
                                        hl="deepskyblue";
                                    } else {
                                        hl="white";
                                    }
                                    
                                    body=body+"<div style=\"background-color: "+hl+"\" class=\"trn\">"+rs4.getString("message")+"<br/><i>"+rs4.getString("date_time")+"</i></div><p/>";
                                    
                                }
                                
                                body=body+"<form name=\"msge\" action=\"messages\" method=\"post\" onsubmit=\"return(msgvl())\" >"
                                        + "<input type=\"hidden\" name=\"mssg\" value=\""+request.getParameter("mssg")+"\" />"
                                        + "<input type=\"hidden\" name=\"tit\" value=\""+request.getParameter("tit")+"\" />"
                                        + "<textarea rows=\"7\" cols=\"58\" name=\"mg\" placeholder=\"Enter Your New Message...\" class=\"tfd\"></textarea><hr/>"
                                        + "By clicking send below you agree to the <a href=\"terms\">terms and conditions</a> as stated by SD Finance.<p/>"
                                        + "<button type=\"submit\" class=\"btn2\">Send</button>"
                                        + "</form><hr/>"
                                        
                                        + "</th>";
                            }
                            
                            
                            
                            //body completion
                            body=body+"</tr></table><hr/>";
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"MESSAGING"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Messaging Servlet";
    }

}
