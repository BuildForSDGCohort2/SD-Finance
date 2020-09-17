
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
@WebServlet(name = "request", urlPatterns = {"/request"})
public class request extends HttpServlet {

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
                            + "<b>Platform: </b>"+platform+"<p/>"
                            + "<b>Date & Time: </b>"+dt
                            + "</th>"
                            + "</tr>"
                            + "</table><p/><hr/>";
                            
                            //Here is my body
                            if(request.getParameter("recpt")!=null && request.getParameter("amont")!=null) {
                                
                                PreparedStatement pst3=con.prepareStatement("select *from profile where email=?");
                                pst3.setString(1,request.getParameter("recpt")); ResultSet rs3=pst3.executeQuery();
                                
                                if(rs3.next() && !rs3.getString("email").equals(user)) {
                                    
                                    body=body+"<h3>Requesting Money from: </h3>"+rs3.getString("fname")+" "+rs3.getString("lname")+"<p/>"
                                            + ""+rs3.getString("email")+"<hr/><h3>Amount: </h3>$"+request.getParameter("amont")+"<hr/>"
                                            + "<form method=\"post\" action=\"request\">"
                                            + "<input type=\"hidden\" name=\"emalo\" value=\""+rs3.getString("email")+"\" />"
                                            + "<input type=\"hidden\" name=\"ammt\" value=\""+request.getParameter("amont")+"\" />"
                                            + "<button type=\"submit\" class=\"btn\">Confirm & Request</button></form><hr/>";
                                    
                                } else {
                                    
                                    body=body+"<hr/>Sorry, your request could not be progressed at the moment. This is because the user you are "
                                            + "trying to request money from does not exist on our network<hr/>"
                                            + "<a href=\"receive\"><button class=\"btn2\">Return</button></a><hr/>";
                                    
                                }
                                
                            } else if (request.getParameter("emalo")!=null && request.getParameter("ammt")!=null) {
                                
                                String qry3="insert into requests(email,date_time,amount,status,own) values(?,?,?,?,?)";
                                PreparedStatement pst5=con.prepareStatement(qry3);
                                pst5.setString(1,request.getParameter("emalo")); pst5.setString(2,dt); 
                                pst5.setString(3,request.getParameter("ammt")); pst5.setString(4,"waiting"); pst5.setString(5,user);
                                
                                //add to the request table
                                pst5.executeUpdate();
                                
                                //Inform user about that
                                body=body+"<hr/>Money request successfully submitted.<p/>Request of $"+request.getParameter("ammt")+" has been "
                                        + "successfully submitted to "+request.getParameter("emalo")+"<hr/>"
                                        + "<a href=\"receive\"><button class=\"btn2\">OK</button></a>";
                                
                                
                            } else {
                                
                                response.sendRedirect("receive");
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"REQUEST"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Request Money Servlet";
    }

}
