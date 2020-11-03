package com.test;

import api.daoImpl.ApiController;

public class testApiData {

	public static void main(String args[]) {
		ApiController apiController=new ApiController();
		String requested_date=java.time.LocalDate.now().toString();
		System.out.println(requested_date);
		String dataa=apiController.getServer4DataToApi("vfCamp", "F27TZYwd", requested_date, requested_date);
		System.out.println("dataa======="+dataa);
				
	}
}
