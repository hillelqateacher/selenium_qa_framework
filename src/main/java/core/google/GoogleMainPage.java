package core.google;

import core.BaseUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@BaseUrl(value = "https://www.google.com/")
public class GoogleMainPage {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    private WebDriver driver;

    public GoogleMainPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleSearchResultPage searFor(final String text) {
        searchInput.sendKeys(text, Keys.ENTER);
        return new GoogleSearchResultPage(driver);
    }
}
