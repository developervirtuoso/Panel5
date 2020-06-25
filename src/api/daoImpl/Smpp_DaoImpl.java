package api.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

import com.beans.ApiEmail;

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
}
