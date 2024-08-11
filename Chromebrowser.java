package com.demoapp.uitesting;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Chromebrowser {
	
ChromeDriver driver;
	
	
	@Test
	public void OpenAndNavigateonChrome() throws IOException {
		
		driver= new ChromeDriver();
		driver.get("https://www.getcalley.com/");
		driver.manage().window().maximize();
		
		String[] urls = {
	            "https://www.getcalley.com/",
	            "https://www.getcalley.com/calley-lifetime-offer/",
	            "https://www.getcalley.com/see-a-demo/",
	            "https://www.getcalley.com/calley-teams-features/",
	            "https://www.getcalley.com/calley-pro-features/"
	        };
		Dimension[] resolutions = {
	            new Dimension(1536, 864),
	            new Dimension(1920, 1080),
	            new Dimension(1366, 768)
	        };
		
		
		//try {
            // Iterate through URLs and resolutions
            for (int i = 0; i < urls.length; i++) {
                driver.get(urls[i]);

                for (Dimension resolution : resolutions) {
                    driver.manage().window().setSize(resolution);

                    // Directory to save screenshots for this resolution
                    String resolutionFolderName = resolution.width + "x" + resolution.height;
                    Path resolutionDir = Paths.get("screenshots_Chrome", resolutionFolderName);
                    if (!Files.exists(resolutionDir)) {
                        Files.createDirectories(resolutionDir);
                    }

                    // Take screenshot
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    String imageFileName = "screenshot_" + (i + 1) + "_" + resolution.width + "x" + resolution.height + ".png";
                    Path destination = resolutionDir.resolve(imageFileName);

                    // Save screenshot
                    Files.copy(screenshot.toPath(), destination);

                    System.out.println("Saved screenshot for " + urls[i] + " at resolution " + resolution.width + "x" + resolution.height);
                }
            }
        //} catch (IOException e) {
            //e.printStackTrace();}
		
	driver.quit();	
}
}
