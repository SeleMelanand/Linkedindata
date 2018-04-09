package employeedata;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class EmployeeDetails extends IndividualResourceInitial{
	//public RemoteWebDriver driver;

	public void storingemployeedate() throws InterruptedException{
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
