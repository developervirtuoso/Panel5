package api.daoImpl;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SendSms {
	public static void main(String[] args) {
		SendSms sendSms=new SendSms();
		boolean check=sendSms.sendSmsToSMS("7355745909", "heelllo");
	}
	public  boolean sendSmsToSMS(String phoneNumber,String message ) {
		
		 String remessage=message.replaceAll(" ", "%20");
		   boolean sessionDebug = false;
		 com.mashape.unirest.http.HttpResponse<JsonNode> response1;
		 try {
			 response1 = Unirest.get("http://103.13.97.241:6005/api/v2/SendSMS?SenderId=PARROT&ApiKey=%2BYblK5SizoprQiGsRHKXENLXvj9kVq%2FwQ%2Bgu1Q%2BSEbA%3D&ClientId=15ee69aa-c91e-4280-a734-64795246300b&Message="+remessage+"&MobileNumbers=91"+phoneNumber+"").asJson();
		 String jsonData=response1.getBody().toString();
		 System.out.println(jsonData);
		 sessionDebug=true;
		 } catch (UnirestException e) {
		 e.printStackTrace();
		 sessionDebug=false;
		 }
		 return sessionDebug;
	 }
}
