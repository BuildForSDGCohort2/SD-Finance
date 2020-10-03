
package sdf;

/**
 * @author Seumx Plus
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Sender { 
    
    private String to,fro,user,pass,content,subject; private final String host;
    Session ssn; Properties pr; MimeMessage msg;
    
    public Sender(String host) {
        this.host=host;
    }
    
    public void setTo(String to) {
        this.to=to;
    }
    
    public void setFro(String fro) {
        this.fro=fro;
    }
    
    public void setUser(String user) {
        this.user=user;
    }
    
    public void setPass(String pass) {
        this.pass=pass;
    }
    
    public void setContent(String cont) {
        this.content=cont;
    }
    
    public void setSubject(String subject) {
        this.subject=subject;
    }
    
    public void arrange() throws Exception {
        
        this.pr=new Properties();
        
        //property data
        this.pr.put(to, pass);
        this.pr.put("mail.smtp.starttls.enable",true);
        this.pr.put("mail.smtp.host",host);
        this.pr.put("mail.smtp.user",user);
        this.pr.put("mail.smtp.password",pass);
        this.pr.put("mail.smtp.port","587");
        this.pr.put("mail.smtp.auth","true");
        
        //sensioning
            this.ssn=Session.getDefaultInstance(this.pr, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(user,pass);
                }
            });
            
        //creating msg--send
            this.msg=new MimeMessage(ssn); this.msg.setFrom(new InternetAddress(fro));
            this.msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            this.msg.setSubject(subject);
        
        //set Content
            this.msg.setContent(content,"text/html");
    }
    
    public void send() throws Exception {
        Transport.send(this.msg);
        System.out.println("Mail successfully sent.");
    }
    
}
