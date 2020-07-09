package com.everis.datascraping;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class App 
{
	
	private static final String FILE_NAME = "/temp/TransaccionesSap.xlsx";
	private static int rowNum = 0;
	private static String modulo = "";
	
    public static void main( String[] args ) throws InterruptedException
    {
    	 
       
    	XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Transacciones");
               
        
    	//HashMap<String, Transaccion> map = new HashMap<String, Transaccion>();
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
        String baseUrl = "https://www.tutorialkart.com/sap-tcode/";       
        driver.get(baseUrl);
        

        WebElement div=driver.findElement(By.xpath("//*[@id='post']/div/div/div[2]"));        
        List<WebElement> links= div.findElements(By.tagName("a"));

        HashMap<String,String> linkMap= new HashMap<String,String>();
        
      
        for(WebElement a:links){        	
        	modulo=a.getText(); 
        	String href= a.getAttribute("href");
        	linkMap.put(modulo, href);        	
        }
        
        
        linkMap.forEach((k, v) -> {
        	System.out.println(k);
        	driver.get(v);
        	
        	List<WebElement> tables= driver.findElements(By.className("tcode_table"));
        	
        	for(WebElement table: tables){        		  
        		List<WebElement> trs=table.findElements(By.tagName("tr"));        		
        		for(WebElement tr: trs){
        			Row row = sheet.createRow(rowNum++);
        			List<WebElement> tds=tr.findElements(By.tagName("td"));    
        			if(tds.size()!=0){
        				int colNum = 0;
        				Cell cell = row.createCell(colNum++);
        				cell.setCellValue((String) k);
        				for(WebElement td:tds){
        					Cell cell1 = row.createCell(colNum++);
        					cell1.setCellValue((String) td.getText());
        				}
        				//Transaccion t = new Transaccion(k,tds.get(0).getText(),tds.get(1).getText(),tds.get(2).getText());
	        			//map.put(k, t);
	        			System.out.println("termino transaccion"+rowNum+"del modulo "+modulo);
        			}
        			
        		}
        	
        	}
        	driver.navigate().back();
        	        	
        });
        
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        
        //map.forEach((k, v) -> {
        //    System.out.format(v.getModulo()+"-"+v.getDescription()+"-"+v.getSiglasModulo()+"-"+v.getTcode());
        //});
        
      
    }
}
