<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java"   errorPage="" %>
<%@ page language ="java" import="java.sql.*" %>
<%@ page language="java" import="javax.servlet.*" %>
<%@ page language="java" import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%!    int count1 = 0, count2 = 0;
    int dom = 0;
    String str1 = "", str2 = "";
%>
<%
String excelfile="";
String excelfileurl="";
String filePath = "";
String pageurl = "";
int imei = 0;
    String fieldName = "";
    String docTitle = "";
    String userid="";
    String docType = "";
    String itemname="";
    String savedFile1="";
    int status=0;
    String url="";
    String image="";
    String[] multiIMEI = new String[5000];
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 
    if (!isMultipart) {
        System.out.println("Else is not executed");
    } else {
    	FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = null;
        try {

            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator itr = items.iterator();
        String name = "";
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            System.out.println("here " + item);
            if (item.isFormField()) {
                fieldName = item.getFieldName();
                if(fieldName.equalsIgnoreCase("pageurl")){
                	pageurl = item.getString();
                }
                System.out.println("here fieldname" + fieldName);
                String value = item.getString();
                System.out.println("here fieldname" + value);
            } else {
                try {
     String itemName = item.getName(); 
     	System.out.println("itemNameitemNameitemNameitemName"+itemName);
    	System.out.println("useriduseriduseriduseriduseriduserid"+userid);
    	itemname=itemName;
                    File savedFile = new File(getServletContext().getRealPath("/")+"UploadedImages/Excel/" + itemName );
                    excelfile="UploadedImages/Excel/" + itemName;
                    // File savedFile = new File("J:\\abc\\" + name);
                    item.write(savedFile);
                    System.out.print("nnnnnnnnnnnnnnn"+savedFile.getAbsolutePath());
                    savedFile1=savedFile.getAbsolutePath();
                    excelfileurl=savedFile.getAbsolutePath();;
                    url=savedFile.getAbsolutePath();
					 StringTokenizer localStringTokenizer = new StringTokenizer(itemName, ".");
					if (localStringTokenizer.countTokens() >= 2) {
                        str1 = localStringTokenizer.nextToken();
                        str2 = localStringTokenizer.nextToken();
                    } else {
                    }
                   // response.sendRedirect("fileUploadSuccess.jsp");
                } catch (Exception e) {
                    System.out.println("here while uploadin" + e);
                    
                }
            }
        }
    }

    try {
    	System.out.println("image"+excelfileurl);
    	Smpp_DaoImpl smpp_DaoImpl=new Smpp_DaoImpl();
    	 String encryptedString = smpp_DaoImpl.encrypt(excelfileurl) ; 
    	 String decryptedString = smpp_DaoImpl.decrypt(encryptedString) ;
    	 System.out.println("encryptedString====>"+encryptedString);
    	 System.out.println("decryptedString====>"+decryptedString);
    	 
    	response.sendRedirect(pageurl+"?filename="+encryptedString);
    	
    	
   } catch (Exception e) {
       e.printStackTrace();
   }%>
