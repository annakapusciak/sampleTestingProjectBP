package web;

import configs.ConfigurationForUi;
import driver.DriverManager;
import driver.DriverManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import web.pages.*;

import java.time.Duration;

@Slf4j
public abstract class BaseWeb {

    public static final ConfigurationForUi configuration = new ConfigurationForUi();
    public static final int TIMEOUT = configuration.getTimeout();

    public WebDriver driver;
    public GlobalPage globalPage;
    public HomePage homePage;
    public SearchResultsPage searchResultsPage;
    public LoginPage loginPage;
    public MyAccountPage myAccountPage;
    public NewAccountPage newAccountPage;
    public ProductPage productPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;

    @BeforeClass(alwaysRun = true)
    public void beforeTestClass(ITestContext context) {

        configuration.setupUiConfiguration();

        log.info("----------------------------------------------------------");
        log.info("Running test: " + context.getCurrentXmlTest().getName());
        log.info("----------------------------------------------------------");

        try {
            driver = new DriverManagerFactory().getManager(configuration.getBrowser());
            DriverManager.setDriver(driver);
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            DriverManager.getDriver().manage().window().maximize();
            initElements();
            goToHomePage();
        } catch (Exception e) {
            log.error("Failed to setup driver. Error: " + e.getMessage());
        }
    }

    @AfterClass
    public void afterTestClass() {
        DriverManager.quit();
    }

    void goToHomePage() {
        DriverManager.getDriver().get(configuration.getUrlBase());
    }

    void goBack() {
        DriverManager.getDriver().navigate().back();
    }

    private void initElements() {
        globalPage = PageFactory.initElements(DriverManager.getDriver(), GlobalPage.class);
        homePage = PageFactory.initElements(DriverManager.getDriver(), HomePage.class);
        searchResultsPage = PageFactory.initElements(DriverManager.getDriver(), SearchResultsPage.class);
        loginPage = PageFactory.initElements(DriverManager.getDriver(), LoginPage.class);
        myAccountPage = PageFactory.initElements(DriverManager.getDriver(), MyAccountPage.class);
        newAccountPage = PageFactory.initElements(DriverManager.getDriver(), NewAccountPage.class);
        productPage = PageFactory.initElements(DriverManager.getDriver(), ProductPage.class);
        cartPage = PageFactory.initElements(DriverManager.getDriver(), CartPage.class);
        checkoutPage = PageFactory.initElements(DriverManager.getDriver(), CheckoutPage.class);
    }

}
