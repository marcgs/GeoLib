package com.mgomez.geolib.functionaltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Category(FunctionalTest.class)
public class UploadTrackTest {

    private WebDriver driver;

    @Test
    public void uploadTrack() {
        WebElement fileUploadElement = driver.findElement(By.cssSelector("[data-test-selector='fileupload']"));

        String sampleGpxFile = "src/test/resources/sample.gpx";
        File file = new File(sampleGpxFile);
        fileUploadElement.sendKeys(file.getAbsolutePath());

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        List<WebElement> trackItems = webDriverWait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[data-test-selector='track-item'")));

        assertTrue(trackItems.stream()
                .filter(t -> file.getName().equals(t.getText()))
                .findAny()
                .isPresent());
    }

    @Before
    public void openBrowser() throws InterruptedException {
        // TODO: remove workaround for giving jboss enough time to start up
        Thread.sleep(20000);
        driver = new ChromeDriver();
        driver.get("http://192.168.33.33:8080/geolib/map.html");
        //driver.get("http://localhost:8080/geolib/map.html");
        driver.navigate();
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

}
