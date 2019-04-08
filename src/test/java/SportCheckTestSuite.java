import core.be.SportCheckApi;
import core.fe.dto.ProductDetailsDTO;
import core.fe.sportchek.SportcheckShopppingCartPage;
import core.fe.sportchek.SportchekProductDetailsPage;
import core.utils.DbHelper;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.unitils.reflectionassert.ReflectionAssert;

import java.sql.SQLException;
import java.util.Map;

public class SportCheckTestSuite extends BaseTest {

    @Test
    public void checkShoppingCartTest() throws SQLException {
        final String url = "https://www.sportchek.ca/categories/men/jackets-coats-vests/winter-jackets/ski-snowboard-jackets/product/the-north-face-mens-harway-insulated-jacket-332339730.html";
        getWebDriver().get(url);

        final SportchekProductDetailsPage detailsPage = new SportchekProductDetailsPage(getWebDriver());
        detailsPage.selectRequiredSize("M");
        detailsPage.clickAddToCartButton();

        final SportcheckShopppingCartPage sportcheckShopppingCartPage = detailsPage.proceedToShoppingCartPage();
        final ProductDetailsDTO frontendData = sportcheckShopppingCartPage.getProductInfo();

        final ProductDetailsDTO backendData = DbHelper.executeQuery("select * from SportCheck");
        ReflectionAssert.assertReflectionEquals("There are incorrect 'Shopping Cart Data' displayed!", backendData, frontendData);
        Assert.assertEquals("There is incorrect 'Title'", frontendData.getTitle(), backendData.getTitle());
    }

    @Test
    public void verifyAddToCartUsingRest() {
        final SportCheckApi sportCheckApi = new SportCheckApi();
        final String requestBody = "{  \n" +
                "   \"itemEntries\":[  \n" +
                "      {  \n" +
                "         \"code\":\"331523434\",\n" +
                "         \"productPictureUrl\":\"//fgl.scene7.com/is/image/FGLSportsLtd/FGL_331523397_01_a\",\n" +
                "         \"productPageUrl\":\"https://www.sportchek.ca/categories/women/womens-jackets-coats-vests/rain-jackets/product/columbia-womens-arcadia-ii-2l-shell-jacket-331523397.html#331523397=331523434\",\n" +
                "         \"quantity\":\"1\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        final Response response = sportCheckApi.addItemToCart(requestBody);
        final Map<String, String> cookies = response.getCookies();
        getWebDriver().get("https://www.sportchek.ca");
        cookies.forEach((key, value) -> getWebDriver().manage().addCookie(new Cookie(key, value)));
        getWebDriver().navigate().refresh();
    }
}
