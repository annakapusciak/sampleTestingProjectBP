package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.components.Product;

import java.util.List;
import java.util.Objects;

public class CartPage extends GlobalPage {

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    private WebElement cartHeader;

    @FindBy(xpath = "(//div[@class='cart__item--qty'])[1]")
    private WebElement quantityHeader;

    @FindBy(xpath = "(//div[@class='cart__item--price text-right'])[1]")
    private WebElement totalHeader;

    @FindBy(xpath = "//a[@href='/cart/change?line=1&quantity=0']")
    private WebElement removeButton;

    @FindBy(xpath = "(//input[@type='text' and @value=1])[2]")
    private WebElement quantityInput;

    @FindBy(xpath = "(//input[@type='text' and @value=1])[2]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//a[@class='cart__product-name']")
    private List<WebElement> listOfProductsAddedToCart;

    public boolean itemAddedToTheCart() {
        return cartPageLoadedCorrectly() &&
                Objects.requireNonNull(getListOfAddedProducts()).size() >= 1;
    }

    private List<Product> getListOfAddedProducts() {
        return null;
    }

    public boolean cartPageLoadedCorrectly() {
        return cartHeader.getText().equals("Cart") &&
                quantityHeader.getText().equals("Quantity") &&
                totalHeader.getText().equals("Total") &&
                removeButton.getText().equals("Remove") &&
                quantityInput.isDisplayed() &&
                checkoutButton.isEnabled();
    }



}
