package web;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest extends BaseWeb {

    @Test(description = "Check load of home page")
    public void homePageLoadedCorrectly() {
        assertThat(homePage.isPageLoadedCorrectly()).isTrue();
    }

    @Test(description = "Check navigation to search result page")
    public void navigateToSearchResults() {
        searchResultsPage = homePage.searchForItems("bag");
        assertThat(searchResultsPage.isPageLoadedCorrectly()).isTrue();
    }

    @Test(description = "Check searching for any existing item(s)")
    public void searchForAnyExistingItem() {
        searchResultsPage = homePage.searchForItems("shoes");
        assertThat(searchResultsPage.getAmountOfSearchedResults()).isGreaterThan(0);
    }

    @Test(description = "Check searching for any not existing item")
    public void searchForAnyNotExistingItem() {
        searchResultsPage = homePage.searchForItems("someNotExistingItem");
        assertThat(searchResultsPage.getAmountOfSearchedResults()).isEqualTo(0);
    }

    @Test(description = "Check empty cart", priority = 1)
    public void checkEmptyCart() {
        homePage.checkCart();
        assertThat(homePage.isCartEmpty()).isTrue();
    }

}
