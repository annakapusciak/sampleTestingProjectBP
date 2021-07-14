package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class DriverManagerFactory {

    public WebDriver getManager(String browser) throws Exception {

        ChromeDriverManager chromeDriverManager = new ChromeDriverManager();
        WebDriver driver;
        BrowserType type = BrowserType.valueOf(browser.toUpperCase());

        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeDriverManager.getChromeOptions());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new Exception("Unsupported browser: " + browser);
        }
        return driver;
    }

    public enum BrowserType {
        CHROME, FIREFOX
    }

}
