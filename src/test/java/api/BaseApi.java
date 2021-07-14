package api;

import configs.ConfigurationForApi;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.*;

@Slf4j
public abstract class BaseApi {

    ConfigurationForApi configuration = new ConfigurationForApi();

    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext context) {
        configuration.setupApiConfiguration();
        baseURI = configuration.getApiBaseUri();
        basePath = configuration.getApiBasePath();

        log.info("----------------------------------------------------------");
        log.info("Running test: " + context.getCurrentXmlTest().getName());
        log.info("----------------------------------------------------------");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
