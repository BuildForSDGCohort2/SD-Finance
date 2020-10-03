
package sdf;

/**
 * @author Seumx Plus
 */

public class notifyme {
    
    public void doNofify(String act,String tym,String user,String plat) throws Exception {
        
        factory u=new factory();
        
        String msg="Hello Silas,<p/>This is to notify you of a new activity that just took place. Kindly review the action as follows;<hr/>"
                + "Activity : "+act+"<p/>Time : "+tym+"<p/>User : "+user+"<p/>Platform : "+plat+"<hr/>User may need your support."
                + "Please Assist <p/>Regards<p/>Seumx Plus";
        
        String mail=u.mail1+"Verification Code"+u.mail2+msg+u.mail3;
        
        //prepare
        Sender sd=new Sender("smtp.gmail.com");
        sd.setFro(""); sd.setTo("silas.seal7@gmail.com");
        sd.setUser(""); sd.setPass("");
        sd.setSubject("SD Finance - Action Notify"); sd.setContent(mail); sd.arrange();
        
        //send
        sd.send();
        
    }
    
}
