package api.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Server4Mail {
	public Server4Mail(String email,String subject,String txt_msg, String requested_date) {


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
    	  Date date = new Date();  
    	  SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy"); 
    	  String strdate= formatter.format(date); 
    	  formatter = new SimpleDateFormat("HH:mm:ss");  
    	   String strtime = formatter.format(date);
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(from));//change accordingly  
          InternetAddress[] addresses = InternetAddress.parse(email);
          message.addRecipients(Message.RecipientType.TO, addresses);
          
          message.setSubject(subject);
          message.setContent("<!DOCTYPE html>\r\n" + 
          		"<html>\r\n" + 
          		"<head>\r\n" + 
          		"<style>\r\n" + 
          		"table, td, th {\r\n" + 
          		"  border: 1px solid black;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"table {\r\n" + 
          		"  border-collapse: collapse;\r\n" + 
          		"  width: 100%;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"th {\r\n" + 
          		"  text-align: left;\r\n" + 
          		"}\r\n" + 
          		"</style>\r\n" + 
          		"</head>\r\n" + 
          		"<body>\r\n" + 
          		"\r\n" + 
          		"\r\n" + 
          		"\r\n" + 
          		"<table>\r\n" + 
          		"   <tr align='center'>\r\n" + 
          		"  <td align='center' style='background-color: #8080ff;'>Date: "+strdate+"</td>\r\n" + 
          		"  <td align='center' style='background-color: #8080ff;'>time: "+strtime+"</td>\r\n" + 
          		"  <td align='center' colspan='4'></td>\r\n" + 
          		"  </tr>"
          		+ "<tr align='center' style='background-color: #f0fc03;'>\r\n" + 
          		"  <td align='center' ><b>Accounts</b></td>\r\n" + 
          		"  <td align='center' ><b>Submitted</b></td>\r\n" + 
          		"  <td align='center'><b>Delivered</b></td>\r\n" + 
          		"  <td align='center' ><b>%age</b></td>\r\n" + 
          		"  <td align='center' ><b>Pending</b></td>\r\n" + 
          		"  <td align='center' ><b>Pending %age</b></td>\r\n" + 

          		"  </tr>\r\n"
          		+ ""+txt_msg+"" +  
          		"  \r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"</body>\r\n" + 
          		"</html>\r\n" + 
          		"","text/html" );  
          //send message  
          Transport.send(message);
          System.out.println("message sent to ----" + to);

      } catch (MessagingException e) {
          throw new RuntimeException(e);

      }
	
	}
}
