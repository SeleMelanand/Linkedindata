package testpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class test1 {
	
	public static RemoteWebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://in.linkedin.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElementById("login-email").sendKeys("pulsar.ansari@gmail.com");
		driver.findElementById("login-password").sendKeys("Haseena@150");
		driver.findElementById("login-submit").click();

		driver.findElementByXPath("//artdeco-typeahead-input[@class='ember-view']/input").sendKeys("First American Financial Corporation", Keys.ENTER);
		
		// Selecting the dropdown to open the pastcompany search
		WebElement webnameprecompany = driver.findElementByXPath("(//span[@class='search-facet__name'])[6]");
		JavascriptExecutor executorpc = (JavascriptExecutor) driver;
		executorpc.executeScript("arguments[0].click();", webnameprecompany);
		
		List<WebElement> precompanylist1 = driver
				.findElementsByXPath("(//ol[starts-with(@class,'search-facet__values')])[5]/li");
		for (WebElement b1 : precompanylist1) {
			if (b1.getText().equalsIgnoreCase("First American Financial Corporation")) {
				driver.findElementByXPath("//label[starts-with(@for,'sf-facetPastCompany')]/div").click();
			}
		}

		Thread.sleep(3000);
		
		// Selecting the keyword dropdown to open the title search
		WebElement webnamekeyword = driver.findElementByXPath("(//span[@class='search-facet__name'])[2]/h3");
		JavascriptExecutor executorwk = (JavascriptExecutor) driver;
		executorwk.executeScript("arguments[0].click();", webnamekeyword);
		// Entering the values in the Title to search
		driver.findElementById("advanced-search-title").sendKeys("CEO", Keys.ENTER);

		Thread.sleep(3000);
		
		WebElement webname = driver.findElementByXPath("(//span[contains(@class,'actor-name')])[1]");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webname);

		String personlinked = driver.findElementByXPath("//div[contains(@class,'pv-top-card-section__information')]/div/h1").getText();
		
		ArrayList<String> employmentlist = new ArrayList<String>();

		String Experiencedetails = null;
		String companyname = null;
		String period = null;
		String employmentlocation = null;
		String employmentduration = null;
		String employeedata;
		
		if (driver.findElementByXPath("//button[starts-with(@class,'pv-profile-section')]").isDisplayed()) {
			driver.findElementByXPath("//button[starts-with(@class,'pv-profile-section')]").click();
		} else {
			System.out.println("More positions not available");
		}
		
		Thread.sleep(3000);
		List<WebElement> abc = driver.findElementsByXPath("//div[starts-with(@class,'pv-entity__summary')]/h3");
		int compcount = abc.size();
		System.out.println("Total number of companies worked - " + compcount);
		int counter = 1;
				for (WebElement a1 : abc) {
					try {
						Experiencedetails = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + counter + "]/h3").getText();
					} catch (Exception e) {
						System.out.println("Experience details doesnot exists- " + personlinked);
					}

					try {
						companyname = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + counter + "]/h4[1]/span[2]").getText();
					} catch (Exception e) {
						System.out.println("Experience details doesnot exists- " + personlinked);
					}
					try {
						period = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + counter + "]/h4[2]/span[2]").getText();
					} catch (Exception e) {
						System.out.println("Period doesnot exists- " + personlinked);
					}
					try {
						employmentduration = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + counter + "]/h4[3]/span[2]").getText();
					} catch (Exception e) {
						System.out.println("employmentduration doesnot exists- " + personlinked);
					}
					try {
						employmentlocation = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + counter + "]/h4[4]/span[2]").getText();
					} catch (Exception e) {
						System.out.println("employmentlocation doesnot exists- " + personlinked);
					}

					employeedata = Experiencedetails + ",  " + companyname + ",  " + period + ",  "
							+ employmentduration + ",  " + employmentlocation;
					employmentlist.add(employeedata);

				//	System.out.println(employmentlist.get(counter-1));

					counter = counter + 1;
				}
				
				if(compcount!=10){
				int empcounter = employmentlist.size();
				System.out.println(empcounter);
				int empaddcounter = 10 - empcounter;
				for(int k=0;k<empaddcounter;k++ ){
					employmentlist.add(empcounter, null);
					empcounter= empcounter+1;
				}									
				}
				else{
					System.out.println("Employee has worked in 10 companies");
				}
				
				for(int l=0;l<10;l++){
					System.out.println(employmentlist.get(l));
				}
				
				
	}

}
