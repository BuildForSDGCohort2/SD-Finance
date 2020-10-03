
package sdf;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import eu.bitwalker.useragentutils.*;
import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "newmsg", urlPatterns = {"/newmsg"})
public class newmsg extends HttpServlet {

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
                            if(request.getParameter("tit")!=null) {
                                
                                boolean exist=false;
                                
                                PreparedStatement pst9=con.prepareStatement("select *from profile");
                                ResultSet rs9=pst9.executeQuery();
                                
                                while(rs9.next()) {
                                    if(rs9.getString("email").equals(request.getParameter("rec"))) {
                                        exist=true;
                                    }
                                }
                                
                                if(exist) {
                                
                                PreparedStatement pst3=con.prepareStatement("insert into covs(subject,date_time,own,recepiet) values(?,?,?,?)");
                                pst3.setString(1,request.getParameter("tit")); pst3.setString(2,dt);
                                pst3.setString(3,user); pst3.setString(4,request.getParameter("rec"));
                                
                                //dooo iiit
                                pst3.executeUpdate();
                                
                                //id for created cov
                                String cov=null;
                                
                                //lets now get our first message
                                PreparedStatement pst4=con.prepareStatement("select *from covs where own=?");
                                pst4.setString(1,user); ResultSet rs4=pst4.executeQuery();
                                rs4.last();
                                if(rs4.last()) {
                                    cov=rs4.getString("ID");
                                }
                                
                                //Put dat message there
                                PreparedStatement pst6=con.prepareStatement("insert into messages(ID,message,date_time,own) values(?,?,?,?)");
                                pst6.setString(1,cov); pst6.setString(2,"Reaching Out To: "+request.getParameter("rec")); 
                                pst6.setString(3,dt); pst6.setString(4,request.getParameter("rec"));
                                
                                pst6.executeUpdate();
                                
                                //Put dat message there
                                PreparedStatement pst5=con.prepareStatement("insert into messages(ID,message,date_time,own) values(?,?,?,?)");
                                pst5.setString(1,cov); pst5.setString(2,request.getParameter("mg").replaceAll("\n","<p/>")); 
                                pst5.setString(3,dt); pst5.setString(4,user);
                                
                                //Here we gooooooo!
                                pst5.executeUpdate();
                                
                                //let the user know
                                body=body+"<h4>Success!</h4>Your conversation has been successfully created. Go to <h4><i>Stream</i></h4>"
                                        + " to continue conversing. Thanks <hr/>"
                                        + "<a href=\"messages\"><button class=\"btn2\">Go To Stream</button></a><hr/>";
                                } else {
                                    
                                    body=body+"Failed! This action could not be completed. This is because the user you are trying to contact does not "
                                            + "exist on our network.<hr/> <a href=\"newmsg\"><button class=\"btn2\">Return</button></a><hr/>";
                                    
                                }
                                
                            } else {
                                
                                body=body+"<p class=\"hedi\">Starting a new conversation</p><hr/>"
                                        + "<b>Please Fill all the required field below</b><hr/>"
                                        + "<form name=\"newmg\" action=\"newmsg\" method=\"post\" onsubmit=\"return(newmsgvl())\" >"
                                        + "<input type=\"text\" size=\"51\" class=\"tfd\" placeholder=\"Enter Recepiet\" name=\"rec\" /><p/>"
                                        + "<input type=\"text\" size=\"51\" class=\"tfd\" placeholder=\"Enter Subject\" name=\"tit\" /><p/>"
                                        + "<textarea rows=\"8\" cols=\"51\" class=\"tfd\" placeholder=\"Enter Your initial message\" name=\"mg\"></textarea><hr/>"
                                        + "By clicking send below you agree to the <a href=\"terms\">terms and conditions</a> as stated by SD Finance.<p/>"
                                        + "<button type=\"submit\" class=\"btn\">Send</button>"
                                        + "</form><hr/>";
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"NEW CONVERSATION"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "New Conversation servlet...";
    }

}
