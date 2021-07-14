package web.pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends GlobalPage {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "CustomerEmail")
    private WebElement email;

    @FindBy(id = "CustomerPassword")
    private WebElement password;

    @FindBy(xpath = "//input[@value='Sign In']")
    private WebElement signInButton;

    @FindBy(id = "customer_register_link")
    private WebElement createNewAccountButton;

    public boolean isPageLoadedCorrectly() {
        return pageHeader.getText().equals("Login") &&
                email.isDisplayed() &&
                password.isDisplayed() &&
                signInButton.isEnabled() &&
                createNewAccountButton.isEnabled();
    }

    public MyAccountPage loginUsingCredentials(String userEmail, String userPassword) {
        email.clear();
        email.sendKeys(userEmail);
        password.clear();
        password.sendKeys(userPassword);
        signInButton.click();
        return new MyAccountPage(DriverManager.getDriver());
    }

    public NewAccountPage goToCreateNewAccountPage() {
        createNewAccountButton.click();
        return new NewAccountPage(DriverManager.getDriver());
    }
}
