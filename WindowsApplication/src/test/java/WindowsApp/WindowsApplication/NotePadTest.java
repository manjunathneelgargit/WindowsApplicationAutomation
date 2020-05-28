package WindowsApp.WindowsApplication;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.windows.WindowsDriver;

public class NotePadTest 
{
	//Windows Driver is link WebDriver
	WindowsDriver driver = null;
	
	@BeforeClass
	public void configBC() throws Exception
	{
		//Need to set capabilities before proceeding with writing the script 
		DesiredCapabilities cap = new DesiredCapabilities();
		//"app" is a key and 2nd argument has to be the complete path of .exe file 
		// of the application which you want to automate
		cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		
		//create an object of WindowsDriver and pass URL Object and capabilities class object reference as argument
		//for URL constructor we need to pass the URL given by WinAppDriver in DOS
		driver = new WindowsDriver(new URL("http://127.0.0.1:4723"),cap);
		
		//maximum wait time for element to be identified
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//to maximize the application
		driver.manage().window().maximize();
		
	}
	
	//below is the test script
	@Test
	public void clickHelp_About_TestCase() throws Exception
	{
		//here we are using "findElementByName" to identify the element
		driver.findElementByName("Help").click();
		driver.findElementByName("About Notepad").click();
		driver.findElementByName("OK").click();
		driver.findElementByName("Text Editor").sendKeys("This is my 1st Windows Application Test Script");
		driver.findElementByName("Text Editor").clear();
		
		driver.findElementByName("Edit").click();
		//will use findElementByAccessibilityId to identify the element-
		//-when we get NoSuchElementException by using findElementByName
		// The Accessibility is the "AutomationID" of that particular element
		driver.findElementByAccessibilityId("26").click();
		
		//get the content of text editor
		String data = driver.findElementByName("Text Editor").getAttribute("Value.value");
		System.out.println(data);
	}
	
	
	@AfterClass
	public void configAC()
	{
		//close the application
		driver.quit();
	}
}
