import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage signUpPage;


    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "/Users/astemirpachev/SelenideExample/src/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/int/signup");
    }



    @Test
    public void typeInvalidYear(){
        signUpPage = new SignUpPage(driver);

        signUpPage.setMonth("December");
        signUpPage.typeDay("20");

        Actions actions = new Actions(driver);
        actions.click().build().perform();

        signUpPage.typeYear("85");
        signUpPage.setShare(true);

        Assert.assertTrue(signUpPage.isErrorVisible("Please enter a valid year."));

        Assert.assertFalse(signUpPage.isErrorVisible("When were you born?"));
    }


    @Test
    public void typeInvalidEmail(){
        signUpPage = new SignUpPage(driver);
        signUpPage.typeEmail("test@mail.test")
                .typeConfirmEmail("nottest@mail.nottest")
                .typeName("Ivan")
                .clickSignUpButton();

        Assert.assertTrue(signUpPage.isErrorVisible("Email address doesn't match."));

    }


    @Test
    public void signUpWithEmptyPassword(){
        signUpPage = new SignUpPage(driver);
        signUpPage.typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("Ivan")
                .clickSignUpButton();

        Assert.assertTrue(signUpPage.isErrorVisible("Please choose a password."));

    }


    @Test
    public void typeInvalidValues(){
        signUpPage = new SignUpPage(driver);
        signUpPage.typeEmail("testmail")
                .typeConfirmEmail("test@mail.test")
                .typePassword("aezakmi000")
                .typeName("Ivan")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();

        Assert.assertEquals(4, signUpPage.getErrors().size());
        Assert.assertEquals("When were you born?", signUpPage.getErrorByNumber(3));
    }



    @After
    public void tearDown(){
        driver.quit();
    }

}
