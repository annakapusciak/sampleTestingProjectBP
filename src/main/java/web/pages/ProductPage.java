package web.pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends GlobalPage {

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@name='add'][2]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='product__price']")
    private WebElement productPrice;

    public CartPage addProductToCart() throws InterruptedException {
        waitForElementToBeVisible(addToCartButton);
        addToCartButton.click();
        waitAbit(5);
        waitForElementToBeVisible(checkoutButton);
        return new CartPage(DriverManager.getDriver());
    }

    public boolean isPageLoadedCorrectly() {
        return addToCartButton.isEnabled() &&
                !pageHeader.getText().isEmpty() &&
                productPrice.isDisplayed() && productPrice.getText().contains("$");
    }

    private void waitAbit(int waitInSeconds) throws InterruptedException {
        long ms = waitInSeconds * 1000L;
        Thread.sleep(ms);
    }

}
