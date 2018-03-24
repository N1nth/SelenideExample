import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }


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



    public SignUpPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String confirmEmail){
        driver.findElement(confirmEmailField).sendKeys(confirmEmail);
        return this;
    }

    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name){
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month){
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, month)))).click();
        return this;
    }



    public SignUpPage typeDay(String day){
        driver.findElement(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year){
        driver.findElement(yearField).sendKeys(year);
        return this;
    }


    public SignUpPage setSex (String value){
        driver.findElement(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }


    public SignUpPage setShare (boolean value){
        WebElement checkbox = driver.findElement(shareCheckBox);

        if (!checkbox.isSelected() == value){
            checkbox.click();
        }

        return this;
    }


    public void clickSignUpButton(){
        driver.findElement(registerButton).click();
    }



    public List<WebElement> getErrors(){
        return driver.findElements(errorLabel);
    }


    public String getErrorByNumber(int number){
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message){
        return driver.findElements(By.xpath(String.format(errorByText, message))).size() > 0
                && driver.findElements(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }



}
