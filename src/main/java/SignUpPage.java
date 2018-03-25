import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SignUpPage {


    private By emailField = By.id("register-email");
    private By confirmEmailField = By.id("register-confirm-email");
    private By passwordField = By.id("register-password");
    private By nameField = By.id("register-displayname");
    private By monthDropDown = By.id("register-dob-month");

    private String monthDropDownOption = "//select[@id=\"register-dob-month\"]/option[text()=\"%s\"]";//вместо %s потом передадим название месяца
    private By dayField = By.id("register-dob-day");
    private By yearField = By.id("register-dob-year");

    private String sexRadioButton = "//li[@id=\"li-gender\"]/label[normalize-space()=\"%s\"]/input";//вместо %s потом передадим название пола человека
    private By shareCheckBox = By.id("register-thirdparty");
    private By registerButton = By.id("register-button-email-submit");

    private By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";//вместо %s потом передадим текст ошибки


    public SignUpPage open(){
        Selenide.open("/");
        return this;
    }




    public SignUpPage typeEmail(String email){
        $(emailField).setValue(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String confirmEmail){
        $(confirmEmailField).setValue(confirmEmail);
        return this;
    }

    public SignUpPage typePassword(String password){
        $(passwordField).setValue(password);
        return this;
    }

    public SignUpPage typeName(String name){
        $(nameField).setValue(name);
        return this;
    }

    public SignUpPage setMonth(String month){
        $(monthDropDown).selectOption(month);
        return this;
    }



    public SignUpPage typeDay(String day){
        $(dayField).setValue(day);
        return this;
    }

    public SignUpPage typeYear(String year){
        $(yearField).setValue(year);
        return this;
    }


    public SignUpPage setSex (String value){
        $(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }


    public SignUpPage setShare (boolean value){
        $(shareCheckBox).setSelected(value);
        return this;
    }


    public void clickSignUpButton(){
        $(registerButton).click();
    }



    public ElementsCollection getErrors(){
        return $$(errorLabel);
    }


    public SelenideElement getErrorByNumber(int number){
        return getErrors().get(number - 1);
    }

    public SelenideElement getError(String message){
        return $(By.xpath(String.format(errorByText, message)));
    }



}
