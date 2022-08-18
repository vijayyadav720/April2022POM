package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * this method is used to initialize the driver on the basis of given browser Name
	 * @param browserName
	 * @return WebDriver
	 */
	public WebDriver initDriver(Properties prop) {
		
			String browserName = prop.getProperty("browser");
			System.out.println("browser name is : "+browserName);
			optionsManager = new OptionsManager(prop);
			
		
			if(browserName.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					//driver = new ChromeDriver(optionsManager.getChromeOptions());
					tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					
			}
		
			else if(browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			else if(browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				
			}
		
			else {
				System.out.println("please pass the right browser name : "+ browserName);
			}
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));	
			getDriver().manage().deleteAllCookies();
		//driver.manage().window().maximize();
			getDriver().get(prop.getProperty("url"));
		//System.out.println(driver.getCurrentUrl());
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
			return tlDriver.get();
			}
	/**
	 *  this method will return all the values from config properties file created under source folder name config
	 * @return this return properties reference
	 */
	public Properties initProp() {
		 prop = new Properties();
		 FileInputStream ip = null;
		 //commnd line agrs   --> maven
		 //mvn clean install -Denv="stage" -Dbrowser ="chrome"
		
		 
		 String envName = System.getProperty("env");
		 System.out.println("Running test cases on environment : "+envName);
		 
		 if(envName == null) {
			 System.out.println("No environemnt is given ... hence running it on QA env");
			 try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 }
		 else {
			 try {
			 switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			default:
				System.out.println("Please pass the right environment ..... " +envName);
				break;
			}
			 } catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			 
		 }
		 
		 
		try {
			ip = new FileInputStream("./src/test/resources/config/cofig.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	return prop;
	}
	/**
	 * take screenshot
	 * @param methodName
	 * @return
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//Users/naveenautomationlabs/Documents/workspace1/
		String path = System.getProperty("user.dir")+"/screenshot/" + methodName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

