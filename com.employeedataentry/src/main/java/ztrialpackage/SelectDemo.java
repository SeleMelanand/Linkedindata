package ztrialpackage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SelectDemo {

	@Test
	public void selectclassdemo() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		String basewindowname = driver.getWindowHandle();
		System.out.println("current window name is - " + basewindowname);
		
		Thread.sleep(5000);
		
		driver.findElementByLinkText("Flights").click();

		Set<String> windowname  = driver.getWindowHandles();
		int windowscount = windowname.size();
		
		System.out.println(windowscount);
		
		for (String winhandle : windowname) {
			if(winhandle!= basewindowname){
				driver.switchTo().window(winhandle);
			}
		}
		
		String newwindow = driver.getWindowHandle();
		System.out.println("new generated window is : "+ newwindow);
		
		//driver.findElementByXPath("//a[@rel='dropmenu2_b']").click();
		
		driver.switchTo().window(basewindowname);
		
		driver.findElementByXPath("//div[@id='bluemenu']/ul/li[2]/a").click();
		
		WebElement webelementtourism = driver.findElementByXPath("//div[@id='bluemenu']/ul/li[2]/ul/li/a");
		Actions action = new Actions(driver);
		action.moveToElement(webelementtourism).build().perform();
		
		WebElement webelementmaharajaexp = driver.findElementByXPath("//div[@id='bluemenu']/ul/li[2]/ul/li/ul/li/a");
		action.moveToElement(webelementmaharajaexp).click().build().perform();

		Set<String>totalwindowname = driver.getWindowHandles();
		int currentwindowcount = totalwindowname.size();
		System.out.println(currentwindowcount);
		
		for (String abc : totalwindowname) {
			
			driver.switchTo().window(abc);
			String titlename = driver.getTitle();

			if(titlename.equalsIgnoreCase("IRCTC, Flight Ticket, Air Ticket, Cheap Flight, Low Fare Flight")){
				
				driver.findElementById("origin").sendKeys("madurai",Keys.ARROW_DOWN,Keys.ENTER);
				driver.findElementById("destination").sendKeys("chennai",Keys.ARROW_DOWN,Keys.ENTER);

				
			}
			
			
			
			
		}
		

		/*Select airlinepreference = new Select(driver.findElementByName("airlinePreference"));
		airlinepreference.selectByValue("0S");
		Thread.sleep(1000);

		List<WebElement> allvalues = airlinepreference.getOptions();
		int size = allvalues.size();

		for (int i = 0; i < size - 1; i++) {
			System.out.println(allvalues.get(i).getText());
		}
		
		driver.findElementByClassName("srchbtn").click();
		Thread.sleep(3000);
		
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();		
		driver.switchTo().alert().accept();*/
		
		
		
		

	}

}
