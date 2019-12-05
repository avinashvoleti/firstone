package mypackage;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import com.google.common.io.Files;


public class Practice {
	static WebDriver driver;
	
	public static void main(String[] args) {
		Practice p = new Practice();
		p.setup();
		p.gettitle(driver);
		
	}
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Ecilipse\\chrome driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/WebTable.html");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test(String phonenumber)
	{
		String phone = phonenumber;
		String lastname = driver.findElement(By.xpath("//div[contains(text(),'"+phone+"')]/parent::div/preceding-sibling::div[1]")).getText();
        System.out.println(lastname);	
        String gender = driver.findElement(By.xpath("//div[contains(text(),'"+phone+"')]/parent::div/preceding-sibling::div[2]")).getText();
        System.out.println(gender);
        String firstname = driver.findElement(By.xpath("//div[contains(text(),'"+phone+ "')]/parent::div/preceding-sibling::div[3]")).getText();
        System.out.println(firstname);
        String email = driver.findElement(By.xpath("//div[contains(text(),'"+phone+"')]/parent::div/preceding-sibling::div[4]")).getText();
        System.out.println(email);
        
	}
	public void getcommands() {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
		String currenturl = driver.getCurrentUrl();
		System.out.println("The Current url is:" + currenturl);
	}
	public void navigatecommands() {
		driver.navigate().to("https://www.amazon.in/gp/most-gifted/?ref_=nav_cs_giftideas");
        driver.navigate().refresh();
        driver.navigate().to("https://www.amazon.in/gp/bestsellers/?ref_=nav_cs_bestsellers");
        driver.navigate().back();
        driver.navigate().forward();      
	}
	public void xpath() {
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//a[@data-nav-ref='nav_ya_signin']/span[2]")).click();
	}
	public void select() {
		driver.get("http://the-internet.herokuapp.com/dropdown");
		Select dropdown = new Select(driver.findElement(By.id("dropdown")));
		dropdown.selectByIndex(1);
		
	}
	public void Action(){
		driver.get("https://www.rythmos.com/contact-us");
		Actions contact = new Actions(driver);
		contact.moveToElement(driver.findElement(By.xpath("//a[@href='javascript:;']"))).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[@href='data-support']")).click();
	}
	public void Actiondraganddrop(){
		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		Actions drop = new Actions(driver);
		drop.clickAndHold(driver.findElement(By.id("draggable"))).moveToElement(driver.findElement(By.id("droppable"))).release().build().perform();
	}
	public void screenshot()
{
	  driver.get("https://www.amazon.in/");
      File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
		FileUtils.copyFile(src, new File ("D:\\Selenium\\screenshot\\google.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void popup() {
		driver.get("https://register.rediff.com/register/register.php?FormName=user_details");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Alert alert= driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}
	public void windowhandler() {
		driver.get("http://the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]")).click();
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		Set<String> windows = driver.getWindowHandles();
		windows.remove(parent);
		for(String s : windows) {
			driver.switchTo().window(s);
		}
		System.out.println(driver.findElement(By.xpath("//h3")).getText());
	}
	public  String gettitle (WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String title = js.executeScript("return document title;").toString();
		return title;
	}
}
