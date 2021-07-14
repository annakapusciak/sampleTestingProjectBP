package driver;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager {

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        return options;
    }

}