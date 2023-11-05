package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BasePage {

     public static WebDriver driver;

    @BeforeTest
    public void  tearUp(){
        //Web Driver Instance Creation
        ChromeOptions options = new ChromeOptions();
        //WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mercadolibre.com/");
    }

    @AfterTest
    public void tearDown(){
        //Close the WebDriver instance
        driver.quit();
    }

    public static WebDriver getDriver() { return driver; }

}
