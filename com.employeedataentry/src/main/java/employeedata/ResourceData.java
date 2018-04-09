package employeedata;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ResourceData extends IndividualResourceInitial {

	@Test
	public void navgatingforresource() throws InterruptedException, IOException {

		//String[] titlesearch = { "Managing Director", "CEO", "VP", "CTO", "Manager" };

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://in.linkedin.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElementById("login-email").sendKeys("pulsar.ansari@gmail.com");
		driver.findElementById("login-password").sendKeys("Haseena@150");
		driver.findElementById("login-submit").click();

		driver.findElementByXPath("//artdeco-typeahead-input[@class='ember-view']/input").sendKeys("First American Financial Corporation", Keys.ENTER);

		// Selecting the keyword dropdown to open the current company search
		WebElement webnamecurcompany = driver.findElementByXPath("(//span[@class='search-facet__name'])[5]");
				
		JavascriptExecutor executorcc = (JavascriptExecutor) driver;
		executorcc.executeScript("arguments[0].click();", webnamecurcompany);

		// getting the list of present companies and selecting the appropriate
		// company to be searched.
		List<WebElement> ccompanylist = driver.findElementsByXPath("(//ol[starts-with(@class,'search-facet__values')])[4]/li");
		for (WebElement a1 : ccompanylist) {
			if (a1.getText().equalsIgnoreCase("First American Financial Corporation")) {
				driver.findElementByXPath("//label[starts-with(@for,'sf-facetCurren')]/div").click();
			}
		}

		// Selecting the keyword dropdown to open the title search
		WebElement webnamekeyword = driver.findElementByXPath("(//span[@class='search-facet__name'])[2]/h3");
		JavascriptExecutor executorwk = (JavascriptExecutor) driver;
		executorwk.executeScript("arguments[0].click();", webnamekeyword);

		// Entering the values in the Title to search
		driver.findElementById("advanced-search-title").sendKeys("CEO", Keys.ENTER);

		System.out.println("Resources searched successfully for current company");
		Thread.sleep(4000);

		// going in to search result to generate all the details of the
		// resources
		IndividualResourceInitial ir = new IndividualResourceInitial();
		ir.gatherresource();

		driver.findElementByXPath("(//li[@class='sub-nav-item'])[1]/button").click();
		Thread.sleep(4000);

		driver.findElementByXPath("(//li[@class='sub-nav-item'])[2]/button").click();

		// Selecting the dropdown to open the previouscompany search
		WebElement webnameprecompany = driver.findElementByXPath("(//span[@class='search-facet__name'])[6]");
		JavascriptExecutor executorpc = (JavascriptExecutor) driver;
		executorpc.executeScript("arguments[0].click();", webnameprecompany);

		List<WebElement> precompanylist1 = driver.findElementsByXPath("(//ol[starts-with(@class,'search-facet__values')])[5]/li");
		for (WebElement b1 : precompanylist1) {
			if (b1.getText().equalsIgnoreCase("First American Financial Corporation")) {
				driver.findElementByXPath("//label[starts-with(@for,'sf-facetPastCompany')]/div").click();
			}
		}

		Thread.sleep(3000);

		// Selecting the keyword dropdown to open the title search
		WebElement webnamekeyword1 = driver.findElementByXPath("(//span[@class='search-facet__legend-container'])[2]");
		JavascriptExecutor executorwk1 = (JavascriptExecutor) driver;
		executorwk1.executeScript("arguments[0].click();", webnamekeyword1);

		driver.findElementById("advanced-search-title").sendKeys("CEO", Keys.ENTER);

		Thread.sleep(3000);

		ir.gatherresource();

		System.out.println("Resources gathered successfully for previous company also");
	}
}