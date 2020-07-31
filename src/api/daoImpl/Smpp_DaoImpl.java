package api.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import com.beans.AccountDetails;
import com.beans.ApiEmail;
import com.beans.ConnFile;
import com.beans.ReportSmsSummary;
import com.beans.Server4;
import com.google.gson.Gson;

import common.database.DbConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Smpp_DaoImpl {
	public static void main(String[] args) {
		System.out.println("rnnnnnnnnnnnnnn");
	}
	public int insertCookie(String cookie) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into cookie (name) values(?)");
			 pst.setString(1,cookie);
			 
	    	 i=pst.executeUpdate();
	    	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return i;
	
	}
	public int insertAdminCookie(String cookie) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into admin_cookie (name) values(?)");
			 pst.setString(1,cookie);
			 
	    	 i=pst.executeUpdate();
	    	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return i;
	
	}
	public ArrayList<ApiEmail> getApiEmail() {
		ArrayList<ApiEmail> apiEmails=new ArrayList<ApiEmail>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;

        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT * FROM apiemail ORDER BY id;");
      	 while(rs.next())
      	 {
      		ApiEmail apiEmail=new ApiEmail();
      		apiEmail.setId(rs.getLong("id"));
      		apiEmail.setEmail(rs.getString("email"));
      		apiEmail.setAccess(rs.getLong("access"));
      		apiEmail.setDatetime(rs.getString("datetime"));
      		apiEmails.add(apiEmail);
      	 }
        }
       catch(Exception e)
        {
     	  e.printStackTrace();
        }finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return apiEmails;
       
}
	public void getCookie(JSONObject jsonObject) {
		
	        Connection conn=DbConnection.getInstance().getConnection();
	         Statement st=null;
	        ResultSet rs=null;

	        try
	        {
	     	  st=conn.createStatement();
	     	  
	      	 rs = st.executeQuery("SELECT * FROM cookie ORDER BY id DESC LIMIT 1;");
	      	System.out.println("SELECT * FROM cookie ORDER BY id DESC LIMIT 1;");
	      	 while(rs.next())
	      	 {
	      		jsonObject.put("id", rs.getInt("id"));
	      		jsonObject.put("name", rs.getString("name"));
	      		jsonObject.put("datetime", rs.getString("datetime"));
	      	 }
	        }
	       catch(Exception e)
	        {
	     	  e.printStackTrace();
	        }finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					if(st!=null) {
						st.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					if(rs!=null) {
						rs.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
	       
	}
	public void getAdminCookie(JSONObject jsonObject) {
		
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        
        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT * FROM admin_cookie ORDER BY id DESC LIMIT 1;");
      	System.out.println("SELECT * FROM admin_cookie ORDER BY id DESC LIMIT 1;");
      	 while(rs.next())
      	 {
      		jsonObject.put("id", rs.getInt("id"));
      		jsonObject.put("name", rs.getString("name"));
      		jsonObject.put("datetime", rs.getString("datetime"));
      	 }
        }
       catch(Exception e)
        {
     	  e.printStackTrace();
        }finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
       
}
	public void sendEmail(JSONObject jsondata) {
		Thread thread=new Thread() {
			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
			String Email=daoImpl.getAccessEmail();
			EmailSent emailSent=new EmailSent(Email, "Your Traffic", "Traffic",jsondata);
		};
		thread.start();
	}
	public String getAccessEmail() {
		ArrayList<ApiEmail> apiEmails=new ArrayList<ApiEmail>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        int count=1;
        String email="";
        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT * FROM apiemail where access=1 ORDER BY id;");
      	 while(rs.next())
      	 {
      		 if(count==1) {
      			 email=rs.getString("email");
      		 }else {
      			 email=email+","+rs.getString("email");
      		 }
      		 count++;
      		}
        }
       catch(Exception e)
        {
     	  e.printStackTrace();
        }finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
       // System.out.println("emailemailemailemail=="+email);
		return email;
       
}
	public int addApiEmail(ApiEmail apiEmail) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into apiemail (email) values(?)");
			 pst.setString(1,apiEmail.getEmail());
	    	 i=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return i;
	
	}
	public int updateApiEmail(ApiEmail apiEmail) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("update apiemail set email =?,access=? where id=? ");
			 pst.setString(1,apiEmail.getEmail());
			 pst.setLong(2, apiEmail.getAccess());
			 pst.setLong(3, apiEmail.getId());
	    	 i=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	
	}
	public int accessApiEmail(ApiEmail apiEmail) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("update apiemail set access=? where id=? ");
			 pst.setLong(1, apiEmail.getAccess());
			 pst.setLong(2, apiEmail.getId());
	    	 i=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	
	}
	public int deleteApiEmail(ApiEmail apiEmail) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("DELETE FROM apiemail where id=? ");
			 pst.setLong(1, apiEmail.getId());
	    	 i=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	
	}
	
	public  String encrypt(String strToEncrypt) 
    {
        try
        {	 MessageDigest sha = null;
        	// byte[]  key = ConnFile.mykey.getBytes("UTF-8");
        	 byte[] key = Base64.getDecoder().decode(ConnFile.mykey);
             sha = MessageDigest.getInstance("SHA-1");
             key = sha.digest(key);
             key = Arrays.copyOf(key, 16); 
             SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
	 public static String decrypt(String strToDecrypt) 
	    {
	        try
	        {
	        	MessageDigest sha = null;
	        	// byte[]  key = ConnFile.mykey.getBytes("UTF-8");
	        	 byte[] key = Base64.getDecoder().decode(ConnFile.mykey);
	             sha = MessageDigest.getInstance("SHA-1");
	             key = sha.digest(key);
	             key = Arrays.copyOf(key, 16); 
	             SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	        } 
	        catch (Exception e) 
	        {
	            System.out.println("Error while decrypting: " + e.toString());
	        }
	        return null;
	    }
	 public void readJSonArr(JSONArray jsonArray,String filname) {
		  
			try  
			{  
			
			File file = new File(filname);   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
				JSONObject jsonObject=new JSONObject();
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
			int i=0;
			while (cellIterator.hasNext())   
			{  
			Cell cell = cellIterator.next();
			if(cell.getRowIndex()==0){
				jsonObject.put("id", "id");
				jsonObject.put("name", "name");
				jsonObject.put("count", "count");
			}else {
				if(i==0) {
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("id", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("id", cell.getNumericCellValue()+"");
					if(cell.getNumericCellValue()==10) {
						jsonObject.put("name", "vnsoftvf_tr3(Direct EB)");
					}else if(cell.getNumericCellValue()==11) {
						jsonObject.put("name", "PARROTNZ_T1(DLT)");
					}else if(cell.getNumericCellValue()==12) {
						jsonObject.put("name", "vnsoft_tr1(DLT)");
					}else if(cell.getNumericCellValue()==13) {
						jsonObject.put("name", "vnsoft_tr(Direct EB)");
					}else if(cell.getNumericCellValue()==16) {
						jsonObject.put("name", "PARROTNZ_T2(DLT)");
					}else if(cell.getNumericCellValue()==17) {
						jsonObject.put("name", "PARROTNZ_T3(DLT)");
					}else if(cell.getNumericCellValue()==18) {
						jsonObject.put("name", "vnsoftvf_tr4");
					}else {
						jsonObject.put("name", "");
					}
					break;  
					}
					default:  
					}
				}else if(i==1){
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("count", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("count", cell.getNumericCellValue()+"");
					break;  
					}
					default:  
					}
				}
				 i++; 
			}
			
			
			}  
			
			jsonArray.put(jsonObject);
			System.out.println("");  
			} 
			System.out.println(jsonArray);
			}  
			catch(Exception e)  
			{  
			e.printStackTrace();  
			}  
			
	 }
	 public void readJSonArr_5Col(JSONArray jsonArray,String filname) {
		  
			try  
			{  
			
			File file = new File(filname);   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
				JSONObject jsonObject=new JSONObject();
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
			int i=0;
			while (cellIterator.hasNext())   
			{  
			Cell cell = cellIterator.next();
			if(cell.getRowIndex()==0){
				jsonObject.put("id", "Vendor ID");
				jsonObject.put("name", "name");
				jsonObject.put("total", "total");
				jsonObject.put("success", "success");
				jsonObject.put("pending", "pending");
				
			}else {
				if(i==0) {
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("id", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("id", cell.getNumericCellValue()+"");
					if(cell.getNumericCellValue()==10) {
						jsonObject.put("name", "vnsoftvf_tr3(Direct EB)");
					}else if(cell.getNumericCellValue()==11) {
						jsonObject.put("name", "PARROTNZ_T1(DLT)");
					}else if(cell.getNumericCellValue()==12) {
						jsonObject.put("name", "vnsoft_tr1(DLT)");
					}else if(cell.getNumericCellValue()==13) {
						jsonObject.put("name", "vnsoft_tr(Direct EB)");
					}else if(cell.getNumericCellValue()==16) {
						jsonObject.put("name", "PARROTNZ_T2(DLT)");
					}else if(cell.getNumericCellValue()==17) {
						jsonObject.put("name", "PARROTNZ_T3(DLT)");
					}else if(cell.getNumericCellValue()==18) {
						jsonObject.put("name", "vnsoftvf_tr4");
					}else {
						jsonObject.put("name", "");
					}
					break;  
					}
					default:  
					}
				}else if(i==1){
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("total", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("total", cell.getNumericCellValue()+"");
					break;  
					}
					default:  
					}
				}else if(i==2){
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("success", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("success", cell.getNumericCellValue()+"");
					break;  
					}
					default:  
					}
				}else if(i==3){
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					jsonObject.put("pending", cell.getStringCellValue()+"");
					break;  
					}
					case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					jsonObject.put("pending", cell.getNumericCellValue()+"");
					break;  
					}
					default:  
					}
				}
				 i++; 
			}
			
			
			}  
			
			jsonArray.put(jsonObject);
			System.out.println("");  
			} 
			System.out.println(jsonArray);
			}  
			catch(Exception e)  
			{  
			e.printStackTrace();  
			}  
			
	 }
	public void sendEmailCookieUpdate() {
		Thread thread=new Thread() {
			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
			String Email=daoImpl.getAccessEmail();
			EmailSent emailSent=new EmailSent(Email, "Update Cookie", "Please Update Cookie. Your url is : http://49.50.86.152:6080/Panel5/pushSmppConnects");
		};
		thread.start();
		
	}
	public void sendSmsCookieUpdate() {
		Thread thread=new Thread() {
			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
			String Email=daoImpl.getAccessEmail();
			SendSms sendSms=new SendSms();
			
			EmailSent emailSent=new EmailSent(Email, "Update Cookie", "Please Update Cookie. Your url is : http://49.50.86.152:6080/Panel5/pushSmppConnects");
		};
		thread.start();
		
	}
	public void sendServer4Mail(List<Server4> list, String txt_msg, String requested_date) {
	
		Thread thread=new Thread() {
			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
	         
			String Email=daoImpl.getAccessEmail();
			SendSms sendSms=new SendSms();
			
			Server4Mail emailSent=new Server4Mail(Email,"Server 4 Data",txt_msg,requested_date);
			
		};
		thread.start();
		
	}
	public void insertServer4Data(List<Server4> list) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into server4 (Account,AccountId,SUB,DEL,percentage,Pending) values(?,?,?,?,?,?)");
			
			for(Server4 server4:list) {
				pst.setString(1,server4.getAccount());
				pst.setLong(2, server4.getId());
				pst.setLong(3, server4.getSUB());
				pst.setLong(4, server4.getDEL());
				pst.setLong(5, server4.getPercentage());
				pst.setLong(6, server4.getPending());
				 i=pst.executeUpdate();
			}
			
			 
	    	
	    	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if(Conn!=null) {
					Conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	
	}
	public long lastSubCount() {

		ArrayList<ApiEmail> apiEmails=new ArrayList<ApiEmail>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        long count=0;
        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT id,(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr1' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr1,\r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr2' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr2, \r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstPR1' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstPR1, \r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr4' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr4, \r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr3' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr3\r\n" + 
      	 		" FROM server4 LIMIT 1;");
      	 while(rs.next())
      	 {
      		count=rs.getLong("vfirstTr1")+rs.getLong("vfirstTr2")+rs.getLong("vfirstTr3")+rs.getLong("vfirstPR1")+rs.getLong("vfirstTr4");
      	 }
        }
       catch(Exception e)
        {
     	  e.printStackTrace();
        }finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
       

	}
	public List<Server4> getServer4DataWithApi(String fromdate,String todate){
		List<Server4> list=new ArrayList<Server4>();
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		ArrayList<AccountDetails> accountDetails =daoImpl.getAccountDetails();
		ApiController apiController=new ApiController();
		long total_sub=0;
		long total_del=0;
		long total_pending=0;
		for(AccountDetails details:accountDetails) {
			if(details.getAccountname().equalsIgnoreCase("vfirstTr1") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr2") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr3") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr4") ||
					details.getAccountname().equalsIgnoreCase("vfirstPR1") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr12") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr11")) {
			String data=apiController.getServer4DataToApi(details.getAccountname(), details.getPwd(), fromdate, todate);
			try {
				JSONObject jsonObject=new JSONObject(data);
				JSONObject response=jsonObject.getJSONObject("response");
				if(response.has("report_smsSummaryList")) {
					JSONArray report_smsSummaryList=response.getJSONArray("report_smsSummaryList");
					JSONObject report_smsSummaryobj=report_smsSummaryList.getJSONObject(0);
					if(report_smsSummaryobj.has("report_smsSummary")) {
						Server4 server4=new Server4();
						Gson gson=new Gson();
						JSONObject report_smsSummary=report_smsSummaryobj.getJSONObject("report_smsSummary");
						ReportSmsSummary smsSummary=gson.fromJson(report_smsSummary.toString(), ReportSmsSummary.class);
						String t="0";
						if(smsSummary.getTotal()!=null) {
							t=smsSummary.getTotal();
						}
						String s="0";
						if(smsSummary.getSuccess()!=null) {
							s=smsSummary.getSuccess();
						}
                        long sub=Long.parseLong(t);
                        long del=Long.parseLong(s);
                        
                        long pending=smsSummary.getPending();
                        
                         total_sub = total_sub + (int) sub;
                         total_del = total_del + (int) del;
                         total_pending = total_pending + pending;
                       //  System.out.println("del=="+del);
                        // System.out.println("sub=="+sub);
                        // System.out.println("per=="+(del *100) * sub);
                         long per =0;
                         if(sub!=0) {
                        	 per =(del / (sub / 100));
                         }
                        
                        System.out.println(per);
                        server4.setSUB(sub);
                        server4.setDEL(del);
                        server4.setPending(pending);
                        server4.setPercentage(per);
                        server4.setAccount(details.getAccountname());
                        server4.setId((long) details.getAccountid());
                        list.add(server4);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	/*	Server4 server4=new Server4();
		server4.setSUB(total_sub);
        server4.setDEL(total_del);
        server4.setPending(total_pending);
        System.out.println(total_del);
        System.out.println(total_sub);
        
        long per = (total_del / (total_sub / 100));
        server4.setPercentage(per);
        server4.setAccount("total");
        list.add(server4);*/
		return list;
	}
	public ArrayList<AccountDetails> getAccountDetails() {
		ArrayList<AccountDetails> accountDetails=new ArrayList<AccountDetails>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("select * from accountDetails_s5 where pwd is not null");
      	 while(rs.next())
      	 {
      		AccountDetails details=new AccountDetails();
      		details.setId(rs.getInt("id"));
      		details.setAccountid(rs.getInt("accountid"));
      		details.setAccountname(rs.getString("accountname")); 
      		details.setCompanyname(rs.getString("companyname"));
      		details.setAccounttype(rs.getString("accounttype"));
      		details.setPM(rs.getString("PM"));
      		details.setServer(rs.getString("server"));
      		details.setPwd(rs.getString("pwd"));
      		accountDetails.add(details);
      	 }
        }
       catch(Exception e)
        {
     	  e.printStackTrace();
        }finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return accountDetails;
       
	}
}
