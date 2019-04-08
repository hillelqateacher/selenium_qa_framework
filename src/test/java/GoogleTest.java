import core.fe.google.GoogleMainPage;
import core.fe.google.GoogleSearchResultPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleTest extends BaseTest {

    @Test
    public void googleCheckGooglePageTest() {
        getWebDriver().get("https://www.google.com/");
        String actualTitle = getWebDriver().getTitle();
        String expectedTitle = "Google";
        Assert.assertEquals("This is incorrect title displaced", expectedTitle, actualTitle);
    }

    @Test
    public void checkGoogleSearchTest() {
        getWebDriver().get("https://www.google.com/");
        WebElement searchInput = getWebDriver().findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("hillel", Keys.ENTER);
        List<WebElement> searchResultLinks = getWebDriver().findElements(By.xpath("//div[@class='rc']//h3"));
        Assert.assertFalse("There are no links displayed!", searchResultLinks.isEmpty());
        for (WebElement searchLink : searchResultLinks) {
            String text = searchLink.getText().toLowerCase();
            String failedTestMessage = String.format("There is incorrect link text in link [%s]", text);
            Assert.assertTrue(failedTestMessage, text.contains("hillel"));
        }
    }

    @Test
    public void checkGoogleSearchTestUsingOop() {
        getWebDriver().get("https://www.google.com/");
        final GoogleMainPage mainPage = new GoogleMainPage(getWebDriver());
        final GoogleSearchResultPage googleSearchResultPage = mainPage.searFor("hillel");
        final List<String> searchResults = googleSearchResultPage.getSearchResultItems();
        searchResults.forEach(item -> {
            String failedTestMessage = String.format("There is incorrect link text in link [%s]", item);
            Assert.assertTrue(failedTestMessage, item.toLowerCase().contains("hillel"));
        });
    }
}
