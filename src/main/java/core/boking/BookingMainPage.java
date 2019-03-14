package core.boking;

import core.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class BookingMainPage extends AbstractPage {

    @FindBy(xpath = "//a[@data-ga-track='click|Product Expansion|flights|kayak (index)']")
    private WebElement flyTicketsButton;

    public BookingMainPage(final WebDriver driver) {
        super(driver);
    }

    public BookingFlyTicketsPage clickFlyTicketsButton() {
        flyTicketsButton.click();

        final String currentOpenedWindowHandle = driver.getWindowHandle();
        System.out.println("Before switch: " + currentOpenedWindowHandle);

        final Set<String> windowsHandles = driver.getWindowHandles();
        for (final String handle : windowsHandles) {
            if (!currentOpenedWindowHandle.equals(handle)) {
                System.out.println("After switch: " + handle);
                driver.switchTo().window(handle);
            }
        }

        return new BookingFlyTicketsPage(driver);
    }
}
