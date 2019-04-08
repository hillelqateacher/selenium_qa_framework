import core.fe.boking.BookingFlyTicketsPage;
import core.fe.boking.BookingMainPage;
import org.junit.Assert;
import org.junit.Test;

public class BookingTestSuite extends BaseTest {

    @Test
    public void checkSwitchingBetweenWindowsTest() {
        getWebDriver().get("https://www.booking.com/index.ru.html");
        final BookingMainPage bookingMainPage = new BookingMainPage(getWebDriver());
        final BookingFlyTicketsPage bookingFlyTicketsPage = bookingMainPage.clickFlyTicketsButton();
        Assert.assertEquals("Найдите авиабилеты на Booking.com", bookingFlyTicketsPage.getPageTitle());
    }
}
