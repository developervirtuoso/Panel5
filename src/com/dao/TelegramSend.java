package com.dao;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
public class TelegramSend {
	public static void main(String args[]) {
		try {
			HttpResponse<JsonNode> response2=Unirest.post("https://api.telegram.org/bot1205919730:AAEIbpoIamcRyznCmvrnNzKiSRXw9RlrxeM/sendMessage")
					
					.header("Content-Type", "application/json")
					.queryString("chat_id", "1092446133")
					.queryString("text", "Hello Mam ki hal hai")
					.asJson();
			
			System.out.println("response is ===="+response2.getBody().toString());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
