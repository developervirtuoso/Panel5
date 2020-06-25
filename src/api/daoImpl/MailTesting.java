package api.daoImpl;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailTesting {
	public static void main(String[] args) {
		 System.out.println(" --------- inside send  mail ----");
	       // String to = "";//change accordingly  

	        //Get the session object  
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
	        //props.put("mail.smtp.host", "smtp.gmail.com");  
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.transport.protocol", "smtp"); 
	props.put("mail.smtp.starttls.enable", "true"); 
	props.put("mail.smtp.localhost", "127.0.0.1");
		System.out.println(" ---------before session send  mail ----");
	        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
	                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                        return new javax.mail.PasswordAuthentication("info@parrotinfosoft.com","info@123");//change accordingly  
	                    }
	                });

	        //compose message  
	        try {
	 	System.out.println(" --------- composing message ----");

	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("altmish.danish@yahoo.com"));//change accordingly  
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress("neeraj@virtuosonetsoft.in"));
	            message.setSubject("Testing ");
	            message.setText("hello neeraj");

	            //send message  
	            Transport.send(message);
	            System.out.println("message sent to ----neeraj@virtuosonetsoft.in" );
	            System.out.println("message sent from ---- ");
	            System.out.println("message sent successfully");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);

	        }

	}
}
