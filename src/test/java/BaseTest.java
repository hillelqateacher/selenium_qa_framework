import core.utils.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setUpDriver(final String browserName) {
        webDriver = WebDriverFactory.getWebDriverImpl(browserName);
    }

    @Before
    public void driverSetUp() {
        final String browserName = System.getProperty("wedriver.name");
        webDriver = WebDriverFactory.getWebDriverImpl(browserName);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @After
    public void driverTearDown() {
        webDriver.close();
        webDriver.quit();
    }
}
