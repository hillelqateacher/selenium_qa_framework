import core.iframe.SiteWithIFrameMainPage;
import org.junit.Assert;
import org.junit.Test;

public class IFrameTestSuite extends BaseTest {

    @Test
    public void checkIFrameTest() {
        getWebDriver().get("https://jsfiddle.net/westonruter/6mSuK/");
        final SiteWithIFrameMainPage mainPage = new SiteWithIFrameMainPage(getWebDriver());
        mainPage.searchWikiFor("Bitcoin");
        final String searchWikiResult = mainPage.getWikiSearchTitleText();
        Assert.assertEquals("There is incorrect  search result displayed!",
                "Bitcoin", searchWikiResult);
    }
}
