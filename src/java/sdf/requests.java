
package sdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import eu.bitwalker.useragentutils.*;
import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "requests", urlPatterns = {"/requests"})
public class requests extends HttpServlet {

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
                            if(request.getParameter("del")==null && request.getParameter("pay")==null) { 
                                if(request.getParameter("rmvv")==null) {
                                
                                body=body+"<p class=\"hedi\">Review Your Requests</p><hr/>"
                                        + "<table border=\"5\" bordercolor=\"deeppink\" cellpadding=\"5\" cellspacing=\"5\" "
                                        + "bgcolor=\"pink\" width=\"100%\">";
                                
                                //lets get the data from the base
                                PreparedStatement pst6=con.prepareStatement("select *from requests where email=? || own=?");
                                pst6.setString(1,user); pst6.setString(2,user);
                                ResultSet rs6=pst6.executeQuery();
                                
                                rs6.last();
                                
                                if(rs6.last()) {
                                    
                                    body=body+"";
                                    if(rs6.getString("email").equals(user) && rs6.getString("status").equals("waiting")) {
                                        
                                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs6.getString("own")+" requested $"+rs6.getString("amount")+" from You on "
                                                +rs6.getString("date_time")+"</th>"+
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"pay\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<input type=\"hidden\" name=\"amti\" value=\""+rs6.getString("amount")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Approve</button>"
                                                + "</form></th>"+
                                                
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"rmvv\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Decline</button>"
                                                + "</form></th>";
                                        
                                    }
                                    if(rs6.getString("own").equals(user) && rs6.getString("status").equals("waiting")) {
                                        
                                        body=body+"<tr><th bgcolor=\"whitesmoke\">You requested $"+rs6.getString("amount")+" from "+rs6.getString("email")+" on "
                                                +rs6.getString("date_time")+"</th>"+
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"del\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Cancel</button>"
                                                + "</form></th>";
                                        
                                    }
                                    
                                }
                                
                                while(rs6.previous()) {
                                    
                                    if(rs6.getString("email").equals(user) && rs6.getString("status").equals("waiting")) {
                                        
                                        body=body+"<tr><th bgcolor=\"whitesmoke\">"+rs6.getString("own")+" requested $"+rs6.getString("amount")+" from You on "
                                                +rs6.getString("date_time")+"</th>"+
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"pay\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<input type=\"hidden\" name=\"amti\" value=\""+rs6.getString("amount")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Approve</button>"
                                                + "</form></th>"+
                                                
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"rmvv\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Decline</button>"
                                                + "</form></th>";
                                        
                                    }
                                    if(rs6.getString("own").equals(user) && rs6.getString("status").equals("waiting")) {
                                        
                                        body=body+"<tr><th bgcolor=\"whitesmoke\">You requested $"+rs6.getString("amount")+" from "+rs6.getString("email")+" on "
                                                +rs6.getString("date_time")+"</th>"+
                                                "<th bgcolor=\"azure\"><form action=\"requests\" method=\"post\">"
                                                + "<input type=\"hidden\" name=\"del\" value=\""+rs6.getString("ID")+"\" />"
                                                + "<button type=\"submit\" class=\"btn2\">Cancel</button>"
                                                + "</form></th>";
                                        
                                    }
                                    
                                }
                                
                                body=body+"</table><hr/>";
                                }
                            }
                            
                            if(request.getParameter("del")!=null) {
                                
                                PreparedStatement pst3=con.prepareStatement("update requests set status=? where ID=?");
                                pst3.setString(1,"ignore"); pst3.setString(2,request.getParameter("del"));
                                
                                //update base
                                pst3.executeUpdate();
                                
                                //tell user
                                body=body+"Success! Hello "+rs.getString("lname")+", Your money request with ID <b>"+request.getParameter("del")+
                                        "</b> has been cancelled. If you want to request funds again, please click <a href=\"receive\">HERE</a>"
                                        + "<p/><a href=\"requests\"><button class=\"btn2\">OK</button></a>";
                                
                            }
                            
