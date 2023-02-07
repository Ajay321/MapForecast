package practice.MapForecast;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class MapInfo {


	WebDriver driver;
	@Test
	public void testMap() throws Exception
	{
		
		String path =System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+"//driver//chromedriver.exe");
		driver=new  ChromeDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://petdiseasealerts.org/forecast-map#/");
		String state="connecticut";
		getStateInfo(state);
		

	}
	
	   public void getStateInfo(String state ) throws Exception
	   {
		String frameid=driver.findElement(By.xpath("//*[@src=\"https://api.capcvet.org/api/embed/forecast/n0vA38xpQ4J7Tcv4PA4v3NXtr4yo41AQVYaUQ7qr?iframe=1\"]")).getAttribute("id");
		driver.switchTo().frame(frameid);
		
		int allstate=driver.findElements(By.xpath("//*[@id='regions']//*[local-name()='g' and @class='region']")).size();
		String name;
		for (int i = 1; i <= allstate; i++) {
			name=driver.findElement(By.xpath("//*[@id='regions']//*[local-name()='g' and @class='region']["+i+"]")).getAttribute("id");
			System.out.println(i +" : "+ name);
		}
	
		driver.findElement(By.xpath("//*[@id='"+ state +"']//*[local-name()='g' and @class='rpath']//*[local-name()='path']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"map-component\"]/ul/li[1]/a")).click();
		
	   }
		

}
