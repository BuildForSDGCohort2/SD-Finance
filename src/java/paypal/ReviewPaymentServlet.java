
package paypal;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import com.paypal.api.payments.*;
import static com.paypal.base.Constants.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.*;

/**
 *
 * @author Seumx Plus
 */

@WebServlet(name = "ReviewPaymentServlet", urlPatterns = {"/ReviewPaymentServlet"})
public class ReviewPaymentServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        Enumeration<String> s=request.getParameterNames();
        while(s.hasMoreElements()) {
            System.out.println(request.getParameter(s.nextElement()));
        }
        */
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
         
        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.getPaymentDetails(paymentId);
             
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
             
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("shippingAddress", shippingAddress);
             
            String url = "Review?paymentId=" + paymentId + "&PayerID=" + payerId;
             
            request.getRequestDispatcher(url).forward(request, response);
             
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            System.out.println(ex.getMessage());
            request.getRequestDispatcher("PaypalError").forward(request, response);
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
        return "ReviewPaymentServlet";
    }

}
