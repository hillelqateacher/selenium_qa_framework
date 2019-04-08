import core.fe.ekatalog.EkatalogMainPage;
import core.fe.ekatalog.EkatalogMobilePhonesPage;
import core.fe.ekatalog.FilterMobilePhonesPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EkatalogTestSuite extends BaseTest {

    @Test
    public void checkFilteringProcessTest() {
        getWebDriver().get("https://ek.ua/"); //1
        final EkatalogMainPage ekatalogMainPage = new EkatalogMainPage(getWebDriver());
        ekatalogMainPage.hoverGadgetMenuItem(); //2
        final EkatalogMobilePhonesPage ekatalogMobilePhonesPage = ekatalogMainPage.selectMobilePhonesMenuItem();//3
        ekatalogMobilePhonesPage.applyFilter();//4
        final FilterMobilePhonesPage filterMobilePhonesPage = ekatalogMobilePhonesPage.clickShowMoreItems();//5
        final List<String> filterItems = filterMobilePhonesPage.getFilterItems();//6
        Assert.assertFalse("There are no filter items available!", filterItems.isEmpty());
        filterItems.forEach(item -> { //7
            final String filterItemText = String.format("There is incorrect filter item text displayed! Expected [%s], Actual [%s]", "Apple", item);
            Assert.assertTrue(filterItemText, item.toLowerCase().contains("apple"));
        });
    }
}
