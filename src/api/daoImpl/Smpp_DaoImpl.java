package api.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import common.database.DbConnection;

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
}