                            if(request.getParameter("pay")!=null) {
                                
                                if(Integer.valueOf(rs.getString("main"))>=Integer.valueOf(request.getParameter("amti"))) { 
                                System.out.println("Suff");
                                    
                                PreparedStatement pst3=con.prepareStatement("update requests set status=? where ID=?");
                                pst3.setString(1,"ignore"); pst3.setString(2,request.getParameter("pay"));
                                
                                //update base
                                pst3.executeUpdate();
                                
                                //Handle Transaction storage
                                //On Both clients
                                Integer a=0,b=0,c=0; String fro=null,to=null;
                                
                                //get replace value diff
                                PreparedStatement pst6=con.prepareStatement("select *from requests where ID=?");
                                pst6.setString(1,request.getParameter("pay")); 
                                ResultSet rs6=pst6.executeQuery();
                                if(rs6.next()) {
                                    
                                    c=Integer.valueOf(rs6.getString("amount"));
                                    fro=rs6.getString("email"); to=rs6.getString("own");
                                    
                                }
                                
                                //get replace val a
                                PreparedStatement pst7=con.prepareStatement("select *from profile where email=?");
                                pst7.setString(1,fro); ResultSet rs7=pst7.executeQuery();
                                if(rs7.next()) {
                                    
                                    a=Integer.valueOf(rs7.getString("main")) - c;
                                    
                                }
                                
                                //get replace val b
                                PreparedStatement pst8=con.prepareStatement("select *from profile where email=?");
                                pst8.setString(1,to); ResultSet rs8=pst8.executeQuery();
                                if(rs8.next()) {
                                    
                                    b=Integer.valueOf(rs8.getString("main")) + c;
                                    
                                }
                                
                                //Update All Balances
                                PreparedStatement pst9=con.prepareStatement("update profile set main=? where email=?");
                                pst9.setString(1,String.valueOf(a)); pst9.setString(2,fro);
                                
                                pst9.executeUpdate();
                                
                                //2
                                PreparedStatement pst10=con.prepareStatement("update profile set main=? where email=?");
                                pst10.setString(1,String.valueOf(b)); pst10.setString(2,to);
                                
                                pst10.executeUpdate();
                                
                                //update balances DONE!!!
                                //Now Lets add these transactions
                                
                                //Lets Begin!!!
                                PreparedStatement pst4=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                                pst4.setString(1,fro); pst4.setString(2,"Send Payment"); pst4.setString(3,to); pst4.setString(4,dt); 
                                pst4.setString(5,String.valueOf(c)); pst4.setString(6,String.valueOf(a)); pst4.setString(7,"completed");
                                //goggle
                                pst4.executeUpdate();
                                
                                ///Number twooooo
                                PreparedStatement pst12=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                                pst12.setString(1,to); pst12.setString(2,"Receive Payment"); pst12.setString(3,fro); pst12.setString(4,dt); 
                                pst12.setString(5,String.valueOf(c)); pst12.setString(6,String.valueOf(b)); pst12.setString(7,"completed");
                                //goggle
                                pst12.executeUpdate();
                                
                                //DOONNEE!!! SEEEE!?
                                
                                //Let the user Know
                                body=body+"Success!<br/>Hello "+rs.getString("lname")+", Amount $"+c+" has been successfully paid to "+to+". "
                                        + "Thanks.<p/><a href=\"requests\"><button class=\"btn2\">OK</button></a>";
                                } else {
                                    
                                    body=body+"<hr/>Sorry, This transaction could not be completed at this moment. <br/>This is because your account "
                             + "balance is insufficient to complete this Action.<p/>Please add more funds and try again to approve this request."
                             + "<p/>"
                             + "<a href=\"requests\"><button class=\"btn2\">Return</button></a><hr/>";
                                    
                                }
                            }
                            
                            if(request.getParameter("rmvv")!=null) {
                                
                                PreparedStatement pst3=con.prepareStatement("update requests set status=? where ID=?");
                                pst3.setString(1,"ignore"); pst3.setString(2,request.getParameter("rmvv"));
                                
                                //update base
                                pst3.executeUpdate();
                                
                                body=body+"Success! Money request with ID <b>"+request.getParameter("rmvv")+"</b> has been successfully declined."
                                        + "<p/>"
                                        + "<a href=\"requests\"><button class=\"btn2\">OK</button></a>";
                                
                            }
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"REQUESTS"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Money you have been requested and you requested!";
    }

}
