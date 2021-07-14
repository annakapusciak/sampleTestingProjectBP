package web.pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends GlobalPage {

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final static String LOCATOR_OF_SLIDER_ELEMENT = "(//div[@class='grid-product__title'])";

    @FindBy(xpath = LOCATOR_OF_SLIDER_ELEMENT + "[1]")
    private WebElement firstItemOnSlider;

    @FindBy(xpath = LOCATOR_OF_SLIDER_ELEMENT + "[2]")
    private WebElement secondItemOnSlider;

    public boolean isPageLoadedCorrectly() {
        return mainMenuItems.get(0).getText().trim().equals("FEATURED") &&
                mainMenuItems.get(1).getText().trim().equals("SHOP ALL") &&
                mainMenuItems.get(2).getText().trim().equals("GET INVOLVED");
    }

    public ProductPage goToDetailsOfFirstItemOnSlider() {
        return goToDetailsOfElementOfSlider(1);
    }

    private ProductPage goToDetailsOfElementOfSlider(int i) {
        switch (i) {
            case 1:
                waitForElementToBeVisible(firstItemOnSlider);
                firstItemOnSlider.click();
                break;
            case 2:
                waitForElementToBeVisible(secondItemOnSlider);
                secondItemOnSlider.click();
                break;
        }
        return new ProductPage(DriverManager.getDriver());
    }

}
