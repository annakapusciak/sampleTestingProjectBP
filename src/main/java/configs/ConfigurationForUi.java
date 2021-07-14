package configs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

@Slf4j
@Data
public class ConfigurationForUi {

    public String urlBase, host, browser, userEmail, userPassword;
    public int timeout;

    public Configuration setupUiConfiguration() {
        try {
            Configuration config = new PropertiesConfiguration("web.properties");
            urlBase = config.getString("url.base");
            host = config.getString("host");
            browser = config.getString("browser");
            userEmail = config.getString("userEmail");
            userPassword = config.getString("userPassword");
            timeout = config.getInt("timeout");
            log.info("Staring UI tests with configuration:\nurl.base: " + urlBase + " using " + browser + " browser.\n");
            return config;
        } catch (Exception e) {
            log.error("Got exception when setting up UI Configuration.\nError: " + e.getMessage());
            return null;
        }
    }

}
