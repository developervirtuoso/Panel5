package api.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.JavaPostRequest;

public class PushSms {

	public static void main(String[] args) {
		try {
			
		
		Smpp_DaoImpl smpp_dao = new Smpp_DaoImpl();
		JSONObject jsonObject=new JSONObject();
		smpp_dao.getAdminCookie(jsonObject);
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
		Date d1 = sdfo.parse(java.time.LocalDate.now().toString()); 
		Date d2 = sdfo.parse(jsonObject.getString("datetime")); 
		String cookiename=jsonObject.getString("name");
		 JavaPostRequest javaPostRequest=new JavaPostRequest();
		    String data=javaPostRequest.pushSmppConnectApiData(cookiename);
		   if(!data.equalsIgnoreCase("")){
		    JSONObject pushobj=new JSONObject(data);
		    if(pushobj.has("data")){
		    JSONArray jsonArray=pushobj.getJSONArray("data");
		    for(int i=0; i<jsonArray.length();i++ ){
		    	JSONObject jsondata=jsonArray.getJSONObject(i);
		    	if(jsondata.getString("name").equalsIgnoreCase("vmobiTr2") 
		    			|| jsondata.getString("name").equalsIgnoreCase("dlt_vnsoft_tr1") 
		    			|| jsondata.getString("name").equalsIgnoreCase("PARROTNZ_T3")
		    			|| jsondata.getString("name").equalsIgnoreCase("PARROTNZ_T2")
		    			|| jsondata.getString("name").equalsIgnoreCase("dlt_parrot_trn")
		    			|| jsondata.getString("name").equalsIgnoreCase("PARROT_SZ_T")){
		    		int kannelQ=jsondata.getInt("kannelQ");
		    		if(kannelQ>=10000) {
		    			smpp_dao.sendEmail(jsondata);
		    		}
		    	}
		    }
		    }
		    }else {
		    	smpp_dao.sendEmailCookieUpdate();
		    	smpp_dao.sendSmsCookieUpdate();
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
