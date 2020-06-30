package api.daoImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;


public class EmailSent {

	public static void main(String[] args) {
		JSONObject jsonObject=new JSONObject();
		EmailSent sendEmail=new EmailSent("neerajbhagat9872@gmail.com", "Your Traffic", "Traffic",jsonObject);
	}
	public  EmailSent(String email,String subject,String txt_msg, JSONObject jsondata)
	{

		 String tos= "neerajbhagat9872@gmail.com,neeraj@virtuosonetsoft.in";
		
		String host ="smtp.gmail.com" ; 
		String user = "info@parrotinfosoft.com";
		String pass = "info@123"; 
		String to = email; 
		String from = "info@parrotinfosoft.com";
		
		Properties props = new Properties();
        //props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, pass);//change accordingly  
            }
        });

        //compose message  
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));//change accordingly  
            InternetAddress[] addresses = InternetAddress.parse(email);
            message.addRecipients(Message.RecipientType.TO, addresses);
            
            message.setSubject(subject);
            message.setText(txt_msg);
            message.setContent("<!DOCTYPE html>\r\n" + 
            		"<html>\r\n" + 
            		"<head>\r\n" + 
            		"<meta charset=\"ISO-8859-1\">\r\n" + 
            		"<title>Traffic Mail</title>\r\n" + 
            		"<style>\r\n" + 
            		".center {\r\n" + 
            		"  margin: auto;\r\n" + 
            		"  width: 60%;\r\n" + 
            		"  border: 3px solid #73AD21;\r\n" + 
            		"  padding: 10px;\r\n" + 
            		"}\r\n" + 
            		"</style>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"<div class=\"center\">\r\n" + 
            		"  <p><b>Name: </b>"+jsondata.getString("name")+".</p>\r\n" + 
            		"  <p><b>user: </b>"+jsondata.getString("user")+".</p>\r\n" + 
            		"  <p><b>tps: </b>"+jsondata.getString("tps")+".</p>\r\n" + 
            		"  <p><b>type: </b>"+jsondata.getString("type")+".</p>\r\n" + 
            		"  <p><b>kannelQ: </b>"+jsondata.getInt("kannelQ")+".</p>\r\n" + 
            		"</div>\r\n" + 
            		"</body>\r\n" + 
            		"</html>","text/html" );  
            //send message  
            Transport.send(message);
            System.out.println("message sent to ----" + to);

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  EmailSent(String email,String subject,String txt_msg)
	{

		 String tos= "neerajbhagat9872@gmail.com,neeraj@virtuosonetsoft.in";
		
		String host ="smtp.gmail.com" ; 
		String user = "info@parrotinfosoft.com";
		String pass = "info@123"; 
		String to = email; 
		String from = "info@parrotinfosoft.com";
		
		Properties props = new Properties();
        //props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, pass);//change accordingly  
            }
        });

        //compose message  
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));//change accordingly  
            InternetAddress[] addresses = InternetAddress.parse(email);
            message.addRecipients(Message.RecipientType.TO, addresses);
            
            message.setSubject(subject);
            message.setText(txt_msg);
            message.setContent("<!DOCTYPE html>\r\n" + 
            		"<html>\r\n" + 
            		"<head>\r\n" + 
            		"<meta charset=\"ISO-8859-1\">\r\n" + 
            		"<title>Traffic Mail</title>\r\n" + 
            		"<style>\r\n" + 
            		".center {\r\n" + 
            		"  margin: auto;\r\n" + 
            		"  width: 60%;\r\n" + 
            		"  border: 3px solid #73AD21;\r\n" + 
            		"  padding: 10px;\r\n" + 
            		"}\r\n" + 
            		"</style>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"<div class=\"center\">\r\n" + 
            		"  <p><b>"+txt_msg+".</b></p>\r\n" + 
            		"</div>\r\n" + 
            		"</body>\r\n" + 
            		"</html>","text/html" );  
            //send message  
            Transport.send(message);
            System.out.println("message sent to ----" + to);

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
	}
	
}
