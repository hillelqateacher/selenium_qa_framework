import core.dto.ProductDetailsDTO;
import core.sportchek.SportcheckShopppingCartPage;
import core.sportchek.SportchekProductDetailsPage;
import core.utils.DbHelper;
import core.utils.UrlBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class SportCheckTestSuite extends BaseTest {

    @Test
    public void checkShoppingCartTest() throws SQLException {
        final String url = "https://www.sportchek.ca/categories/men/jackets-coats-vests/winter-jackets/ski-snowboard-jackets/product/the-north-face-mens-harway-insulated-jacket-332339730.html";
        getWebDriver().get(url);

        final SportchekProductDetailsPage detailsPage = new SportchekProductDetailsPage(getWebDriver());
        detailsPage.selectRequiredSize("S");
        detailsPage.clickAddToCartButton();

        final SportcheckShopppingCartPage sportcheckShopppingCartPage = detailsPage.proceedToShoppingCartPage();
        final ProductDetailsDTO frontendData = sportcheckShopppingCartPage.getProductInfo();

        final String dbUserName = UrlBuilder.getPropertyValue("database.username");
        final String password = UrlBuilder.getPropertyValue("database.password");
        final String connectionUrl = UrlBuilder.getPropertyValue("database.connection.string");

        DbHelper.connectToDb(dbUserName, password, connectionUrl);
        DbHelper.executeQuery("select * from SportCheck");
        final ProductDetailsDTO dbData = DbHelper.mapDbData();
        Assert.assertEquals("There is incorrect 'Title'", frontendData.getTitle(), dbData.getTitle());
    }
}
