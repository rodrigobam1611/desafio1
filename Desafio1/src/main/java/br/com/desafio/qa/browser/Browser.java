package br.com.desafio.qa.browser;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public static WebDriver getTempDriver() {
		return tempDriver;
	}

	public static void setTempDriver(WebDriver tempDriver) {
		Browser.tempDriver = tempDriver;
	}

	public static List<String> cenariosExecutados;
	
	static Browser localDriver = new Browser();
	
	public static WebDriver driver;

	public static WebDriver tempDriver;
	
	public WebDriver driverAtual;
	
	public Browser() {};
	
	public static Browser getInstance(){
		return localDriver;
	}
	
	public static WebDriver abrirNavegador(String url) {
		
		try { 
			
			if (driver == null) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
		      }
				
			tempDriver = driver;
			
    	} catch (Exception e) {
    		
			 System.out.println(e.getMessage());
		}
		
		return tempDriver;
	}
	
	public static void fecharNavegador(){
		
		if(driver != null){
			
			driver.quit();
		}
	}

	public WebDriver getDriverAtual() {
		return driverAtual;
	}

	public void setDriverAtual(WebDriver driverAtual) {
		this.driverAtual = driverAtual;
	}
}
