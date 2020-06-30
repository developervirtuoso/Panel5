package com.beans;
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject; 
public class XLSXReaderExample {
	public static void main(String[] args)   
	{  
	try  
	{  
	JSONArray jsonArray=new JSONArray();
	File file = new File("C:\\Users\\Dell\\Desktop\\count.xlsx");   //creating a new file instance  
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
		break;  
		}
		default:  
		}
	}else if(i==1){
		switch (cell.getCellType())               
		{  
		case Cell.CELL_TYPE_STRING: {   //field that represents string cell type  
		System.out.print(cell.getStringCellValue() + "\t\t\t");  
		jsonObject.put("name", cell.getStringCellValue()+"");
		break;  
		}
		case Cell.CELL_TYPE_NUMERIC: {   //field that represents number cell type  
		System.out.print(cell.getNumericCellValue() + "\t\t\t");  
		jsonObject.put("name", cell.getNumericCellValue()+"");
		break;  
		}
		default:  
		}
	}
	 i++; 
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
}
