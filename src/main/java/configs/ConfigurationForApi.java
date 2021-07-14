package configs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

@Slf4j
@Data
public class ConfigurationForApi {

    String apiBaseUri, apiBasePath, authenticateUser, authenticatePass;

    public Configuration setupApiConfiguration() {
        try {
            Configuration config = new PropertiesConfiguration("api.properties");
            apiBaseUri = config.getString("api.base.uri");
            apiBasePath = config.getString("api.base.path");
            log.info("Staring API tests with configuration:\napiBaseUrl: " + apiBaseUri + apiBasePath);
            return config;
        } catch (Exception e) {
            log.error("Got exception when setting up API Configuration.\nError: " + e.getMessage());
            return null;
        }
    }

}
