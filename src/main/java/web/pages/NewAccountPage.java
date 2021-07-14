package web.pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewAccountPage extends GlobalPage {

    public NewAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "CreatePassword")
    private WebElement password;

    @FindBy(xpath = "//input[@value='Create']")
    private WebElement createButton;

    public boolean isPageLoadedCorrectly() {
        return pageHeader.getText().equals("Create Account") &&
                isDisplayedAsEmptyInput(firstName) &&
                isDisplayedAsEmptyInput(lastName) &&
                isDisplayedAsEmptyInput(email) &&
                isDisplayedAsEmptyInput(password) &&
                createButton.isEnabled();
    }

    public GlobalPage createNewAccount() {
        firstName.sendKeys("Johe");
        lastName.sendKeys("Doe");
        email.sendKeys("someValidMail1234@gmail.com");
        password.sendKeys("someValidPass");
        createButton.click();
        return new GlobalPage(DriverManager.getDriver());
    }

    private boolean isDisplayedAsEmptyInput(WebElement element) {
        return element.isDisplayed() && element.getText().isEmpty();
    }

}
