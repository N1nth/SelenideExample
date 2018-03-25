import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
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

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignUpTest {
    private SignUpPage signUpPage;


    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "/Users/astemirpachev/SelenideExample/src/geckodriver");
        baseUrl = "https://www.spotify.com/int/signup";
        //browser = "marionette";
        WebDriverRunner.setWebDriver(new FirefoxDriver());
    }



    @Test
    public void typeInvalidYear(){
        signUpPage = new SignUpPage();

        signUpPage.open()
                .setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);

        signUpPage.getError("Please enter a valid year.").shouldBe(visible);
        signUpPage.getError("When were you born?").shouldNotBe(visible);
    }


    @Test
    public void typeInvalidEmail(){
        signUpPage = new SignUpPage();
        signUpPage.open()
                .typeEmail("test@mail.test")
                .typeConfirmEmail("nottest@mail.nottest")
                .typeName("Ivan")
                .clickSignUpButton();

        signUpPage.getError("Email address doesn't match.").shouldBe(visible);
    }


    @Test
    public void signUpWithEmptyPassword(){
        signUpPage = new SignUpPage();
        signUpPage.open()
                .typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("Ivan")
                .clickSignUpButton();

        signUpPage.getError("Please choose a password.").shouldBe(visible);

    }


    @Test
    public void typeInvalidValues(){
        signUpPage = new SignUpPage();
        signUpPage.open()
                .typeEmail("testmail")
                .typeConfirmEmail("test@mail.test")
                .typePassword("aezakmi000")
                .typeName("Ivan")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();

        signUpPage.getErrors().shouldHave(size(4));
        signUpPage.getErrorByNumber(3).shouldHave(text("When were you born?"));
    }


}
