package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends GlobalPage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//th[@class='product__description']")
    private List<WebElement> listOfProductsToCheckout;

    @FindBy(id = "checkout_email")
    private WebElement email;

    @FindBy(id = "checkout_shipping_address_first_name")
    private WebElement firstName;

    @FindBy(id = "checkout_shipping_address_last_name")
    private WebElement lastName;

    @FindBy(id = "checkout_shipping_address_address1")
    private WebElement address;

    @FindBy(id = "checkout_shipping_address_city")
    private WebElement city;

    @FindBy(id = "checkout_shipping_address_phone")
    private WebElement phone;

    @FindBy(id = "continue_button")
    private WebElement continueButton;

    @FindBy(xpath = "//button[@id='continue_button']/span")
    private WebElement continueToPaymentButton;

    public int getNumberOfItemsToCheckout() {
        return listOfProductsToCheckout.size();
    }

    public boolean isPageLoadedCorrectly() {
        return getNumberOfItemsToCheckout() > 0 &&
                continueButton.isEnabled();
    }

    public void provideUserDetailsForOrderAndContinueCheckout() {
        email.sendKeys("someValidEmail@gmail.com");
        firstName.sendKeys("Joe");
        lastName.sendKeys("Doe");
        address.sendKeys("anyAddress");
        city.sendKeys("New York");
        phone.sendKeys("123456");
        continueButton.click();
    }

    public boolean isContinueToPaymentButtonEnabled() {
        return continueToPaymentButton.getText().equals("Continue to payment") &&
                continueButton.isEnabled();
    }
}
