
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
@WebServlet(name = "doc", urlPatterns = {"/doc"})
public class doc extends HttpServlet {

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
                            "<p class=\"hhdd\">Walk Through Document</p><hr/>"
                            + "In this short tutorial, we are going to walk through the basics of using this platform. Below is the break down of "
                            + "it.<hr/><ul type=\"disc\">"
                            + "<li>Welcome</li>"
                            + "<li>Activity</li>"
                            + "<li>Sending Money</li>"
                            + "<li>Receiving Money</li>"
                            + "<li>Withdrawing Money</li>"
                            + "<li>Managing Withdraw Accounts</li>"
                            + "<li>Dealing with Requests</li>"
                            + "<li>Payrolls</li>"
                            + "<li>Messaging</li>"
                            + "<li>Developer/Merchant Mode</li>"
                            + "<li>Notifications</li>"
                            + "<li>Change Password</li>"
                            + "<li>Contact Us</li>"
                            + "</ul><hr/><h2 class=\"hhdd\">Welcome</h2>This is your initial display after logging in. You'll view your "
                            + "Balances and the last 8 transactions here<p/><h2 class=\"hhdd\">Activity</h2>When you click ACTIVITY on the "
                            + "Menu, you'll view all your performed transactions initially sorted to view most recent transactions first. Here "
                            + "you can search and sort the transactions by typing text related to your search and pressing search. You can "
                            + "search and sort by any text corresponding to any column. Forexample, 1. you can search by date, status, type, email. 2. "
                            + "You can search \"sending\" to view only sending transactions OR \"pending\" to view pending transactions. It will "
                            + "be sorted according to your search text.<p/><h2 class=\"hhdd\">Sending Money</h2>Click Send on the Menu to begin "
                            + "the sending process. You are requested to enter email of user and amount you wish to send to that particular user. "
                            + "You can Only send to registered users of this platform. If user email provided exists, You'll review the names "
                            + "of user and then confirm to send Money Instantly.<p/><h2 class=\"hhdd\">Receiving Money</h2>"
                            + "You can receive money on your account by either depositing or requesting from registered users.<br/>"
                            + "If you select Deposit, you'll be prompted to specify amount and then continue depositing using Paypal.<br/>"
                            + "If you select Request, you'll be prompted to provide user email and amount inorder to request money from user.<p/>"
                            + "<h2 class=\"hhdd\">Withdrawing Money</h2>Click Withdraw on the Menu to withdraw money to your registerd withdraw "
                            + "account. Enter amount and select withdraw account to submit your withdraw request.Please note that withdraw "
                            + "requests are approved and completed with 24HRS.<p/>"
                            + "<h2 class=\"hhdd\">Managing Withdraw Accounts</h2>Withdraw accounts are other registred accounts e.g. Bank, Paypal, "
                            + "Payoneer, Mobile Money where you can withdraw your funds to. When you request a withdraw, Money is sent to your selected "
                            + "withdraw account. On Manage Tab is where you can review, add and remove withdraw accounts associated with your SD Finance "
                            + "account.<p/><h2 class=\"hhdd\">Dealing with Requests</h2>Requests on the Menu is where you will review your sent and "
                            + "received Money requests. You can take three(3) actions here; 1. Cancel your money request if not interested. 2. "
                            + "Approve a money request [this action sends the requested amount of money from your account to the requesting user]. 3. "
                            + "Decline Money request. [This action ignores and dismisses money request from other user]. <p/>"
                            + "<h2 class=\"hhdd\">Payrolls</h2>Payroll On the Menu is where you can pay many registered users who are classified "
                            + "in the same payroll. This is done in 3 steps; 1. Create new Payroll with your prefered name e.g. Payroll001. 2. "
                            + "On the list on created Payrolls click enter to view the payroll. Here you can view and delete user from this list. And "
                            + "Below the list is a from which you need to fill to add new user to this payroll. 3. After Entering a payroll, Click "
                            + "<b>Pay This Payroll</b> to pay all users in this list using the money on your account if Balance is sufficient.<p/>"
                            + "<h2 class=\"hhdd\">Messaging</h2>Here is where things get Interesting. Messages on the Menu is where you can have a "
                            + "private discussion with another registered user on this platform. Click <b>Start New Conversation</b> to create a new "
                            + "conversation stream with another user. Select available Streams to continue conversations in that stream. <br/><b>NOTE: "
                            + "These discussions are only posibble between 2 users. We are working to make it unlimited on the number of users per "
                            + "Stream.</b><p/><h2 class=\"hhdd\">Developer/Merchant API</h2>Developer on the Menu is where a developer can receive payments "
                            + " to his/her SD Finance account from his/her own website. It is simple, yes you can sell and get paid here. Follow the "
                            + "Steps Below to get Started.<ul type=\"disc\">"
                            + "<li>Create New App by your Preferred name and company Name. It will be created in Demo MODE. What is Demo MODE? Here you "
                            + "test your app without causing real Money transactions when the app is in demo MODE. Switch to active only when you have "
                            + "tested your app and sure all is set.</li><li>Write HTML Form to submit a payment request to SD Finace Server. Server will "
                            + "accept Five(5) parameters to process payment; 1. App ID [It is generated when you create App]. 2. Item [Product Name e.g. iPhone]. "
                            + "3. Amount [Amount to be paid]. 4. Return URL [URL to redirect when user cancels payment]. 5. Success URL [URL to redirect when "
                            + "transaction (payment) is successful]. You Should set Form Action to <i>\"http://env-8279297.lon.wafaicloud.com/SD-Finance/checkout\"</i> "
                            + "where your transaction is processed.<p/>View The Sample Code Below<hr/>"
                            + "<img src=\"images/sample.PNG\" class=\"btn\" height=\"191\" width=\"691\" alt=\"CODE\" /><hr/>"
                            + "Your payment is processed when form is submitted. Contact Us if you need further assistance with this process.<p/>"
                            + "<h2 class=\"hhdd\">Notifications</h2>Basically review Notifications here...<p/>"
                            + "<h2 class=\"hhdd\">Change Password</h2>Here is where you change your passsword. You'll be asked to enter active password [Current], "
                            + "New Password and to confirm new password to Change.<p/>"
                            + "<h2 class=\"hhdd\">Contact Us</h2>Send Us a direct message Here. Action will be taken Immediately.<p/>"
                            + "<hr/><h2>Thank you for reading<p/><hr/>";
                    
                    //we do the output stream
                    String content=u.head1+hom+u.head2+btn+u.head3+"SIMPLE GUIDE DOC"+u.comph+body+u.foot1+hom+u.foot2;
                    
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
        return "Doc";
    }

}
