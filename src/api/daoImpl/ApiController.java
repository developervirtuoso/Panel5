package api.daoImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beans.ReportSmsSummary;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class ApiController {
	public static void main(String[] args) throws JSONException {

		String jsonData="";
		try {
			HttpResponse<JsonNode> response = Unirest.get("http://172.105.57.57/SMSApi/report/smsSummary?userid=vfirstTr1&password=4GR658Fg&fromdate=2020-07-25&todate=2020-07-25&groupby=summary&output=json")
					.asJson();
			jsonData=response.getBody().toString();
			JSONObject jsonObject=new JSONObject(jsonData);
			JSONObject data=jsonObject.getJSONObject("response");
			if(data.has("report_smsSummaryList")) {
				JSONArray report_smsSummaryList=data.getJSONArray("report_smsSummaryList");
				JSONObject report_smsSummaryobj=report_smsSummaryList.getJSONObject(0);
			
				if(report_smsSummaryobj.has("report_smsSummary")) {
					Gson gson=new Gson();
					JSONObject report_smsSummary=report_smsSummaryobj.getJSONObject("report_smsSummary");
					System.out.println(report_smsSummary.toString());
					ReportSmsSummary smsSummary=gson.fromJson(report_smsSummary.toString(), ReportSmsSummary.class);
					System.out.println(smsSummary.getSuccess());
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonData);

		
	}
	
	public String getServer4DataToApi(String accountname,String pwd,String fromdate,String todate) {
		String jsonData="";
		try {
			if(accountname.equalsIgnoreCase("vfirstTr12") || accountname.equalsIgnoreCase("vfirstTr11")) {
				HttpResponse<JsonNode> response = Unirest.get("http://172.105.50.198:5612/SMSApi/report/smsSummary?userid="+accountname+"&password="+pwd+"&fromdate="+fromdate+"&todate="+todate+"&groupby=summary&output=json")
						.asJson();
				jsonData=response.getBody().toString();
			}else {
				HttpResponse<JsonNode> response = Unirest.get("http://172.105.57.57/SMSApi/report/smsSummary?userid="+accountname+"&password="+pwd+"&fromdate="+fromdate+"&todate="+todate+"&groupby=summary&output=json")
						.asJson();
				jsonData=response.getBody().toString();
			}
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		//System.out.println(jsonData);
		return jsonData;
	}
}
