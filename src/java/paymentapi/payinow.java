
package paymentapi;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.sql.*;

/**
 *
 * @author Seumx Plus
 */
@WebServlet(name = "payinow", urlPatterns = {"/payinow"})
public class payinow extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String content="<html><head><title>SD FINANCE</title><link rel=\"shortcut icon\" href=\"images/logo.ico\" /> "
                + "</head><body bgcolor=\"lightslategrey\">"
                + "<center><table border=\"8\" bordercolor=\"blueviolet\" bgcolor=\"whitesmoke\" width=\"100%\" cellpadding=\"8\" "
                + "cellspacing=\"8\">"
                + "<tr><th height=\"51\" bgcolor=\"navy\"></th></tr>"
                + "<tr><th bgcolor=\"darkslateblue\"><hr/><img src=\"images/logo.png\" width=\"113\" height=\"113\" /><hr/>"
                + "<font face=\"Century Gothic\" size=\"+3\" color=\"white\">SD Finance : Payments</font></th></tr>"
                + "<tr><th height=\"317\" class=\"trn\">";
        
        try {
        sdf.factory u=new sdf.factory();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(u.host,u.user,u.password);
        PreparedStatement pst3=con.prepareStatement("select *from profile where email=?");
        pst3.setString(1,request.getParameter("email")); ResultSet rs3=pst3.executeQuery();
        
        if(rs3.next()) {
            
            boolean IsActive=false; String mailp=null,co=null;
            
            java.util.Date ui=new java.util.Date(); String dt=ui.toString().replaceAll("MST", " | ");
            
            PreparedStatement pst4=con.prepareStatement("select *from marchant where ID=?");
            pst4.setString(1,request.getParameter("ID")); ResultSet rs4=pst4.executeQuery();
            
            if(rs4.next()) {
                if(rs4.getString("Status").equals("active")) {
                    IsActive=true;
                }
                mailp=rs4.getString("Email"); co=rs4.getString("Company");
            }
            
            if(IsActive) {
                
                if(rs3.getString("pass").equals(request.getParameter("pass"))) {
                    
                    if(Float.valueOf(rs3.getString("main"))>=Float.valueOf(request.getParameter("Amount"))) {
                        
                        //First perform action On Payer
                        float bala=Float.valueOf(rs3.getString("main"))-Float.valueOf(request.getParameter("Amount"));
                        
                        PreparedStatement pst7=con.prepareStatement("update profile set main=? where email=?");
                        pst7.setString(1,String.valueOf(bala)); pst7.setString(2,rs3.getString("email"));
                        
                        pst7.executeUpdate();
                        
                        //Add transactions
                        PreparedStatement pst8=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                        pst8.setString(1,rs3.getString("email")); pst8.setString(2,"Send Payment : "+request.getParameter("Item"));
                        pst8.setString(3,co); pst8.setString(4,dt); pst8.setString(5,request.getParameter("Amount")); 
                        pst8.setString(6,String.valueOf(bala)); pst8.setString(7,"completed");
                        
                        pst8.executeUpdate();
                        
                        // perform action On Payee
                        Float blo=null;
                        //get balance
                        PreparedStatement pst10=con.prepareStatement("select *from profile where email=?");
                        pst10.setString(1,mailp); ResultSet rs10=pst10.executeQuery();
                        
                        if(rs10.next()) {
                            blo=Float.valueOf(rs10.getString("main"));
                        }
                        
                        float balb=blo+Float.valueOf(request.getParameter("Amount"));
                        
                        PreparedStatement pst9=con.prepareStatement("update profile set main=? where email=?");
                        pst9.setString(1,String.valueOf(balb)); pst9.setString(2,mailp);
                        
                        pst9.executeUpdate();
                        
                        //Add transactions
                        PreparedStatement pst11=con.prepareStatement("insert into transactions(email,trn_type,contact,date_time,amount,balance,status)"
                             + " values(?,?,?,?,?,?,?)");
                        pst11.setString(1,mailp); pst11.setString(2,"Receive Payment : "+request.getParameter("Item"));
                        pst11.setString(3,co); pst11.setString(4,dt); pst11.setString(5,request.getParameter("Amount")); 
                        pst11.setString(6,String.valueOf(balb)); pst11.setString(7,"completed");
                        
                        pst11.executeUpdate();
                        
                        response.sendRedirect(request.getParameter("successlink"));
                        
                    } else {
                        content=content+"<hr/><font face=\"Cambria\" size=\"+2\" color=\"darkred\">INSUFFICIENT BALANCE TO PROCEED!</font><hr/>"
                    + "<a href=\""+request.getParameter("returnlink")+"\">Ok Take Me Back</a><hr/>";
                    }
                    
                    
                } else {
                    content=content+"<hr/><font face=\"Cambria\" size=\"+2\" color=\"darkred\">INVALID PASSWORD PROVIDED</font><hr/>"
                    + "<a href=\""+request.getParameter("returnlink")+"\">Ok Take Me Back</a><hr/>";
                }
                
            } else {
                System.out.println(request.getParameter("successlink"));
                response.sendRedirect(request.getParameter("successlink"));
                
            }
            
        } else {
            content=content+"<hr/><font face=\"Cambria\" size=\"+2\" color=\"darkred\">USER NOT FOUND</font><hr/>"
                    + "<a href=\""+request.getParameter("returnlink")+"\">Ok Take Me Back</a><hr/>";
        }
        
        content=content+ "</th></tr></table><hr/></body>";
        
        try (PrintWriter out = response.getWriter()) {
            out.println(content);
        }
       
        
                } catch(SQLException | ClassNotFoundException ex){
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
        return "Time : UIO";
    }

}
