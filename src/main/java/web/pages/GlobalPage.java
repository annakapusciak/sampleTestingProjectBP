package web.pages;

import driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class GlobalPage {

    public WebDriver driver;

    @FindBy(tagName = "h1")
    public WebElement pageHeader;

    @FindBy(xpath = "//li/a[@href='#']")
    public List<WebElement> mainMenuItems;

    @FindBy(xpath = "(//a[@href='/account'])[1]")
    private WebElement accountButton;

    @FindBy(xpath = "//a[@href='/search']")
    private WebElement searchButton;
    @FindBy(name = "q")
    public WebElement searchBar;

    @FindBy(xpath = "//a[@href='/cart']")
    private WebElement cartButton;
    @FindBy(xpath = "//p[@class='appear-animation appear-delay-3']")
    private WebElement emptyCartInfo;
    @FindBy(xpath = "(//button[@class='drawer__close-button js-drawer-close'])[2]")
    private WebElement closeCart;

    @FindBy(xpath = "//div[@class='cart__checkout-wrapper']/button[@name='checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//p[@class='shopify-challenge__message']")
    private WebElement captchaNotification;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement captchaSubmitButton;

    @FindBy(xpath = "//div[@class='errors']/ul/li")
    private WebElement validationErrorMessage;

    public GlobalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage searchForItems(String itemToSearch) {
        log.info("Searching for " + itemToSearch);
        searchButton.click();
        searchBar.clear();
        searchBar.sendKeys(itemToSearch);
        searchBar.sendKeys(Keys.ENTER);
        return new SearchResultsPage(DriverManager.getDriver());
    }

    public LoginPage goToLoginPage() {
        accountButton.click();
        return new LoginPage(DriverManager.getDriver());
    }

    public GlobalPage checkCart() {
        cartButton.click();
        return new GlobalPage(DriverManager.getDriver());
    }

    public boolean isCartEmpty() {
        waitForElementToBeVisible(emptyCartInfo);
        return emptyCartInfo.isDisplayed() &&
                emptyCartInfo.getText().equals("Your cart is currently empty.");
    }

    public void waitForElementToBeVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public CheckoutPage checkoutCart() {
        waitForElementToBeVisible(checkoutButton);
        checkoutButton.click();
        return new CheckoutPage(DriverManager.getDriver());
    }

    public boolean captchaWasRequested() {
        waitForElementToBeVisible(captchaNotification);
        return captchaNotification.isDisplayed() &&
                captchaNotification.getText().equals("To continue, let us know you're not a robot.") &&
                captchaSubmitButton.isEnabled();
    }

    public boolean invalidCredentialsMessageIsVisible() {
        waitForElementToBeVisible(validationErrorMessage);
        return validationErrorMessage.isDisplayed() &&
                validationErrorMessage.getText().equals("Incorrect email or password.");
    }

}
