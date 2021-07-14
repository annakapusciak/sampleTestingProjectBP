package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends GlobalPage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer_logout_link")
    private WebElement logoutButton;

    public boolean isPageLoadedCorrectly() {
        return logoutButton.isEnabled() &&
                pageHeader.getText().equals("My Account");
    }

}
