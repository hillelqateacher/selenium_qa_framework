package core.fe.iframe;

import core.fe.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SiteWithIFrameMainPage extends AbstractPage {

    @FindBy(xpath = "//iframe[@name='result']")
    private WebElement firstIFrame;

    @FindBy(xpath = "//iframe[not(@name)]")
    private WebElement secondIFrame;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement wikiSearchInput;

    @FindBy(xpath = "//h1[@id='firstHeading']")
    private WebElement wikiSearchTitle;

    public SiteWithIFrameMainPage(final WebDriver driver) {
        super(driver);
    }

    public void searchWikiFor(final String text) {
        driver.switchTo().frame(firstIFrame);
        driver.switchTo().frame(secondIFrame);
        wikiSearchInput.sendKeys(text, Keys.ENTER);
        //driver.switchTo().defaultContent()
    }

    public String getWikiSearchTitleText() {
        return wikiSearchTitle.getText();
    }
}
