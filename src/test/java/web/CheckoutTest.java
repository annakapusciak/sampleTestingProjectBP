package web;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class CheckoutTest extends BaseWeb {

    @Test(description = "Check navigation to search result page")
    public void navigateToProductPage() {
        productPage = homePage.goToDetailsOfFirstItemOnSlider();
        assertThat(productPage.isPageLoadedCorrectly()).isTrue();
        goBack();
    }

    @Test(description = "Check adding products to the cart", priority = 1)
    public void checkAddProductToTheCart() {
        try {
            //given
            productPage = homePage.goToDetailsOfFirstItemOnSlider();
            cartPage = productPage.addProductToCart();

            //when
            checkoutPage = cartPage.checkoutCart();

            //then
            Assertions.assertThat(cartPage.itemAddedToTheCart()).isTrue();
            Assertions.assertThat(checkoutPage.getNumberOfItemsToCheckout()).isEqualTo(1);
            Assertions.assertThat(checkoutPage.isPageLoadedCorrectly()).isTrue();
        } catch (Exception e) {
            fail("Error when adding products to the cart.\nError: " + e.getMessage());
            log.error("Error when adding products to the cart.\nError: " + e.getMessage());
        }
    }

    @Test(description = "Checkout cart is ready for payment", priority = 2)
    public void checkoutCartIsReadyForPayment() {
        checkoutPage.provideUserDetailsForOrderAndContinueCheckout();
        Assertions.assertThat(checkoutPage.isContinueToPaymentButtonEnabled()).isTrue();
    }

}
