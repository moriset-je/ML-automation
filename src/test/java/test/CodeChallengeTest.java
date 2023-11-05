package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BasePage;

import javax.swing.*;
import java.time.Duration;

public class CodeChallengeTest extends BasePage {

    @Test
    public void CodeChallengeTest() throws InterruptedException {

       /*
       Complete the test cases according to the specifications described on the PDF sent by Email
       The Chrome Web driver is already configured, you are ready to work.
       */
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Wait for elements before throwing an exception

        //Select Country Argentina
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#AR"))).click();

        //Accept Cookies
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("header[data-siteid='MLA']")));
        try {
            driver.findElement(By.cssSelector("button[data-testid='action:understood-button']")).click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        //Close onboarding location tooltip
        driver.findElement(By.cssSelector(".onboarding-cp-tooltip"));
        driver.findElement((By.cssSelector(".andes-tooltip__buttons button:last-child"))).click();

        //Select Vehicles Category from dropdown
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector(".nav-menu-categories-link"))).click();
        driver.findElement(By.xpath("//a[text()='Vehículos']")).click();

        //Search for "Autos y Camionetas"
        String vehicleTypesDropdownText = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.id(":r0:-display-values"))).getText();
        Assert.assertEquals(vehicleTypesDropdownText, "Autos y Camionetas");
        driver.findElement(By.cssSelector(".andes-button__content")).click();

        //Scroll to locations and open "more options"
        WebElement locationsElement = wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//*[contains(text(),'Ubicación')]//following-sibling::ul//child::li//child::a[contains(text(), 'Mostrar más')]")));
        actions.moveToElement(locationsElement);
        actions.perform();
        locationsElement.click();

        //Search and select Cordoba Location
        String enterLocation = "Cordoba";
        String enterPrice = "2000000";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(":Rjhjlb9:")));
        driver.findElement(By.cssSelector("input[data-testid='search_bar']")).sendKeys(enterLocation);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-search-search-modal-filter-name"))).click();

        //Scroll to price section and input amount
        WebElement maxPriceInput = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector("input[data-testid='Maximum-price']")));
        actions.moveToElement(maxPriceInput);
        actions.perform();
        maxPriceInput.sendKeys(enterPrice);
        maxPriceInput.sendKeys(Keys.ENTER);

        //Order by lowest to highest price
        wait.until(ExpectedConditions.elementToBeClickable(By.id(":Rlh9b9:-display-values"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(":Rlh9b9:-menu-list-option-price_asc"))).click();

        //Asserts on tags
        String location = "Córdoba";
        String price = "2.000.000";
        Assert.assertEquals(driver.findElement
                        (By.xpath("//div[@class='andes-tag__label' and contains(text(), 'Hasta $')]")).getText(),
                "Hasta $ " + price);
        Assert.assertEquals(driver.findElement
                        (By.xpath("//div[@class='andes-tag__label' and contains(text(), '" + location + "')]")).
                getText(), location);


        //results
        String amountResults = driver.findElement(By.cssSelector(".ui-search-search-result__quantity-results")).getText();
        JOptionPane.showMessageDialog(null, "Number of results available in Cordoba under 2.000.000: "
                + amountResults, "Results Info", JOptionPane.INFORMATION_MESSAGE);


    }


}
