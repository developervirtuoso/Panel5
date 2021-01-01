package api.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class VFMail {
	public VFMail(String email,String cc,String subject,String txt_msg, String requested_date) {


		 String tos= "neerajbhagat9872@gmail.com,neeraj@virtuosonetsoft.in";
		
		String host ="smtp.gmail.com" ; 
	/*	String user = "vns.techsupport@virtuosonetsoft.in";
		String pass = "VNStechsupport@123"; 
		String to = email; 
		String from = "vns.techsupport@virtuosonetsoft.in";*/
		
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
   	  Calendar cal = Calendar.getInstance();
	 cal.add(Calendar.DATE, -1);
   	  SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy"); 
   	  String strdate= formatter.format(cal.getTime()).toString(); 
   	
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));//change accordingly  
         InternetAddress[] addresses = InternetAddress.parse(email);
         InternetAddress[] cc_addresses=InternetAddress.parse(cc);
         message.addRecipients(Message.RecipientType.TO, addresses);
         message.addRecipients(Message.RecipientType.CC, cc_addresses);
         
         Multipart multipart = new MimeMultipart();
         
         message.setSubject(subject);
         
         
         message.setText("Dear Team,\r\n\" "
         		+ "Please find the daily traffic report for "+strdate+"");
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
         		"  </tr>"
         		+ "<tr align='center' style='background-color: #f0fc03;'>\r\n" + 
         		"  <td align='center' ><b>Accounts</b></td>\r\n" + 
         		"  <td align='center' ><b>Total Sent</b></td>\r\n" + 
         		"  <td align='center'><b>Delivered</b></td>\r\n" + 
         		"  <td align='center' ><b>Delivered %age</b></td>\r\n" + 
         		"  <td align='center' ><b>Failed</b></td>\r\n" + 
         		"  <td align='center' ><b>Failed %age</b></td>\r\n" +
         		"  <td align='center' ><b>Waiting</b></td>\r\n" + 
         		"  <td align='center' ><b>Waiting %age</b></td>\r\n" +
         		"  <td align='center' ><b>MTD</b></td>\r\n" +

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
         System.out.println("message cc sent to ----" + cc);

     } catch (MessagingException e) {
         throw new RuntimeException(e);

     }
	
	}
}
