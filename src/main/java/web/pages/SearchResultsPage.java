package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends GlobalPage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h2")
    private WebElement nbrOfResults;

    @FindBy(xpath = "//span/button[@type='submit']")
    private WebElement searchButtonOnSearchPage;

    public boolean isPageLoadedCorrectly() {
        return pageHeader.getText().equals("Search") &&
                searchBar.isEnabled() &&
                searchButtonOnSearchPage.isEnabled() &&
                nbrOfResults.getText().contains(" results");
    }

    public int getAmountOfSearchedResults() {
        return Integer.parseInt(nbrOfResults.getText().replace(" results", ""));
    }
}
