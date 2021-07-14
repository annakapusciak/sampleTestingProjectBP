package web;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class NewAccountCreationTest extends BaseWeb {

    @Test(description = "Check navigation to new account creation page")
    public void navigateToNewAccountCreationPage() {
        newAccountPage = homePage.goToLoginPage()
                .goToCreateNewAccountPage();
        Assertions.assertThat(newAccountPage.isPageLoadedCorrectly()).isTrue();
    }

    @Test(description = "Check login required Captcha using any credentials", priority = 1)
    public void checkNewAccountCreationRequiresCaptcha() {
        newAccountPage.createNewAccount();
        Assertions.assertThat(newAccountPage.captchaWasRequested()).isTrue();
        goBack();
    }

}
