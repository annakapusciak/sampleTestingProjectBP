package web;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseWeb {

    @Test(description = "Check navigation to login page")
    public void navigateToLoginPage() {
        loginPage = homePage.goToLoginPage();
        Assertions.assertThat(loginPage.isPageLoadedCorrectly()).isTrue();
    }

    @DataProvider(name = "credentialsProvider")
    public static Object[][] credentialsProvider() {
        return new Object[][]{
                {configuration.userEmail, configuration.userPassword},
                {"someInvalidNotExisting@email", "anyPassword"},
                {"", ""},
        };
    }

    @Test(description = "Check login required Captcha using any credentials", priority = 1,
            dataProvider = "credentialsProvider",
            enabled = false)
    public void checkLoginUsingAnyCredentialsRequiresCaptcha(String email, String password) {
        loginPage.loginUsingCredentials(email, password);
        Assertions.assertThat(loginPage.captchaWasRequested()).isTrue();
        goBack();
    }

    @Test(description = "Check login without Captcha using valid credentials - this test was disabled" +
            "because in major cases login requires Captcha after providing credentials.",
            priority = 2,
            enabled = false)
    public void checkLoginUsingValidCredentials() {
        myAccountPage = loginPage.loginUsingCredentials(configuration.userEmail, configuration.userPassword);
        Assertions.assertThat(myAccountPage.isPageLoadedCorrectly()).isTrue();
    }

}
