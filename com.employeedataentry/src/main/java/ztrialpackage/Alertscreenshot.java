package ztrialpackage;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Alertscreenshot {
	int number;

	
	@Test
	public void alertprint() throws AWTException, IOException, InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.switchTo().frame("iframeResult");
		
		driver.findElementByXPath("//button[text()='Try it']").click();
		
		Thread.sleep(5000);
	
		BufferedImage bi = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(bi, "jpeg", new File("./screenshot/screenshot1.jpeg"));
		
		
		/*try {
			
		} catch (WebDriverException e) {
			System.out.println("Webdriver exception occured while taking screenshot");
		} catch (IOException e) {
			System.out.println("File not found exception occured while taking screenshot");
		}*/

		
	}


	private File File(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
