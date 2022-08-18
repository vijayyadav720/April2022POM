package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
 //private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String , String> productMap;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	
	private By  productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By  productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	public ProductInfoPage(WebDriver driver)
	{
		//this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	
	public String getProductHeaderValue()
	{
		//By mainProduct = By.xpath("//h1[text()='"+mainProductName+"]");
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println(productHeaderValue);
		
		return productHeaderValue;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsToBeVisible(productImages, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
		System.out.println("images count : "+imagesCount);
		return imagesCount;
	}

	//to add details of device
	public Map<String , String> getProductInfo() {
			//productMap = new HashMap<String , String>();
			//productMap = new TreeMap<String , String>();
			productMap = new LinkedHashMap<String , String>();
			//adding product name in map
			productMap.put("productname" , getProductHeaderValue());
			getProductMetaData();
			getProductPriceData();
			System.out.println("============= actual Product info =============");
			productMap.forEach((k,v) -> System.out.println(k+" : "+v));
		return productMap;
		
		
	}

			private void getProductMetaData() {
				
				//Brand: Apple
				//Product Code: Product 18
				//Reward Points : 800
				//Availability : In Stock
				List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
					for(WebElement e: metaDataList)
					{
						String text = e.getText();
						String meta[] = text.split(":");
						String key = meta[0].trim();
						String value = meta[1].trim();
						productMap.put(key, value);
					}
			}
			
			private void getProductPriceData() {
				//price data
				List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
				//String productPrice = metaPriceList.get(0).getText().trim();
				String productExTaxPrice = metaPriceList.get(1).getText().trim();
				//key passing user defined bcz these are only two values due to this reason we have passed keys manually
				productMap.put("productPrice", productExTaxPrice);
				productMap.put("exTaxPrice", productExTaxPrice);		
			}




}
