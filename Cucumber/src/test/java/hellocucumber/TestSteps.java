package hellocucumber;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSteps {

    private WebDriver userDriver;
    private WebDriver adminDriver;

    private WebDriverWait waitUser;
    private WebDriverWait waitAdmin;

    private String beforeName;
    @Before
    public void setup(){
        String driverType = "webdriver.chrome.driver";
        String path = "/src/chromedriver.exe";
        System.setProperty(driverType, System.getProperty("user.dir") + path);
        this.userDriver = new ChromeDriver();
        this.adminDriver = new ChromeDriver();

        this.waitUser = new WebDriverWait(userDriver, Duration.ofSeconds(40));
        this.waitAdmin = new WebDriverWait(adminDriver, Duration.ofSeconds(40));

        // maximize the window - some web apps look different in different sizes
        userDriver.manage().window().maximize();
        adminDriver.manage().window().maximize();


    }

    @Given("a client is login with email {string} and password {string}")
    public void aClientIsLoginWithEmailAndPassword(String username, String password) {

        userDriver.get("http://localhost/opencart/index.php?route=account/login&language=en-gb");
        waitUser.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(username);
        userDriver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(password);
        waitUser.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='submit']"))).click();


    }

    @And("the admin login with {string} and {string} to his account")
    public void theAdminLoginWithAndToHisAccount(String username, String password) {
        adminDriver.get("http://localhost/opencart/rize");
        waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-username']"))).sendKeys(username);
        adminDriver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(password);
        waitAdmin.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='submit']"))).click();
    }


    @And("Client is on the Edit Account page trying to update his name to {string}")
    public void clientIsOnTheEditAccountPageTryingToUpdateHisNameTo(String newName) {
        beforeName = userDriver.findElement(By.id("input-firstname")).getAttribute("value");
        waitUser.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Edit your account information"))).click();
        waitUser.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys(newName);
    }

    @And("the admin has deactivated the user")
    public void theAdminHasDeactivatedTheUser() {
        try {
            Thread.sleep(2000);
            waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-customer']"))).click();
            waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Customers']"))).click();
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) adminDriver;
            waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form-customer\"]/div[1]/table/tbody/tr/td[7]/div/a"))).click();
            Thread.sleep(1000);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(1000);
            waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-status"))).click();
            WebElement Element = adminDriver.findElement(By.xpath("//*[@class='btn btn-primary']"));
            js.executeScript("arguments[0].scrollIntoView();", Element);
            Thread.sleep(1000);
            waitAdmin.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='btn btn-primary']"))).click();
        }catch(Exception e){
        }
    }

    @When("Client click to submit the name change")
    public void clientClickToSumbitTheNameChange() {
            waitUser.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Continue']"))).click();
    }

    @Then("the user name was not changed")
    public void theUserNameWasNotChanged() {
        String afterChange = adminDriver.findElement(By.id("input-firstname")).getAttribute("value");
        System.out.println(afterChange);
        assertEquals(afterChange,beforeName);
        //assert (userDriver.getCurrentUrl().equals("http://localhost/opencart/index.php?route=account/login&language=en-gb"));
    }


}
