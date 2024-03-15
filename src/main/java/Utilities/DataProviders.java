package Utilities;


import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utilities.XLUtility;

public class DataProviders {
		
		

		@DataProvider(name="validLogin")
		public String[][] getAllValidData() throws IOException
		{
			String path=System.getProperty("user.dir")+"//Resources//UserTestData.xlsx";
			//System.out.println(path);
			XLUtility xl = new XLUtility(path);
		
			int rownum=xl.getRowCount("Sheet1");	
			int colcount=xl.getCellCount("Sheet1",1);
			
			String data[][]=new String[rownum][colcount];
			
			for(int i=1;i<=rownum;i++)
			{		
				for(int j=0;j<colcount;j++)
				{
					data[i-1][j]= xl.getCellData("Sheet1",i, j);  
				}
			}
		
			return data;
		}
		

		@DataProvider(name="invalidLogin")
		public String[][] getAllInvalidData() throws IOException
		{
			String path=System.getProperty("user.dir")+"//Resources//UserTestData.xlsx";
			//System.out.println(path);
			XLUtility xl = new XLUtility(path);
		
			int rownum=xl.getRowCount("Sheet2");	
			int colcount=xl.getCellCount("Sheet2",1);
			
			String data[][]=new String[rownum][colcount];
			
			for(int i=1;i<=rownum;i++)
			{		
				for(int j=0;j<colcount;j++)
				{
					data[i-1][j]= xl.getCellData("Sheet2",i, j);  
				}
			}
		
			return data;
		}

		
		@DataProvider(name="UserNames")
		public String[] getUserNames() throws IOException
		{
			String path=System.getProperty("user.dir")+"//Resources//UserTestData.xlsx";
			XLUtility xl=new XLUtility(path);
		
			int rownum=xl.getRowCount("Sheet1");	
				
			String apidata[]=new String[rownum];
			
			for(int i=1;i<=rownum;i++)
			{		
				apidata[i-1]= xl.getCellData("Sheet1",i, 1);  
				
			}
		
			return apidata;
		}
}
