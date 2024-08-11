package com.dealsdry.mavenproject;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;


public class Login {

	ChromeDriver driver;
	
	
	@Test
	public void OpenAndNavigate() {
		
		driver= new ChromeDriver();
		driver.get("https://demo.dealsdray.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void UserLogin()
	{
		
		WebElement useradmin = driver.findElement(By.name("username"));
		useradmin.sendKeys("prexo.mis@dealsdray.com");
		
		WebElement userpass=driver.findElement(By.name("password"));
		userpass.sendKeys("prexo.mis@dealsdray.com");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click() ;  
		
		
	}
	@Test
	public void UserOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Click on Order
		WebElement Order = driver.findElement(By.xpath("(//button[@type='button'])[2]"));
		if(Order.isEnabled()) {
			System.out.println("The button is enabled.");
			Order.click();
        } else {
            System.out.println("The button is disabled.");
        }
		
		//Click on Orders 
		WebElement Orders=driver.findElement(By.xpath("(//button[@type='button'])[3]"));
		//Orders.click();
		if(Orders.isEnabled()) {
			System.out.println("The Orders button is enabled.");
			Orders.click();
        } else {
            System.out.println("The button is disabled.");
        }
		
		//Click on Bulk Orders
		WebElement bulkorder=driver.findElement(By.xpath("//button[@align='right']"));
		bulkorder.click();
		
		//Pass the file to choose file
		WebElement ChooseFile = driver.findElement(By.id("mui-7"));
		ChooseFile.sendKeys("C:/Users/anees/Downloads/demo-data.xlsx");
		
		//Click on Import
		WebElement ImportBtn = driver.findElement(By.xpath("(//button[@type='button'])[7]"));
		ImportBtn.click();
		
		
		//Click Validate
		
		
		WebElement ValidateBtn = driver.findElement(By.xpath("(//button[@type='button'])[7]"));
		String buttonText = ValidateBtn.getText();
		if(buttonText.equals("Validate Data")) {
			ValidateBtn.click();
			//takeScreenshot("ValidationStarted");
		}
		else {
			System.out.println("No Such Text Found");
		}
		Thread.sleep(2000);
		//Click on pop-up
		Alert popUp = driver.switchTo().alert();
		popUp.accept();
		takeScreenshot("AfterPopUp");
	}
		
	// Method to take screenshots
    public void takeScreenshot(String screenshotName) {
        // Create a timestamp for unique file names
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Define the destination file path
        String destinationFile = "screenshots/" + screenshotName + "_" + timestamp + ".png";
        
        try {
            // Create the screenshots directory if it doesn't exist
            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdir();
            }
            // Save the screenshot
            FileHandler.copy(screenshot, new File(destinationFile));
            System.out.println("Screenshot saved as: " + destinationFile);
            
        }
        catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
	
	
    }
}
