package employeedata;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IndividualResourceInitial {
	public static RemoteWebDriver driver;

	public  String personlinked;
	public void gatherresource() throws InterruptedException, IOException {

		
		
		// To get the total number of employees linked to the company
		String resultcount = driver.findElementByXPath("//h3[contains(@class,'search-results__total')]").getText();
		String[] rc = resultcount.split(" ");
		int iteratequotient;
		if (rc[1].length() > 3) {
			rc[1] = rc[1].replace(",", "");
			System.out.println("Total number of employess in search: " + rc[1]);

			iteratequotient = Integer.parseInt(rc[1]) / 10;
			System.out.println("total number of pages to run :" + iteratequotient);
		} else {
			iteratequotient = Integer.parseInt(rc[1]) / 10;
			System.out.println("total number of pages to run :" + iteratequotient);
		}

		try {
			driver.findElementByXPath("//li[@class='page-list']/ol/li[1]").click();
		} catch (Exception e1) {
			System.out.println("Page doesnot exists");
		}

		// Loop to run for each page depending up on the total search result.
		for (int i = 1; i <= (iteratequotient + 1); i++) {

			System.out.println(i + "- page running in progress");

			// driver.findElementByXPath("//li[@class='page-list']").click();
			String mainwindow = driver.getWindowHandle();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			int browsercount = tabs.size();
			System.out.println("Total number of browsers: " + browsercount);

			if (browsercount > 1) {
				driver.switchTo().window(mainwindow);
			}

			List<WebElement> ename = driver.findElementsByXPath("//span[contains(@class,'actor-name')]");
			int count = ename.size();
			System.out.println("Total nuber of records per page : " + count);

			// Loop to run on each page to get the employee details.
			for (int j = 0; j < count; j++) {
				if (j > 5) {
					WebElement elementdown = driver.findElementByXPath("(//span[contains(@class,'actor-name')])[8]");
					JavascriptExecutor jscriptexecutor1 = (JavascriptExecutor) driver;
					jscriptexecutor1.executeScript("arguments[0].scrollIntoView(true);", elementdown);
					Thread.sleep(4000);
				}else{
					System.out.println("data lies wihin the page");
				}

				
				int n = j + 1;

				Thread.sleep(3000);
				String name = driver.findElementByXPath("(//span[contains(@class,'actor-name')])[" + n + "]").getText();

				if (name.equalsIgnoreCase("LinkedIn Member")) {
					System.out.println("Linkedin Member is in the list");

					FileWriter fw = new FileWriter("./reports/EmployeedataFAFC.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Linkedin Member is in the list");
					bw.newLine();
					bw.close();
				} else {

					WebElement webname;

					try {
						webname = driver.findElementByXPath("(//span[contains(@class,'actor-name')])[" + n + "]");
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();", webname);

					} catch (Exception e) {
						System.out.println("Unable to identify the element");
					}

					personlinked = driver.findElementByXPath("//div[contains(@class,'pv-top-card-section__information')]/div/h1")
							.getText();
					String persondesignation = driver.findElementByXPath("//h2[contains(@class,'pv-top-card-section__headline')]").getText();

					String employer = driver.findElementByXPath("//h3[contains(@class,'pv-top-card-section__company')]")
							.getText();

					String mainwindow1 = driver.getWindowHandle();
					ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
					int browsercount1 = tabs1.size();

					if (browsercount1 > 1) {
						driver.switchTo().window(mainwindow1);
					}

					driver.findElementByXPath("//button[contains(@class,'contact-see-more-less')]").sendKeys("");
					driver.findElementByXPath("//button[contains(@class,'contact-see-more-less')]").click();

					WebElement emppro = null;
					try {
						emppro = driver.findElementByXPath("//div[@class='pv-contact-info__ci-container']/a");
					} catch (Exception e1) {
						System.out.println("Webelement - profile doesnot exists");
					}
					String empprofile = emppro.getAttribute("href");

					// getting Experience details of selected resources
					ArrayList<String> employmentlist = new ArrayList<String>();

					String Experiencedetails = null;
					String companyname = null;
					String period = null;
					String employmentlocation = null;
					String employmentduration = null;
					
					
					
					for (int empcounter = 1; empcounter <= 3; empcounter++) {

						System.out.println("Details of Employment :" + empcounter);
						try {
							Experiencedetails = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + empcounter + "]/h3").getText();

						} catch (Exception e) {
							System.out.println("Experience details doesnot exists- " + personlinked);
							Experiencedetails = "-";
						}
						try {
							companyname = driver.findElementByXPath("(//div[@class='pv-entity__summary-info'])[" + empcounter + "]/h4[1]/span[2]").getText();
						} catch (Exception e) {
							System.out.println("Company name doesnot exists- " + personlinked);
							companyname = "-";
						}
						try {
							period = driver.findElementByXPath(
									"(//div[@class='pv-entity__summary-info'])[" + empcounter + "]/h4[2]/span[2]")
									.getText();
						} catch (Exception e) {
							System.out.println("Period doesnot exists- " + personlinked);
							period = "-";
						}
						try {
							employmentduration = driver.findElementByXPath(
									"(//div[@class='pv-entity__summary-info'])[" + empcounter + "]/h4[3]/span[2]")
									.getText();
						} catch (Exception e) {
							System.out.println("employmentduration doesnot exists- " + personlinked);
							employmentduration = "-";
						}
						try {
							employmentlocation = driver.findElementByXPath(
									"(//div[@class='pv-entity__summary-info'])[" + empcounter + "]/h4[4]/span[2]")
									.getText();
						} catch (Exception e) {
							System.out.println("employmentlocation doesnot exists- " + personlinked);
							employmentlocation = "-";
						}
						String employeedata = Experiencedetails + ",  " + companyname + ",  " + period + ",  "
								+ employmentduration + ",  " + employmentlocation;
						employmentlist.add(employeedata);
					}

					// getting Education details of selected resources
					List<WebElement> education = driver.findElementsByXPath("//h3[contains(@class,'pv-entity__school-name')]");
					int educationcount = education.size();
					int educindex = 0;
					ArrayList<String> educationlist = new ArrayList<String>();
					String educationperiod = null;
					System.out.println("Total number of education: " + educationcount);

					for (int m = 1; m <= educationcount; m++) {
						String educationinstitute = driver
								.findElementByXPath("(//h3[contains(@class,'pv-entity__school-name')])[" + m + "]")
								.getText();

						List<WebElement> a1 = driver
								.findElementsByXPath("(//div[@class='pv-entity__degree-info'])[" + m + "]/p");
						int a1count = a1.size();
						String degreedetails = null;
						if (a1count > 1) {
							String deg1 = driver
									.findElementByXPath(
											"(//span[@class='pv-entity__comma-item'])[" + (educindex + 1) + "]")
									.getText();
							String deg1details = driver
									.findElementByXPath(
											"(//span[@class='pv-entity__comma-item'])[" + (educindex + 2) + "]")
									.getText();
							degreedetails = deg1 + "-" + deg1details;
							educindex = educindex + a1count;
						} else {
							try {
								degreedetails = driver
										.findElementByXPath(
												"(//div[@class='pv-entity__degree-info'])[" + m + "]/p[1]/span[2]")
										.getText();
							} catch (Exception e) {
								System.out.println("Degree details not available -" + personlinked);
								degreedetails = "-";
							}
							educindex = educindex + 1;
						}
						try {
							educationperiod = driver
									.findElementByXPath("(//p[contains(@class,'pv-entity__dates')])[" + m + "]/span[2]")
									.getText();
						} catch (Exception e) {
							System.out.println("education period not available for - " + personlinked);
							educationperiod = "-";
						}

						String educationdetails = educationinstitute + ",  " + degreedetails + ",  " + educationperiod;

						educationlist.add(educationdetails);
						System.out.println(educationdetails);
					}

					Thread.sleep(8000);

					ArrayList<String> emparray = new ArrayList<String>();
					emparray.add(personlinked);
					emparray.add(persondesignation);
					emparray.add(empprofile);
					emparray.add(employer);
					emparray.addAll(employmentlist);
					emparray.addAll(educationlist);

					for (int q = 0; q < emparray.size(); q++) {
						System.out.println(emparray.get(q));
					}

					FileInputStream fis = new FileInputStream("./reports/EmployeedataFAFC.xlsx");

					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet worksheet = workbook.getSheet("Sheet1");

					int rowcount = worksheet.getLastRowNum();
					System.out.println("rowcount: " + rowcount);
					XSSFRow header = worksheet.getRow(0);
					XSSFRow row = worksheet.createRow(rowcount + 1);
					int columncount = header.getLastCellNum();
					System.out.println("columncount: " + columncount);

					for (int k = 0; k < columncount; k++) {
						try {
							row.createCell(k).setCellValue(emparray.get(k));
						} catch (Exception e) {
							System.out.println("User details not available" + personlinked);
						}
					}

					FileOutputStream fos = new FileOutputStream("./reports/EmployeedataFAFC.xlsx");
					workbook.write(fos);
					workbook.close();

					System.out.println("Record saved successfully ");

					driver.navigate().back();
					Thread.sleep((long) (Math.random() * 5000));
				}

			}
			try {
				WebElement elementnext = driver.findElementByXPath("//div[@class='next-text']");
				JavascriptExecutor jscriptexecutor = (JavascriptExecutor) driver;
				jscriptexecutor.executeScript("arguments[0].scrollIntoView(true);", elementnext);
				Thread.sleep(3000);

				driver.findElementByXPath("//div[@class='next-text']").click();

				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Navigated to Last page");
			}
		} // Total number of pages for a particular search ends here

		driver.findElementById("advanced-search-title").clear();

	}

}
