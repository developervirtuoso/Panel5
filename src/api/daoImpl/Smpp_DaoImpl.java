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
import com.beans.Campaign_data;
import com.beans.ConnFile;
import com.beans.ReportRecord;
import com.beans.ReportSmsSummary;
import com.beans.Server4;
import com.beans.Server4VfReport;
import com.beans.VFEmails;
import com.google.gson.Gson;
import com.sun.corba.se.spi.orbutil.fsm.State;

import EmailThread.MyRunnable_SendEmail;
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
	public String getLastCheckedTimeForCampaigns() {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String timee="";
		
		try {
			conn=DbConnection.getInstance().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select timee from campaign_data order by id desc limit 1");
			while(rs.next()) {
				timee=rs.getString("timee");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(st!=null) {
					st.close();
				}if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			
			return timee;
	}}
	public boolean checkEntryByDateAndAccount(String account,String datee) {
		boolean check=false;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			conn=DbConnection.getInstance().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select * from vfreportrecord where account='"+account+"' and report_date='"+datee+"'");
			while(rs.next()) {
				check=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(st!=null) {
					st.close();
				}if(rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
		}
		
		return check;
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
	public ArrayList<VFEmails> getVFEmails() {
		ArrayList<VFEmails> vfEmails=new ArrayList<VFEmails>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;

        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT * FROM vf_emails ORDER BY id;");
      	 while(rs.next())
      	 {
      		VFEmails vfEmail=new VFEmails();
      		vfEmail.setId(rs.getLong("id"));
      		vfEmail.setEmail(rs.getString("email"));
      		vfEmail.setAccess(rs.getLong("access"));
      		vfEmail.setDatetime(rs.getString("datetime"));
      		vfEmail.setType(rs.getString("type"));
      		vfEmails.add(vfEmail);
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
		return vfEmails;
       
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
	public String getVFAccessEmail(String type) {
		ArrayList<VFEmails> vfEmails=new ArrayList<VFEmails>();
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        int count=1;
        String email="";
        try
        {
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT * FROM vf_emails where access=1 and type='"+type+"' ORDER BY id;");
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
	public int addVFEmail(VFEmails vfEmails) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into vf_emails(email,type) values(?,?)");
			 pst.setString(1,vfEmails.getEmail());
			 pst.setString(2,vfEmails.getType());
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
	public int updateMTDinRecords(long mtd,int id) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("update vfirst_mtd set mtd ="+mtd+" where id="+id);
			
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
	public int updateMTDinMtdValuesTable(String account,long mtd,String testing) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			if(testing.equals("no")) {
				pst=Conn.prepareStatement("update mtd_values set mtd="+mtd+" where account ='"+account+"'");

			}else if(testing.equals("yes")) {
				pst=Conn.prepareStatement("update mtd_values_test set mtd="+mtd+" where account ='"+account+"'");

			}
			System.out.println(pst.toString());
			
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
	
	public int updateVFEmail(VFEmails vfEmails) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("update vf_emails set email =?,access=?,type=? where id=? ");
			 pst.setString(1,vfEmails.getEmail());
			 pst.setLong(2, vfEmails.getAccess());
			 pst.setString(3,vfEmails.getType());
			 pst.setLong(4, vfEmails.getId());
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
	public int accessVFEmail(VFEmails vfEmails) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("update vf_emails set access=? where id=? ");
			 pst.setLong(1, vfEmails.getAccess());
			 pst.setLong(2, vfEmails.getId());
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
	public int deleteVFEmail(VFEmails vfEmails) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("DELETE FROM vf_emails where id=? ");
			 pst.setLong(1, vfEmails.getId());
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
	public void sendVFMail(List<Server4VfReport> list, String subject,String txt_msg, String requested_date) {

			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
	         
			String to_Email=daoImpl.getVFAccessEmail("To");
			String Cc_Email=daoImpl.getVFAccessEmail("Cc");
		//	SendSms sendSms=new SendSms();
			MyRunnable_SendEmail myRunnable = new MyRunnable_SendEmail(to_Email, Cc_Email, subject, txt_msg, requested_date);
			Thread t = new Thread(myRunnable);
			t.start();			
		};
		public void sendVFReport(String subject,String txt_msg, String requested_date) {

			Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
	         
			String to_Email=daoImpl.getVFAccessEmail("To");
			String Cc_Email=daoImpl.getVFAccessEmail("Cc");
		//	SendSms sendSms=new SendSms();
			MyRunnable_SendEmail myRunnable = new MyRunnable_SendEmail(to_Email, Cc_Email, subject, txt_msg, requested_date);
			Thread t = new Thread(myRunnable);
			t.start();			
		};
		
	
public void sendVFTestMail(String to_email,String cc_email,String sub, String txt_msg, String requested_date) {
	Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
    
//	SendSms sendSms=new SendSms();
	MyRunnable_SendEmail myRunnable = new MyRunnable_SendEmail(to_email, cc_email, sub, txt_msg, requested_date);
	Thread t = new Thread(myRunnable);
	t.start();
			
		};
		
		
	
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
	public void insertCampaignData(List<Campaign_data> list) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into campaign_data (account,sub,del,pending,timee) values(?,?,?,?,?)");
			
			for(Campaign_data campaign_data:list) {
				pst.setString(1,campaign_data.getAccount());
				pst.setLong(2, campaign_data.getSub());
				pst.setLong(3, campaign_data.getDel());
				pst.setLong(4, campaign_data.getPending());
				pst.setString(5, campaign_data.getTimee());
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
	public int  insertDailyReportRecord(String datetime,String tested) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into daily_report_record (datetime,status,tested) values('"+datetime+"','sent','"+tested+"')");
			
				
				 i=pst.executeUpdate();
				  System.out.println(pst.toString()); 

		  if(i>0) {
			  System.out.println("successfull===>"+pst.toString());
		  }else {
			  System.out.println("faileddd===>"+pst.toString()); 
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
		return i;
	
	}
	public int  insertVFRecords(ReportRecord record) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int i=0;
		    PreparedStatement pst=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into vfreportrecord (account,sub,del,del_per,failed,failed_per,waiting,waiting_per,mtd,report_date) values(?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1, record.getAccount());
			pst.setLong(2, record.getSub());
			pst.setLong(3, record.getDel());
			pst.setLong(4, record.getDel_per());
			pst.setLong(5, record.getFailed());
			pst.setLong(6, record.getFailed_per());
			pst.setLong(7, record.getWaiting());
			pst.setLong(8, record.getWaiting_per());
			pst.setLong(9, record.getMtd());
			pst.setString(10, record.getReport_date());
			
				
				 i=pst.executeUpdate();
				  System.out.println(pst.toString()); 

		  if(i>0) {
			  System.out.println("successfull===>"+pst.toString());
		  }else {
			  System.out.println("faileddd===>"+pst.toString()); 
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
		return i;
	
	}
	public ArrayList<ReportRecord> recordForTodayMail(String datee){
		ArrayList<ReportRecord> rr=new ArrayList<>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			conn=DbConnection.getInstance().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery("select * from vfreportrecord where report_date='"+datee+"'");
			while(rs.next()) {
				rr.add(parseReportRecord(rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(st!=null) {
					st.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
		}
		
		return rr;
	}
	public ReportRecord parseReportRecord(ResultSet rs)throws Exception{
		ReportRecord rr=new ReportRecord();
		
		rr.setAccount(rs.getString("account"));
		rr.setDel(rs.getLong("del"));
		rr.setDel_per(rs.getLong("del_per"));
		rr.setFailed(rs.getLong("failed"));
		rr.setFailed_per(rs.getLong("failed_per"));
		rr.setMtd(rs.getLong("mtd"));
		rr.setReport_date(rs.getString("report_date"));
		rr.setSub(rs.getLong("sub"));
		rr.setWaiting(rs.getLong("waiting"));
		rr.setWaiting_per(rs.getLong("waiting_per"));
		return rr;
	}
	public int  getIdForUpdatingMTD(String account,long sub,long del,long failed,long waiting,String test_report) {
		Connection Conn=DbConnection.getInstance().getConnection();
		   int id=0;
		    PreparedStatement pst=null;
		    ResultSet rs=null;
		   
		try 
		{
			pst=Conn.prepareStatement("insert into vfirst_mtd (account,sub,del,failed,waiting,test_report) values('"+account+"',"+sub+","+del+","+failed+","+waiting+",'"+test_report+"')",Statement.RETURN_GENERATED_KEYS);
			
				
				 int i=pst.executeUpdate();
				  System.out.println(pst.toString()); 

		  if(i>0) {
			  ResultSet gen=pst.getGeneratedKeys();
			  System.out.println("successfull===>"+pst.toString());
			  if(gen.next()) {
					id=gen.getInt(1);
				}
		  }else {
			  System.out.println("faileddd===>"+pst.toString()); 
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
		return id;
	
	}
	public long  getLatestMTD(String account,String testing) {
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        long mtd=0;

        try
        {
     	  st=conn.createStatement();
     	  if(testing.equals("no")) {
           	 rs = st.executeQuery("SELECT mtd FROM mtd_values where account='"+account+"'");

     	  }else if(testing.equals("yes")) {
           	 rs = st.executeQuery("SELECT mtd FROM mtd_values_test where account='"+account+"'");

     	  }
      	 while(rs.next())
      	 {
      		 
      		 mtd=rs.getLong("mtd");
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
		return mtd;
       
}
	public long  getMTDValue(String account) {
        Connection conn=DbConnection.getInstance().getConnection();
         Statement st=null;
        ResultSet rs=null;
        long mtd=0;

        try
        {
     	  st=conn.createStatement();
     	  
           	 rs = st.executeQuery("SELECT SUM(sub) as mtd FROM vfreportrecord where account='"+account+"'");

      	 while(rs.next())
      	 {
      		 
      		 mtd=rs.getLong("mtd");
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
		return mtd;
       
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
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr12' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr12, \r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr11' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr11, \r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr3' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr3,\r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfCamp' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp,\r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr,\r\n" + 
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfgsm' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfgsm,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfCamp1' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp1,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfCamp2' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp2,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr31' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr31,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr14' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr14,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr5' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr5,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr6' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr6,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTrN01' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTrN01,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr7' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr7,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstPR2' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstPR2,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfTest' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfTest,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstPr4' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstPr4,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstPr3' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstPr3,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr9' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr9,\r\n" +
      	 		"(SELECT SUB FROM server4 WHERE ACCOUNT ='vfirstTr10' AND DATE(DATETIME) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfirstTr10\r\n" +

      			 " FROM server4 LIMIT 1;");
      	 while(rs.next())
      	 {
      		count=rs.getLong("vfirstPr4")+rs.getLong("vfirstPr3")+rs.getLong("vfirstTr9")+rs.getLong("vfirstTr10")+rs.getLong("vfirstTr7")+rs.getLong("vfirstPR2")+rs.getLong("vfTest")+rs.getLong("vfgsm")+rs.getLong("vfirstTr")+rs.getLong("vfCamp")+rs.getLong("vfCamp1")+rs.getLong("vfCamp2")+rs.getLong("vfirstTr1")+rs.getLong("vfirstTr2")+rs.getLong("vfirstTr3")+rs.getLong("vfirstPR1")+rs.getLong("vfirstTr4")+rs.getLong("vfirstTr12")+rs.getLong("vfirstTr11")+rs.getLong("vfirstTr31")+rs.getLong("vfirstTr14")+rs.getLong("vfirstTr5")+rs.getLong("vfirstTr6")+rs.getLong("vfirstTrN01");
      	 
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
	public long lastSubCountForCampaign() {

        Connection conn=DbConnection.getInstance().getConnection();
        Statement st=null;
        ResultSet rs=null;
        long count=0;
        try
        {
        	
     	  st=conn.createStatement();
     	  
      	 rs = st.executeQuery("SELECT id,(SELECT sub FROM campaign_data WHERE ACCOUNT ='vfCamp' AND DATE(timee) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp,\r\n" + 
      	 		"(SELECT sub FROM campaign_data WHERE ACCOUNT ='vfCamp1' AND DATE(timee) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp1,\r\n" +
      	 		"(SELECT sub FROM campaign_data WHERE ACCOUNT ='vfCamp2' AND DATE(timee) = CURDATE() ORDER BY id DESC LIMIT 1) AS vfCamp2\r\n" +
      			 " FROM campaign_data LIMIT 1;");
      	 while(rs.next())
      	 {
      		count=rs.getLong("vfCamp")+rs.getLong("vfCamp1")+rs.getLong("vfCamp2");
      	 
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
			System.out.println("accuntname=="+details.getAccountname());
			if(details.getAccountname().equalsIgnoreCase("vfirstTr1") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr2") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr3") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr4") ||
					details.getAccountname().equalsIgnoreCase("vfirstPR1") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr12") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr11") ||
					details.getAccountname().equalsIgnoreCase("vfCamp") ||
					details.getAccountname().equalsIgnoreCase("vfirstTr")||
					details.getAccountname().equalsIgnoreCase("vfgsm")||
					details.getAccountname().equalsIgnoreCase("vfCamp1")||
					details.getAccountname().equalsIgnoreCase("vfCamp2")||
					details.getAccountname().equalsIgnoreCase("vfirstTr31")||
					details.getAccountname().equalsIgnoreCase("vfirstTr14")||
					details.getAccountname().equalsIgnoreCase("vfirstTr5")||
					details.getAccountname().equalsIgnoreCase("vfirstTr6")||
					details.getAccountname().equalsIgnoreCase("vfirstTrN01")||
					details.getAccountname().equalsIgnoreCase("vfirstTr7")||
					details.getAccountname().equalsIgnoreCase("vfirstPR2")||
					details.getAccountname().equalsIgnoreCase("vfTest")||
					details.getAccountname().equalsIgnoreCase("vfirstPr4")||
					details.getAccountname().equalsIgnoreCase("vfirstPr3")||
					details.getAccountname().equalsIgnoreCase("vfirstTr9")||
				details.getAccountname().equalsIgnoreCase("vfirstTr10")){
			String data=apiController.getServer4DataToApi(details.getAccountname(), details.getPwd(), fromdate, todate);
			try {
				//System.out.println(data);
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
                        long pending_per=0;
                        
                         total_sub = total_sub + (int) sub;
                         total_del = total_del + (int) del;
                         total_pending = total_pending + pending;
                       //  System.out.println("del=="+del);
                        // System.out.println("sub=="+sub);
                        // System.out.println("per=="+(del *100) * sub);
                         long per =0;
                         if(sub!=0) {
                        	 per =(del*100 / (sub));
                        	 pending_per=(pending*100 / (sub));
                         }
                        
                      //  System.out.println(per);
                        server4.setSUB(sub);
                        server4.setDEL(del);
                        server4.setPending(pending);
                        server4.setPercentage(per);
                        server4.setPending_per(pending_per);
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
	public List<Server4VfReport> getServer4DataForVFWithApi(String fromdate,String todate,String testing){
		List<Server4VfReport> list=new ArrayList<Server4VfReport>();
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		ArrayList<AccountDetails> accountDetails =daoImpl.getAccountDetails();
		ApiController apiController=new ApiController();
		long total_sub=0;
		long total_del=0;
		long total_failed=0;
		for(AccountDetails details:accountDetails) {
			System.out.println("accuntname=="+details.getAccountname());
			if(details.getAccountname().equalsIgnoreCase("vfirstTr31")||
					details.getAccountname().equalsIgnoreCase("vfirstTr14")||
					details.getAccountname().equalsIgnoreCase("vfirstTr5")||
					details.getAccountname().equalsIgnoreCase("vfirstTr6")){
			String data=apiController.getServer4DataForVFToApi(details.getAccountname(), details.getPwd(), fromdate, todate);
			try {
				//System.out.println(data);
				JSONObject jsonObject=new JSONObject(data);
				JSONObject response=jsonObject.getJSONObject("response");
				if(response.has("report_smsSummaryList")) {
					JSONArray report_smsSummaryList=response.getJSONArray("report_smsSummaryList");
					JSONObject report_smsSummaryobj=report_smsSummaryList.getJSONObject(0);
					if(report_smsSummaryobj.has("report_smsSummary")) {
						Server4VfReport server4VfReport=new Server4VfReport();
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
						String f="0";
						if(smsSummary.getFailed()!=null) {
							f=smsSummary.getFailed();
						}
                        long sub=Long.parseLong(t);
                        long del=Long.parseLong(s);
                        long failed=Long.parseLong(f);
                        
                        long d_per=0;
                        long f_per=0;
                        if(sub!=0) {
                        	d_per =(del*100 / (sub));
                        }
                        if(failed!=0) {
                        	f_per =(failed*100 / (sub));
                        }
                        long waiting=0;
                        long waiting_per=0;
                        
                        
                      /*  total_sub = total_sub + (int) sub;
                         total_del = total_del + (int) del;
                         total_failed = total_failed + (int) failed;*/
                       //  System.out.println("del=="+del);
                        // System.out.println("sub=="+sub);
                        // System.out.println("per=="+(del *100) * sub);
                       /*  long total_d_per =0;
                         long total_f_per =0;
                         
                         if(total_sub!=0) {
                        	 total_d_per =(total_del*100 / (total_sub));
                         }
                         if(total_failed!=0) {
                        	 total_f_per =(total_failed*100 / (total_sub));
                         }
                        */
                      //  System.out.println(per);
                        server4VfReport.setSUB(sub);
                        
                        server4VfReport.setDEL(del);
                        server4VfReport.setDel_per(d_per);
                        server4VfReport.setFAILED(failed);
                        server4VfReport.setFailed_per(f_per);
                        server4VfReport.setWAITING(waiting);
                        server4VfReport.setWaiting_per(waiting_per);
                        server4VfReport.setAccount(details.getAccountname());
                        server4VfReport.setId((long) details.getAccountid());
                        int id=getIdForUpdatingMTD(details.getAccountname(), sub, del, failed, waiting, testing);
                        long mtd=0;
                        if(id>0) {
                        	mtd=getLatestMTD(details.getAccountname(),testing);
                        	System.out.println("mtd==>"+mtd);
                        }
                        int update_mtd=updateMTDinRecords(mtd+sub, id);
                        if(update_mtd>0) {
                        	int u=updateMTDinMtdValuesTable(details.getAccountname(),mtd+sub,testing);
                        }
                        server4VfReport.setMtd(mtd+sub);
                        list.add(server4VfReport);
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
	public List<Campaign_data> getCampaignData(String fromdate,String todate,String timeStamp){
		List<Campaign_data> list=new ArrayList<Campaign_data>();
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		ArrayList<AccountDetails> accountDetails =daoImpl.getAccountDetails();
		ApiController apiController=new ApiController();
		long total_sub=0;
		long total_del=0;
		long total_failed=0;
		for(AccountDetails details:accountDetails) {
			if(details.getAccountname().equalsIgnoreCase("vfCamp")||
					details.getAccountname().equalsIgnoreCase("vfCamp1")||
					details.getAccountname().equalsIgnoreCase("vfCamp2")){
			String data=apiController.getServer4DataToApi(details.getAccountname(), details.getPwd(), fromdate, todate);
			try {
				//System.out.println(data);
				JSONObject jsonObject=new JSONObject(data);
				JSONObject response=jsonObject.getJSONObject("response");
				if(response.has("report_smsSummaryList")) {
					JSONArray report_smsSummaryList=response.getJSONArray("report_smsSummaryList");
					JSONObject report_smsSummaryobj=report_smsSummaryList.getJSONObject(0);
					if(report_smsSummaryobj.has("report_smsSummary")) {
						Campaign_data campaign_data=new Campaign_data();
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
						String f="0";
						if(smsSummary.getFailed()!=null) {
							f=smsSummary.getFailed();
						}
						long p=0;
						if(smsSummary.getPending()!=0) {
							p=smsSummary.getPending();
						}
                        long sub=Long.parseLong(t);
                        long del=Long.parseLong(s);
                        long pending=p;
                        
                        long per=0;
                        if(sub!=0) {
                        	per =(del*100 / (sub));
                        }
                        
                 
                       campaign_data.setAccount(details.getAccountname());
                       campaign_data.setSub(sub);
                       campaign_data.setDel(del);
                       campaign_data.setPer(per);
                       campaign_data.setPending(pending);
                       campaign_data.setTimee(timeStamp);
                       
                        list.add(campaign_data);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}

		return list;
	}
	public List<Server4VfReport> getServer4DataForVFReportRecord(String fromdate,String todate,String testing){
		List<Server4VfReport> list=new ArrayList<Server4VfReport>();
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		ArrayList<AccountDetails> accountDetails =daoImpl.getAccountDetails();
		ApiController apiController=new ApiController();
		long total_sub=0;
		long total_del=0;
		long total_failed=0;
		for(AccountDetails details:accountDetails) {
			System.out.println("accuntname=="+details.getAccountname());
			if(details.getAccountname().equalsIgnoreCase("vfirstTr31")||
					details.getAccountname().equalsIgnoreCase("vfirstTr14")||
					details.getAccountname().equalsIgnoreCase("vfirstTr5")||
					details.getAccountname().equalsIgnoreCase("vfirstTr6")){
			String data=apiController.getServer4DataForVFToApi(details.getAccountname(), details.getPwd(), fromdate, todate);
			try {
				//System.out.println(data);
				JSONObject jsonObject=new JSONObject(data);
				JSONObject response=jsonObject.getJSONObject("response");
				if(response.has("report_smsSummaryList")) {
					JSONArray report_smsSummaryList=response.getJSONArray("report_smsSummaryList");
					JSONObject report_smsSummaryobj=report_smsSummaryList.getJSONObject(0);
					if(report_smsSummaryobj.has("report_smsSummary")) {
						Server4VfReport server4VfReport=new Server4VfReport();
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
						String f="0";
						if(smsSummary.getFailed()!=null) {
							f=smsSummary.getFailed();
						}
                        long sub=Long.parseLong(t);
                        long del=Long.parseLong(s);
                        long failed=Long.parseLong(f);
                        
                        long d_per=0;
                        long f_per=0;
                        if(sub!=0) {
                        	d_per =(del*100 / (sub));
                        }
                        if(failed!=0) {
                        	f_per =(failed*100 / (sub));
                        }
                        long waiting=0;
                        long waiting_per=0;
                        
                        
                      /*  total_sub = total_sub + (int) sub;
                         total_del = total_del + (int) del;
                         total_failed = total_failed + (int) failed;*/
                       //  System.out.println("del=="+del);
                        // System.out.println("sub=="+sub);
                        // System.out.println("per=="+(del *100) * sub);
                       /*  long total_d_per =0;
                         long total_f_per =0;
                         
                         if(total_sub!=0) {
                        	 total_d_per =(total_del*100 / (total_sub));
                         }
                         if(total_failed!=0) {
                        	 total_f_per =(total_failed*100 / (total_sub));
                         }
                        */
                      //  System.out.println(per);
                        server4VfReport.setSUB(sub);
                        
                        server4VfReport.setDEL(del);
                        server4VfReport.setDel_per(d_per);
                        server4VfReport.setFAILED(failed);
                        server4VfReport.setFailed_per(f_per);
                        server4VfReport.setWAITING(waiting);
                        server4VfReport.setWaiting_per(waiting_per);
                        server4VfReport.setAccount(details.getAccountname());
                        server4VfReport.setId((long) details.getAccountid());
                       /* int id=getIdForUpdatingMTD(details.getAccountname(), sub, del, failed, waiting, testing);
                        long mtd=0;
                        if(id>0) {
                        	mtd=getLatestMTD(details.getAccountname(),testing);
                        	System.out.println("mtd==>"+mtd);
                        }
                        int update_mtd=updateMTDinRecords(mtd+sub, id);
                        if(update_mtd>0) {
                        	int u=updateMTDinMtdValuesTable(details.getAccountname(),mtd+sub,testing);
                        }*/
                        long mtd=getMTDValue(details.getAccountname());
                        System.out.println("mtd=====>"+mtd);
                    	int u=updateMTDinMtdValuesTable(details.getAccountname(),mtd+sub,testing);

                        server4VfReport.setMtd(mtd+sub);
                        list.add(server4VfReport);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	
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
     	  
      	 rs = st.executeQuery("select * from accountdetails_s5 where pwd is not null");
      //	 rs = st.executeQuery("select * from accountDetails_s5 where pwd is not null");

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
