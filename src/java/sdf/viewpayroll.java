
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
@WebServlet(name = "viewpayroll", urlPatterns = {"/viewpayroll"})
public class viewpayroll extends HttpServlet {

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
                        
                        if(request.getParameter("rmv")==null && request.getParameter("tit")==null) {
                            
                            body=body+"<p class=\"hedi\">Payroll Entry</p><hr/>"
                                    
                                    + "<form action=\"payall\" method=\"post\">"
                                        + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                        + "<button type=\"submit\" class=\"btn\">Pay This Payroll Now</button></form><hr/>"
                                    
                                    + "<table border=\"8\" bordercolor=\"navy\" cellpadding=\"7\" cellspacing=\"7\" bgcolor=\"whitesmoke\" width=\"100%\">";
                                   
                            
                            PreparedStatement pst3=con.prepareStatement("select *from payroller where PID=?");
                            pst3.setString(1,request.getParameter("view"));
                            ResultSet rs3=pst3.executeQuery();
                            
                            while(rs3.next()) {
                                
                                body=body+"<tr><th>"+rs3.getString("Name")+"</th><th>"+rs3.getString("Title")+"</th><th>"+rs3.getString("Amount")+"</th>"
                                        + "<th><form method=\"post\" action=\"viewpayroll\">"
                                        + "<input type=\"hidden\" name=\"rmv\" value=\""+rs3.getString("ID")+"\" />"
                                        + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                        + "<button type=\"submit\" class=\"btn2\">Delete Now</button></form></th></tr>";
                                
                            }
                            
                            body=body+"</table>"
                                    + "<p class=\"hhdd\">Add New To Payroll</p><hr/>"
                                    + "<form name=\"adp\" method=\"post\" action=\"viewpayroll\" onsubmit=\"return(addpl())\">"
                                    + "<input type=\"text\" name=\"fname\" class=\"tfd\" size=\"41\" placeholder=\"Full Names\" /><p/>"
                                    + "<input type=\"text\" name=\"tit\" class=\"tfd\" size=\"41\" placeholder=\"Title\" /><p/>"
                                    + "<input type=\"number\" step=\"0.01\" name=\"amt\" class=\"tfd\" size=\"41\" placeholder=\"Amount\" /><p/>"
                                    + "<input type=\"text\" name=\"mail\" class=\"tfd\" size=\"41\" placeholder=\"Email\" /><p/>"
                                    + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                    + "<button type=\"submit\" class=\"btn\">Add Now</button></form><hr/>";
                            
                        }
                        
                        if(request.getParameter("rmv")==null && request.getParameter("tit")!=null) {
                            
                            //See if user exists
                            boolean exis=false;
                            
                            PreparedStatement pst3=con.prepareStatement("select *from profile where email=?");
                            pst3.setString(1,request.getParameter("mail")); ResultSet rs3=pst3.executeQuery();
                            
                            if(rs3.next()) {
                                exis=true;
                            }
                            
                            if(exis) {
                            
                            //take action
                            PreparedStatement pst4=con.prepareStatement("insert into payroller(Name,Title,Amount,PID,Email) "
                                    + "values(?,?,?,?,?)");
                            pst4.setString(1,request.getParameter("fname")); pst4.setString(2,request.getParameter("tit"));
                            pst4.setString(3,request.getParameter("amt")); pst4.setString(4,request.getParameter("view"));
                            pst4.setString(5,request.getParameter("mail"));
                            
                            pst4.executeUpdate();
                            
                            //let user know
                            body=body+"Success!<p/>"+request.getParameter("fname")+" has been successfully added to payroll ID: "
                                    +request.getParameter("view")+". <hr/>"
                                    + "<form action=\"viewpayroll\" method=\"post\">"
                                    + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                    + "<button type=\"submit\" class=\"btn\">Ok Thanks</button></form><hr/>";
                            
                            } else {
                                
                                body=body+"Error!<p/>"
                                        + "This action to add specified user to payroll could not be completed. This is because "
                                        + "the user with names and email provided does not exist on our network.<hr/>"
                                        
                                        + "<form action=\"viewpayroll\" method=\"post\">"
                                        + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                        + "<button type=\"submit\" class=\"btn\">Return</button></form><hr/>";
                                
                            }
                        }
                        
                        if(request.getParameter("rmv")!=null && request.getParameter("tit")==null) {
                            
                            PreparedStatement pst3=con.prepareStatement("delete from payroller where ID=?");
                            pst3.setString(1,request.getParameter("rmv"));
                            
                            pst3.executeUpdate();
                            
                            //let him know
                            body=body+"Success!<p/>"
                                    + "User with ID: "+request.getParameter("rmv")+" has been deleted from payroll with ID: "+
                                    request.getParameter("view")+".<hr/>"
                                    
                                    + "<form action=\"viewpayroll\" method=\"post\">"
                                        + "<input type=\"hidden\" name=\"view\" value=\""+request.getParameter("view")+"\" />"
                                        + "<button type=\"submit\" class=\"btn\">Ok Thanks</button></form><hr/>";
                            
                        }
                        
                    } else {
                        response.sendRedirect("payroll");
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
        return "View Payrolls";
    }

}
